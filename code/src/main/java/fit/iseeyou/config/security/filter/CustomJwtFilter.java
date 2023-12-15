package fit.iseeyou.config.security.filter;

import cn.hutool.jwt.JWT;
import cn.hutool.jwt.JWTException;
import cn.hutool.jwt.JWTUtil;
import fit.iseeyou.common.utils.RedisUtils;
import fit.iseeyou.web.domain.EbLoginUserDomain;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Objects;

@Component
@Slf4j
public class CustomJwtFilter extends OncePerRequestFilter {
    @Value("${custom.jwt.key}")
    private String key;
    @Autowired
    private RedisUtils redisUtils;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
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
            EbLoginUserDomain user = redisUtils.getCacheObject("ebao:login:" + username);
            if (Objects.isNull(user)) {
                log.error("JWT校验过程中出现异常，异常类型：{}，异常信息：{}",
                        UsernameNotFoundException.class.getCanonicalName(), "请先登录");
                throw new UsernameNotFoundException("请先登录");
            }
            // 创建认证对象
            Authentication authentication = new UsernamePasswordAuthenticationToken(username, user.getPassword(), user.getAuthorities());
            // 将认证对象设置到Security上下文中
            SecurityContextHolder.getContext().setAuthentication(authentication);
        }
        // 继续过滤器链
        filterChain.doFilter(request, response);
    }
}
