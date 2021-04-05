package com.nia.wj.controller;

import com.nia.wj.pojo.RetObj;
import com.nia.wj.pojo.User;
import com.nia.wj.service.UserService;
import com.sun.xml.internal.txw2.output.ResultFactory;
import org.apache.shiro.crypto.SecureRandomNumberGenerator;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.tags.HtmlEscapeTag;
import org.springframework.web.util.HtmlUtils;

import javax.servlet.http.HttpSession;
import java.util.Date;
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

        String loginName = loginUser.getLoginName();
        // 特殊字符转义的问题，例如< > ?等，有的时候保存到数据库时，数据库会自动将特殊字符进行转义，存到数据库的就不是你输入的那些特殊字符，而是转义以后的，例如“<”,保存到数据库时会变成“&lt;”
        loginName = HtmlUtils.htmlEscape(loginName);

        User user = userService.get(loginName, loginUser.getPassword());
        if (user == null) {
            return new RetObj(400);
        }else {
            session.setAttribute("user", loginUser);
            return new RetObj(200);
        }

    }

    @PostMapping("api/register")
    @ResponseBody
    public RetObj register(@RequestBody User user) {

        String userName = user.getUserName();
        String loginName = user.getLoginName();
        String password = user.getPassword();

        //特殊字符转义的问题，例如< > ?等，有的时候保存到数据库时，数据库会自动将特殊字符进行转义，存到数据库的就不是你输入的那些特殊字符，而是转义以后的，例如“<”,保存到数据库时会变成“&lt;”
        userName = HtmlUtils.htmlEscape(userName);
        loginName = HtmlUtils.htmlEscape(loginName);
        user.setLoginName(userName);
        user.setLoginName(loginName);

        boolean exist = userService.isExist(loginName);
        if (exist) {
            String message = "用户名已被使用";
            return new RetObj(message);
        }

        // 生成盐,默认长度 16 位
        String salt = new SecureRandomNumberGenerator().nextBytes().toString();
        // 设置 hash 算法迭代次数
        int times = 2;
        // 得到 hash 后的密码
        String encodedPassword = new SimpleHash("md5", password, salt, times).toString();
        // 存储用户信息，包括 salt 与 hash 后的密码
        user.setSalt(salt);
        user.setPassword(encodedPassword);
        user.setCreateTime(new Date());
        userService.add(user);

        return new RetObj(user);
    }

}
