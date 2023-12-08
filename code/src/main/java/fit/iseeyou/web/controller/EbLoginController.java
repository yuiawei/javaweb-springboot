package fit.iseeyou.web.controller;

import cn.hutool.captcha.CaptchaUtil;
import cn.hutool.captcha.LineCaptcha;
import cn.hutool.captcha.generator.RandomGenerator;
import cn.hutool.core.codec.Base64;
import cn.hutool.jwt.JWTUtil;
import cn.hutool.jwt.signers.JWTSigner;
import cn.hutool.jwt.signers.JWTSignerUtil;
import fit.iseeyou.common.domain.AjaxResult;
import fit.iseeyou.common.utils.RedisUtils;
import fit.iseeyou.config.security.MyUserDetails;
import fit.iseeyou.web.domain.EbSysUserDomain;
import fit.iseeyou.web.domain.vo.LoginReqVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

@RestController
@RequestMapping("/user")
public class EbLoginController {
    @Value("${custom.jwt.key}")
    private String key;
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private RedisUtils redisUtils;

    @GetMapping("/image")
    public AjaxResult image() {
        UUID uuid = UUID.randomUUID();
        LineCaptcha captcha = CaptchaUtil.createLineCaptcha(250, 80, 4, 10);
        captcha.setGenerator(new RandomGenerator("0123456789", 4));
        captcha.createCode();
        String code = captcha.getCode();
        redisUtils.setCacheObject("ebao:image-code:" + uuid, code, 1, TimeUnit.MINUTES);
        Map<String, Object> data = new HashMap<>();
        data.put("uuid", uuid);
        data.put("image", captcha.getImageBase64Data());
        return AjaxResult.success(data);
    }

    @PostMapping("/login")
    public AjaxResult login(@RequestBody LoginReqVO reqVO) {
        String uuid = reqVO.getUuid();
        String verifyCode = reqVO.getVerifyCode();
        Object cacheObject = redisUtils.getCacheObject("ebao:image-code:" + uuid);
        if (Objects.isNull(cacheObject)) {
            return AjaxResult.error("验证码已过期");
        }
        if (!verifyCode.equals(cacheObject.toString())) {
            return AjaxResult.error("验证码错误");
        }
        String username = reqVO.getUsername();
        String password = reqVO.getPassword();
        UsernamePasswordAuthenticationToken upAuthenticationToken = new UsernamePasswordAuthenticationToken(username, new String(Base64.decode(password)));
        Authentication authenticate = authenticationManager.authenticate(upAuthenticationToken);
        if (Objects.isNull(authenticate)) {
            throw new AuthenticationServiceException("授权服务失败");
        }
        MyUserDetails principal = (MyUserDetails) authenticate.getPrincipal();
        // 生成jwt
        Map<String, Object> payload = new HashMap<>();
        payload.put("username", username);
        JWTSigner jwtSigner = JWTSignerUtil.hs256(key.getBytes());
        String token = JWTUtil.createToken(payload, jwtSigner);
        // 获取完整的用户信息并存入redis中，以username为key
        redisUtils.setCacheObject("ebao:login:" + username, principal);
        AjaxResult ajax = AjaxResult.success("登录成功");
        ajax.put("token", token);
        return ajax;
    }

    @GetMapping("/logout")
    public AjaxResult logout() {
        // 从redis中取出用户信息
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Object username = authentication.getPrincipal();
        MyUserDetails loginUser = redisUtils.getCacheObject("ebao:login:" + username);
        if (Objects.isNull(loginUser)) {
            return AjaxResult.error("退出登录失败，请先登录");
        }
        redisUtils.deleteObject("ebao:login:" + username);
        SecurityContextHolder.getContext().setAuthentication(null);
        return AjaxResult.success("退出登录成功");
    }
}
