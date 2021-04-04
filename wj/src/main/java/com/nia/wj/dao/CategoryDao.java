package com.nia.wj.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nia.wj.pojo.Category;

/**
 * @ClassName CategoryDao
 * @Description TODO
 * @Author ganwei
 * @Date 2021/4/4 23:44
 * @VERSION 1.0
 **/


public interface CategoryDao extends JpaRepository<Category, Integer> {

}

