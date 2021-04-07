package com.nia.wj.service;

import com.nia.wj.dao.AdminPermissionDao;
import com.nia.wj.dao.AdminRolePermissionDao;
import com.nia.wj.pojo.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @author Evan
 * @date 2019/11
 */
@Service
public class AdminPermissionService {
    @Autowired
    private AdminPermissionDao adminPermissionDao;
    @Autowired
    private AdminUserRoleService adminUserRoleService;
    @Autowired
    private AdminRoleService adminRoleService;
    @Autowired
    private AdminRolePermissionService adminRolePermissionService;
    @Autowired
    private AdminRolePermissionDao adminRolePermissionDao;
    @Autowired
    private UserService userService;

    public List<AdminPermission> list() {return adminPermissionDao.findAll();}

    /**
     * 判断前端请求是否为菜单权限的url开头，是就需要过滤
     * a certain API.
     * @param requestAPI API requested by client
     * @return true when requestAPI is found in the DB
     */
    public boolean needFilter(String requestAPI) {

        List<AdminPermission> ps = adminPermissionDao.findAll();
        for (AdminPermission p: ps) {
            // match prefix
            if (requestAPI.startsWith(p.getUrl())) {
                return true;
            }
        }
        return false;
    }

    public List<AdminPermission> listPermsByRoleId(int rid) {
        List<Integer> pids = adminRolePermissionService.findAllByRid(rid)
                .stream().map(AdminRolePermission::getPid).collect(Collectors.toList());
        return adminPermissionDao.findAllById(pids);
    }

    /**
     * 根据用户名查出其对应的所有菜单的url（去重后的）
     * @param loginName
     * @return
     */
    public Set<String> listPermissionURLsByUser(String loginName) {

        //查出当前用户所有角色，将主键放到list中
        List<Integer> rids = adminRoleService.listRolesByUser(loginName)
                .stream().map(AdminRole::getId).collect(Collectors.toList());

        //根据角色主键查询对应所有的菜单权限主键，并将主键放到list中
        List<Integer> pids = adminRolePermissionDao.findAllByRid(rids)
                .stream().map(AdminRolePermission::getPid).collect(Collectors.toList());

        //根据角色主键查询对应所有的菜单权限
        List<AdminPermission> perms = adminPermissionDao.findAllById(pids);
        //将所有的菜单权限对应的url去重（不同的角色可能有相同的菜单权限）
        Set<String> URLs = perms.stream().map(AdminPermission::getUrl).collect(Collectors.toSet());

        return URLs;
    }
}
