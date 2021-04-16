package com.nia.wj.dao;

import com.nia.wj.pojo.AdminRole;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author Evan
 * @date 2019/11
 */
public interface AdminRoleDao extends JpaRepository<AdminRole, Integer> {
    AdminRole findById(int id);
}
