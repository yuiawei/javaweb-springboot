package fit.iseeyou.config.security;

import cn.hutool.core.codec.Base64;
import cn.hutool.core.io.FastByteArrayOutputStream;
import cn.hutool.core.io.IoUtil;
import cn.hutool.jwt.JWT;
import cn.hutool.jwt.JWTException;
import cn.hutool.jwt.JWTUtil;
import com.alibaba.fastjson.JSONObject;
import fit.iseeyou.common.utils.RedisUtils;
import fit.iseeyou.web.domain.EbSysUserDomain;
import fit.iseeyou.web.mapper.EbSysUserMapper;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.authentication.rememberme.RememberMeAuthenticationException;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Objects;

/**
 * 定义JWT过滤器
 */
@Component
@Slf4j
public class JwtAuthenticationFilter extends OncePerRequestFilter {
    @Value("${custom.jwt.key}")
    private String key;
    @Autowired
    private EbSysUserMapper ebSysUserMapper;
    @Autowired
    private RedisUtils redisUtils;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        // 从请求头中获取JWT
        String jwt = null;
        String bearerToken = request.getHeader("Authorization");
        if (bearerToken != null && bearerToken.startsWith("Bearer ")) {
            jwt = bearerToken.substring(7);
        }
        // 验证JWT
        if (jwt != null && JWTUtil.verify(jwt, key.getBytes())) {
            // 从JWT中提取用户名
            JWT parsed = JWTUtil.parseToken(jwt);
            Object username = parsed.getPayload("username");
            if (Objects.isNull(username)) {
                log.error("JWT校验过程中出现异常，异常类型：{}，异常信息：{}",
                        JWTException.class.getCanonicalName(), "未能从payload中获取信息");
                throw new JWTException("未能从payload中获取信息");
            }
            // 从redis中获取用户信息
            MyUserDetails user = redisUtils.getCacheObject("ebao:login:" + username);
            if (Objects.isNull(user)) {
                log.error("JWT校验过程中出现异常，异常类型：{}，异常信息：{}",
                        UsernameNotFoundException.class.getCanonicalName(), "请先登录");
                throw new UsernameNotFoundException("请先登录");
            }

            /*// 根据username获取用户信息
            EbSysUserDomain user = ebSysUserMapper.getUserByUsername(username.toString());
            if (Objects.isNull(user)) {
                log.error("JWT校验过程中出现异常，异常类型：{}，异常信息：{}",
                        UsernameNotFoundException.class.getCanonicalName(), "用户名错误");
                throw new UsernameNotFoundException("用户名错误");
            }*/
            /*try (ServletInputStream is = request.getInputStream()) {
                FastByteArrayOutputStream jsonStream = IoUtil.read(is);
                JSONObject jsonObject = JSONObject.parseObject(jsonStream.toString());
                Object rememberMe = jsonObject.get("rememberMe");
                if (Objects.nonNull(rememberMe)) {
                    request.setAttribute("rememberMe", rememberMe);
                }
            } catch (IOException e) {
                log.error("获取请求体数据时出现异常");
                throw new RememberMeAuthenticationException("获取请求体数据时出现异常");
            }*/
            // 创建认证对象
            // TODO 获取权限信息
            Authentication authentication = new UsernamePasswordAuthenticationToken(username, user.getPassword(), null);
            // 将认证对象设置到Security上下文中
            SecurityContextHolder.getContext().setAuthentication(authentication);
        }
        // 继续过滤器链
        filterChain.doFilter(request, response);
    }
}
