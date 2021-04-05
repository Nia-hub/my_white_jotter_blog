package com.nia.wj.service;

import com.nia.wj.dao.UserDao;
import com.nia.wj.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @program: wj
 * @description: 用户service
 * @author: ganwei
 * @create: 2021-03-29 19:03
 **/
@Service
public class UserService {
    @Autowired
    UserDao userDao;

    public boolean isExist(String loginName) {
        User user = getByLoinName(loginName);
        return null!=user;
    }

    public User getByLoinName(String username) {
        return userDao.findByLoginName(username);
    }

    public User get(String username, String password){
        return userDao.findByLoginNameAndPassword(username, password);
    }

    public void add(User user) {
        userDao.save(user);
    }
}
