package fit.iseeyou.interceptor;

import fit.iseeyou.common.utils.JWTUtils;
import fit.iseeyou.exception.UserAuthException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Slf4j
public class RequestInterceptor implements HandlerInterceptor {
    private static final String[] IGNORE_URIS = {"/login"};
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String uri = request.getRequestURI();
        for (String ignoreUri : IGNORE_URIS) {
            if (uri.contains(ignoreUri))
                return true;
        }
        String authorization = request.getHeader("Authorization");
        try {
            String token = authorization.split(" ")[1];
            JWTUtils.verifyToken(token);
            return true;
        } catch (Exception e) {
            log.error(e.getMessage());
            throw new UserAuthException("用户验证失败");
        }
    }
}
