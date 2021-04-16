package com.nia.wj.service;

import com.nia.wj.dao.AdminUserRoleDao;
import com.nia.wj.pojo.AdminRole;
import com.nia.wj.pojo.AdminUserRole;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName BookService
 * @Description 用户和角色关系服务类
 * @Author ganwei
 * @Date 2021/4/4 23:46
 * @VERSION 1.0
 **/
@Service
public class AdminUserRoleService {
    @Autowired
    private AdminUserRoleDao adminUserRoleDao;

    /**
     * 根据用户主键查询其用有的角色
     * @param uid
     * @return
     */
    public List<AdminUserRole> listAllByUid(int uid) {
        return adminUserRoleDao.findAllByUid(uid);
    }

//    @Modifying
    @Transactional
    public void saveRoleChanges(int uid, List<AdminRole> roles) {
        adminUserRoleDao.deleteAllByUid(uid);
        List<AdminUserRole> urs = new ArrayList<>();
        roles.forEach(r -> {
            AdminUserRole ur = new AdminUserRole();
            ur.setUid(uid);
            ur.setRid(r.getId());
            urs.add(ur);
        });
        adminUserRoleDao.saveAll(urs);
    }
}
