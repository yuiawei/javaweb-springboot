package top.zhanglingxi.security.custom;

import cn.hutool.http.HttpStatus;
import com.alibaba.fastjson.JSONObject;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

@Component
public class CustomAuthenticationSuccessHandler implements AuthenticationSuccessHandler {
    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json;charset=utf-8;");
        Map<String, Object> res = new HashMap<>();
        res.put("code", HttpStatus.HTTP_OK);
        res.put("msg", "登录成功");
        res.put("user", authentication.getPrincipal());
        PrintWriter out = response.getWriter();
        out.write(JSONObject.toJSONString(res));
    }
}
