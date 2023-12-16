package fit.iseeyou.config.security.custom;

import com.alibaba.fastjson.JSONObject;
import fit.iseeyou.common.domain.AjaxResult;
import fit.iseeyou.common.utils.RedisUtils;
import fit.iseeyou.web.domain.EbLoginUserDomain;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Objects;

@Component
public class CustomLogoutSuccessHandler implements LogoutSuccessHandler {
    @Autowired
    private RedisUtils redisUtils;

    @Override
    public void onLogoutSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        PrintWriter out = response.getWriter();
        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json;charset=utf-8;");
        // 从redis中取出用户信息
//        Object username = authentication.getPrincipal();
//        EbLoginUserDomain loginUser = redisUtils.getCacheObject("ebao:login:" + username);
//        if (Objects.isNull(loginUser)) {
//            AjaxResult error = AjaxResult.error("注销失败");
//            out.write(JSONObject.toJSONString(error));
//        }
//        redisUtils.deleteObject("ebao:login:" + username);
        AjaxResult success = AjaxResult.success("注销成功");
        success.put("user", authentication.getPrincipal());
        out.write(JSONObject.toJSONString(success));
    }
}
