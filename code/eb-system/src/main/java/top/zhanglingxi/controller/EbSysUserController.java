package top.zhanglingxi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import top.zhanglingxi.domain.AjaxResult;
import top.zhanglingxi.domain.EbSysUserDomain;
import top.zhanglingxi.service.IEbSysUserService;

import java.util.List;

@RestController
@RequestMapping("/sys/user")
public class EbSysUserController {
    @Autowired
    private IEbSysUserService ebSysUserService;

    @GetMapping
    public AjaxResult list(EbSysUserDomain user) {
        List<EbSysUserDomain> list = ebSysUserService.getList(user);
        return AjaxResult.success(list);
    }
}
