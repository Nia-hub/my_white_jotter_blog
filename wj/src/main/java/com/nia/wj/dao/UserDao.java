package com.nia.wj.dao;

import com.nia.wj.pojo.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserDao extends JpaRepository<User, Integer> {

    User findByLoinName(String loinName);

    User findByLoinNameAndPassword(String LoinName, String password);
}
