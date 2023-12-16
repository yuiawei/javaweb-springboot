package top.zhanglingxi.aop;

import com.alibaba.fastjson.JSONObject;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import top.zhanglingxi.domain.AjaxResult;
import top.zhanglingxi.domain.EbSysLoginLogDomain;
import top.zhanglingxi.domain.vo.LoginReqVO;
import top.zhanglingxi.mapper.EbSysLoginLogMapper;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Objects;

@Aspect
@Component
public class LogAspect {
    @Autowired
    private EbSysLoginLogMapper ebSysLoginLogMapper;

    @Pointcut("execution(public * top.zhanglingxi.controller.EbLoginController.login(..))")
    public void loginPointcut(){};

    @Around("loginPointcut()")
    public Object aroundLogin(ProceedingJoinPoint joinPoint) throws Throwable {
        Object[] args = joinPoint.getArgs();
        if (Objects.nonNull(args) && args.length > 0) {
            LoginReqVO reqVO = (LoginReqVO) args[0];
            Object res = joinPoint.proceed(args);
            if (Objects.nonNull(res) && res instanceof AjaxResult) {
                AjaxResult ajaxResult = (AjaxResult) res;
                EbSysLoginLogDomain ebSysLoginLogDomain = new EbSysLoginLogDomain();
                ebSysLoginLogDomain.setUsername(reqVO.getUsername());
                ebSysLoginLogDomain.setRequestData(JSONObject.toJSONString(reqVO));
                ebSysLoginLogDomain.setLoginStatus(Integer.parseInt(ajaxResult.get("code").toString()) == 200 ? 1 : 0);
                ebSysLoginLogDomain.setLoginTime(LocalDateTime.now());
                ebSysLoginLogMapper.insert(ebSysLoginLogDomain);

                return res;
            }
            throw new RuntimeException("请求/doLogin接口，响应数据异常");
        }
        throw new IllegalArgumentException("请求/doLogin接口，请求参数不能为空");
    }
}
