package fit.iseeyou.config.security.custom;

import cn.hutool.http.HttpStatus;
import com.alibaba.fastjson.JSONObject;
import fit.iseeyou.common.domain.AjaxResult;
import org.springframework.http.MediaType;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class CustomAuthenticationEntryPoint implements AuthenticationEntryPoint {
    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException, ServletException {
        response.setCharacterEncoding("UTF-8");
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        AjaxResult error = AjaxResult.error(HttpStatus.HTTP_INTERNAL_ERROR, authException.getMessage());
        PrintWriter out = response.getWriter();
        out.write(JSONObject.toJSONString(error));
    }
}
