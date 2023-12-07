package fit.iseeyou.login10.controller;

import cn.hutool.crypto.digest.MD5;
import fit.iseeyou.common.domain.AjaxResult;
import fit.iseeyou.common.utils.ImageCodeUtils;
import fit.iseeyou.common.utils.JWTUtils;
import fit.iseeyou.common.utils.RedisUtils;
import fit.iseeyou.common.utils.Sm4Utils;
import fit.iseeyou.login10.domain.UserDomain;
import fit.iseeyou.login10.domain.vo.LoginVO;
import fit.iseeyou.login10.service.IUserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.*;
import java.util.concurrent.TimeUnit;

@RestController
@RequestMapping("/10/login")
@Slf4j
public class LoginController {
    @Autowired
    private RedisUtils redisUtils;
    @Autowired
    private IUserService userService;

    @GetMapping
    public AjaxResult verifyImage() {
        AjaxResult ajax = AjaxResult.success();
        Map<String, Object> imageCode = ImageCodeUtils.genImageCode();
        UUID uuid = UUID.randomUUID();
        String code = imageCode.get("code").toString();
        redisUtils.setCacheObject(uuid.toString(), code, 1, TimeUnit.MINUTES);
        BufferedImage buffImg = (BufferedImage) imageCode.get("image");
        ByteArrayOutputStream os = new ByteArrayOutputStream();
        Base64.Encoder encoder = Base64.getEncoder();
        ImageIO.setUseCache(false);
        try {
            ImageIO.write(buffImg, "png", os);
        } catch (IOException e) {
            log.error(e.getMessage());
            return AjaxResult.error();
        }
        ajax.put("uuid", uuid.toString());
        ajax.put("image", encoder.encodeToString(os.toByteArray()));
        return ajax;
    }

    @PostMapping
    public AjaxResult login(@RequestBody LoginVO loginVO) {
        String uuid = loginVO.getUuid();
        Object vcCache = redisUtils.getCacheObject(uuid);
        if (Objects.nonNull(vcCache)) {
            String username = loginVO.getUsername();
            String password = loginVO.getPassword();
            String verifyCode = loginVO.getVerifyCode();
            // 解密password
            String pwd = Sm4Utils.decrypt(password);
            pwd = MD5.create().digestHex(pwd);
            // 获取用户信息
            UserDomain user = userService.getUserByUsername(username);
            if (!verifyCode.equals(vcCache.toString())) {
                return AjaxResult.error("验证码错误！");
            } else if (Objects.isNull(user)) {
                return AjaxResult.error("用户名错误！");
            } else if (!user.getUserPwd().equals(pwd)) {
                return AjaxResult.error("密码错误！");
            } else {
                Map<String, String> tokenInfo = new HashMap<>();
                tokenInfo.put("username", username);
                String token = JWTUtils.getToken(tokenInfo);
                if (redisUtils.hasKey(uuid)) redisUtils.deleteObject(uuid);
                AjaxResult ajax = AjaxResult.success("登录成功！");
                ajax.put("token", token);
                return ajax;
            }
        }
        return AjaxResult.error("验证码已失效，请刷新登录页面");
    }
}
