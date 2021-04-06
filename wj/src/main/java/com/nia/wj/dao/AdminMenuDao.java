package com.nia.wj.dao;

import com.nia.wj.pojo.AdminMenu;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * @ClassName BookDao
 * @Description TODO
 * @Author ganwei
 * @Date 2021/4/4 23:40
 * @VERSION 1.0
 **/
public interface AdminMenuDao extends JpaRepository<AdminMenu, Integer> {

     AdminMenu findById(int id);

     List<AdminMenu> findAllByParentId(int parentId);
}

