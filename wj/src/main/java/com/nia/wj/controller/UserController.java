package com.nia.wj.controller;

import com.nia.wj.pojo.*;
import com.nia.wj.result.Result;
import com.nia.wj.result.ResultFactory;
import com.nia.wj.service.*;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @program: wj
 * @description: 用户控制器
 * @author: ganwei
 * @create: 2021-04-07 14:38
 **/
@RestController
public class UserController {
    @Autowired
    UserService userService;
    @Autowired
    AdminUserRoleService adminUserRoleService;

    @RequiresPermissions("/api/admin/user")
    @GetMapping("/api/admin/user")
    public Result listUsers() {
        return ResultFactory.buildSuccessResult(userService.list());
    }


}
