package com.example.security.controller;

import com.example.common.utils.R;
import com.example.security.Service.LoginAndLogoutService;
import com.example.security.vo.params.LoginParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class LoginAndLogoutController {
    @Autowired
    LoginAndLogoutService loginAndLogoutService;

    @RequestMapping("login")
    public R login(@RequestBody LoginParam loginParam) {
        return loginAndLogoutService.login(loginParam);
    }

    @PostMapping("logout")
    public R logout() {
        return loginAndLogoutService.logout();
    }

    @DeleteMapping("deleteUser/{id}")
    public R deleteUser(@PathVariable Long id) {
        return loginAndLogoutService.deleteUser(id);
    }

}
