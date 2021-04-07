package com.nia.wj.dao;

import com.nia.wj.pojo.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserDao extends JpaRepository<User, Integer> {

    /**
     * 根据登录名查出用户
     * @param loginName
     * @return
     */
    User findByLoginName(String loginName);

    User findByLoginNameAndPassword(String loginName, String password);
}
