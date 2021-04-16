package com.nia.wj.service;

import com.nia.wj.dao.AdminRoleMenuDao;
import com.nia.wj.pojo.AdminRole;
import com.nia.wj.pojo.AdminRoleMenu;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @ClassName AdminRoleMenuService
 * @Description 角色菜单关系服务类
 * @Author ganwei
 * @Date 2021/4/6 23:46
 * @VERSION 1.0
 **/
@Service
public class AdminRoleMenuService {

    @Autowired
    private AdminRoleMenuDao adminRoleMenuDao;

    public List<AdminRoleMenu> findAllByRid(int rid) {
        return adminRoleMenuDao.findAllByRid(rid);
    }

    public List<AdminRoleMenu> findAllByRid(List<Integer> rids) {
        return adminRoleMenuDao.findAllByRid(rids);
    }

    public void save(AdminRoleMenu rm) {
        adminRoleMenuDao.save(rm);
    }

    @Modifying
    @Transactional
    public void updateRoleMenu(int rid, Map<String, List<Integer>> menusIds) {
        adminRoleMenuDao.deleteAllByRid(rid);
        List<AdminRoleMenu> rms = new ArrayList<>();
        for (Integer mid : menusIds.get("menusIds")) {
            AdminRoleMenu rm = new AdminRoleMenu();
            rm.setMid(mid);
            rm.setRid(rid);
            rms.add(rm);
        }

        adminRoleMenuDao.saveAll(rms);
    }
}
