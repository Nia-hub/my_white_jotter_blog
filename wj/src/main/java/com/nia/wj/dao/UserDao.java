package com.nia.wj.dao;

import com.nia.wj.pojo.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserDao extends JpaRepository<User, Integer> {

    User findByUserName(String userName);

    User findByUserNameAndPassword(String userName, String password);
}