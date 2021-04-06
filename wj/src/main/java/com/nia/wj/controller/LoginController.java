package com.nia.wj.controller;

import com.nia.wj.result.Result;
import com.nia.wj.pojo.User;
import com.nia.wj.service.UserService;
import com.nia.wj.result.ResultFactory;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.crypto.SecureRandomNumberGenerator;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.HtmlUtils;

import javax.servlet.http.HttpSession;
import java.util.Date;

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
    public Result login(@RequestBody User loginUser){

        String loginName = loginUser.getLoginName();
        Subject subject = SecurityUtils.getSubject();
        //loginName便成了subject中的principal
        UsernamePasswordToken usernamePasswordToken = new UsernamePasswordToken(loginName, loginUser.getPassword());
        usernamePasswordToken.setRememberMe(true);//记住cookie

        try{
            subject.login(usernamePasswordToken);//核心
            return ResultFactory.buildSuccessResult(loginName);
        } catch (AuthenticationException e) {
            String message = "账号密码错误";
            return ResultFactory.buildFailResult(message);
        }

        // 特殊字符转义的问题，例如< > ?等，有的时候保存到数据库时，数据库会自动将特殊字符进行转义，存到数据库的就不是你输入的那些特殊字符，而是转义以后的，例如“<”,保存到数据库时会变成“&lt;”
//        loginName = HtmlUtils.htmlEscape(loginName);
    }

    /**
     * 注册用户
     * @param user
     * @return
     */
    @PostMapping("api/register")
    @ResponseBody
    public Result register(@RequestBody User user) {

        String userName = user.getUserName();
        String loginName = user.getLoginName();
        String password = user.getPassword();

        //特殊字符转义的问题，例如< > ?等，有的时候保存到数据库时，数据库会自动将特殊字符进行转义，存到数据库的就不是你输入的那些特殊字符，而是转义以后的，例如“<”,保存到数据库时会变成“&lt;”
        userName = HtmlUtils.htmlEscape(userName);
        loginName = HtmlUtils.htmlEscape(loginName);
        user.setUserName(userName);
        user.setLoginName(loginName);

        boolean exist = userService.isExist(loginName);
        if (exist) {
            String message = "用户名已被使用";
            return ResultFactory.buildFailResult(message);
        }

        // 生成盐,默认长度 16 位,随机的 byte 数组，又转换成了字符串类型的 base64 编码并返回
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

        return ResultFactory.buildSuccessResult(user);
    }

    /**
     * 登出
     * @return
     */
    @ResponseBody
    @GetMapping("api/logout")
    public Result logout(){

        Subject subject = SecurityUtils.getSubject();
        subject.logout();//核心，其实现方法中会清除 session、principals，并把 authenticated 设置为 false

        return ResultFactory.buildSuccessResult("登出成功");
    }

    /**
     * 验证用户是否处于登陆状态
     * @return
     */
    @ResponseBody
    @GetMapping(value = "api/authentication")
    public String authentication(){
        return "身份认证成功";
    }

}
