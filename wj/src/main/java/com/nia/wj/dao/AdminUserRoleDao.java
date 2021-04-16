package com.nia.wj.dao;

import com.nia.wj.pojo.AdminUserRole;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * @ClassName AdminUserRoleDao
 * @Description TODO
 * @Author ganwei
 * @Date 2021/4/4 23:40
 * @VERSION 1.0
 **/
public interface AdminUserRoleDao extends JpaRepository<AdminUserRole,Integer> {

    /**
     * 根据用户主键查询其用有的角色
     * @param uid
     * @return
     */
    List<AdminUserRole> findAllByUid(int uid);

    /**
     * 更具用户主键删除其对应的所有菜单
     * @param uid
     */
    void deleteAllByUid(int uid);
}
