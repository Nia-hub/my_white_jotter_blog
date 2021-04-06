package com.nia.wj.dao;

import com.nia.wj.pojo.AdminRoleMenu;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * @ClassName AdminRoleMenuDao
 * @Description TODO
 * @Author ganwei
 * @Date 2021/4/4 23:40
 * @VERSION 1.0
 **/
public interface AdminRoleMenuDao extends JpaRepository<AdminRoleMenu,Integer> {

    List<AdminRoleMenu> findAllByRid(int rid);

    List<AdminRoleMenu> findAllByRid(List<Integer> rids);

    void deleteAllByRid(int rid);
}
