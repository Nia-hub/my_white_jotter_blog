package com.nia.wj.service;

import com.nia.wj.dao.AdminRoleDao;
import com.nia.wj.pojo.AdminMenu;
import com.nia.wj.pojo.AdminPermission;
import com.nia.wj.pojo.AdminRole;
import com.nia.wj.pojo.AdminUserRole;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Evan
 * @date 2019/11
 */
@Service
public class AdminRoleService {
    @Autowired
    private AdminRoleDao adminRoleDao;
    @Autowired
    private UserService userService;
    @Autowired
    private AdminUserRoleService adminUserRoleService;
    @Autowired
    private AdminPermissionService adminPermissionService;
    @Autowired
    private AdminRolePermissionService adminRolePermissionService;
    @Autowired
    private AdminMenuService adminMenuService;

    public List<AdminRole> listWithPermsAndMenus() {
        List<AdminRole> roles = adminRoleDao.findAll();
        List<AdminPermission> perms;
        List<AdminMenu> menus;
        for (AdminRole role : roles) {
            perms = adminPermissionService.listPermsByRoleId(role.getId());
            menus = adminMenuService.getMenusByRoleId(role.getId());
            role.setPerms(perms);
            role.setMenus(menus);
        }
        return roles;
    }

    public List<AdminRole> findAll() {
        return adminRoleDao.findAll();
    }


    public void addOrUpdate(AdminRole adminRole) {
        adminRoleDao.save(adminRole);
    }

    /**
     * 根据用户登录名查出其所有角色
     * @param loginName
     * @return 所有对应角色
     */
    public List<AdminRole> listRolesByUser(String loginName) {

        //查出当前用户的主键
        int uid = userService.getByLoinName(loginName).getId();
        //查出这个用户的所有角色，放到list中
        List<Integer> rids = adminUserRoleService.listAllByUid(uid)
                .stream().map(AdminUserRole::getRid).collect(Collectors.toList());

        //根据角色id查出对应角色
        return adminRoleDao.findAllById(rids);
    }

    public AdminRole updateRoleStatus(AdminRole role) {
        AdminRole roleInDB = adminRoleDao.findById(role.getId());
        roleInDB.setEnabled(role.isEnabled());
        return adminRoleDao.save(roleInDB);
    }

    public void editRole(@RequestBody AdminRole role) {
        adminRoleDao.save(role);
        adminRolePermissionService.savePermChanges(role.getId(), role.getPerms());
    }
}
