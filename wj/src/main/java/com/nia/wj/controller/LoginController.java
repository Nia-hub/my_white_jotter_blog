package com.nia.wj.controller;

import com.nia.wj.pojo.RetObj;
import com.nia.wj.pojo.User;
import com.nia.wj.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.tags.HtmlEscapeTag;
import org.springframework.web.util.HtmlUtils;

import javax.servlet.http.HttpSession;
import java.util.Objects;

/**
 * @program: wj
 * @description: 登录控制器
 * @author: ganwei
 * @create: 2021-03-26 14:38
 **/
@Controller
public class LoginController {

    @Autowired
    private UserService userService;

    /**
     * 用户登录
     * @param loginUser
     * @return
     */
    @CrossOrigin(origins = "*",maxAge = 3600)
    @PostMapping(value = "/api/login")
    @ResponseBody
    public RetObj login(@RequestBody User loginUser, HttpSession session){

        // 对 html 标签进行转义，防止 XSS 攻击
        String userName = loginUser.getUserName();
        userName = HtmlUtils.htmlEscape(userName);

        User user = userService.get(userName, loginUser.getPassword());
        if (user == null) {
            return new RetObj(400);
        }else {
            session.setAttribute("user", loginUser);
            return new RetObj(200);
        }

    }



}
