package fit.iseeyou.login10.controller;

import fit.iseeyou.common.domain.AjaxResult;
import fit.iseeyou.login10.domain.UserDomain;
import fit.iseeyou.login10.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/10/user")
public class UserController {
    @Autowired
    private IUserService userService;

    @GetMapping
    public AjaxResult list(UserDomain userDomain) {
        // TODO
        return null;
    }

    @GetMapping("/{id}")
    public AjaxResult getUserById(@PathVariable("id") Integer id) {
        return AjaxResult.success(userService.getUser(id));
    }

    @GetMapping("/username/{username}")
    public AjaxResult getUserByUsername(@PathVariable("username") String username) {
        return AjaxResult.success(userService.getUserByUsername(username));
    }
}
