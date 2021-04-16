package com.nia.wj.dao;

import com.nia.wj.pojo.AdminRolePermission;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * @author Evan
 * @date 2019/11
 */
public interface AdminRolePermissionDao extends JpaRepository<AdminRolePermission, Integer> {

    /**
     * 根据角色主键查出对应所有权限
     * @param rid
     * @return
     */
    List<AdminRolePermission> findAllByRid(int rid);

    /**
     * 根据角色主键列表查出对应所有权限
     * @param rids
     * @return
     */
    List<AdminRolePermission> findAllByRid(List<Integer> rids);

    /**
     * 根据角色主键删除对应所有权限
     * @param rid
     * @return
     */
    void deleteAllByRid(int rid);
}
