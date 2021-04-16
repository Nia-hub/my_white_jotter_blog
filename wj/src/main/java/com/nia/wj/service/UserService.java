package com.nia.wj.service;

import com.nia.wj.dao.UserDao;
import com.nia.wj.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @program: wj
 * @description: 用户service
 * @author: ganwei
 * @create: 2021-03-29 19:03
 **/
@Service
public class UserService {

    @Autowired
    private UserDao userDao;

    /**
     * 根据登录名查询用户是否存在
     * @param loginName
     * @return
     */
    public boolean isExist(String loginName) {
        User user = getByLoinName(loginName);
        return null!=user;
    }

    /**
     * 根据登录名查出用户
     * @param loginName
     * @return
     */
    public User getByLoinName(String loginName) {
        return userDao.findByLoginName(loginName);
    }

    /**
     * 根据登录名和密码查询用户
     * @param username
     * @param password
     * @return
     */
    public User get(String username, String password){
        return userDao.findByLoginNameAndPassword(username, password);
    }

    /**
     * 添加用户
     * @param user
     */
    public void add(User user) {
        userDao.save(user);
    }

    /**
     * 查询所有用户
     * @return
     */
    public List<User> list(){
        return userDao.findAll();
    }
}
