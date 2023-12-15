package fit.iseeyou.config.security.custom;

import cn.hutool.http.HttpStatus;
import com.alibaba.fastjson.JSONObject;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

public class CustomAuthenticationFailureHandler implements AuthenticationFailureHandler {
    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException, ServletException {
        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json;charset=utf-8;");
        Map<String, Object> res = new HashMap<>();
        res.put("code", HttpStatus.HTTP_INTERNAL_ERROR);
        res.put("msg", "登录失败");
        res.put("exception", exception.getMessage());
        PrintWriter out = response.getWriter();
        out.write(JSONObject.toJSONString(res));
    }
}
