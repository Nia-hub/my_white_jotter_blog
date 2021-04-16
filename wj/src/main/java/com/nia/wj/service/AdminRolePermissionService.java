package com.nia.wj.service;

import com.nia.wj.dao.AdminRolePermissionDao;
import com.nia.wj.pojo.AdminPermission;
import com.nia.wj.pojo.AdminRolePermission;
import com.nia.wj.dao.AdminRolePermissionDao;
import com.nia.wj.pojo.AdminPermission;
import com.nia.wj.pojo.AdminRolePermission;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Evan
 * @date 2019/11
 */
@Service
public class AdminRolePermissionService {
    @Autowired
    private AdminRolePermissionDao adminRolePermissionDao;

    List<AdminRolePermission> findAllByRid(int rid) {
        return adminRolePermissionDao.findAllByRid(rid);
    }

//    @Modifying
    @Transactional
    public void savePermChanges(int rid, List<AdminPermission> perms) {
        adminRolePermissionDao.deleteAllByRid(rid);
        List<AdminRolePermission> rps = new ArrayList<>();
        perms.forEach(p -> {
            AdminRolePermission rp = new AdminRolePermission();
            rp.setRid(rid);
            rp.setPid(p.getId());
            rps.add(rp);
        });
        adminRolePermissionDao.saveAll(rps);
    }
}
