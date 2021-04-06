package com.nia.wj.service;

import com.nia.wj.dao.AdminMenuDao;
import com.nia.wj.dao.BookDao;
import com.nia.wj.pojo.*;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @ClassName AdminMenuService
 * @Description 菜单服务类
 * @Author ganwei
 * @Date 2021/4/6 23:46
 * @VERSION 1.0
 **/
@Service
public class AdminMenuService {

    @Autowired
    private UserService userService;
    @Autowired
    private AdminMenuDao adminMenuDao;
    @Autowired
    private AdminUserRoleService adminUserRoleService;
    @Autowired
    private AdminRoleMenuService adminRoleMenuService;

    /**
     * 查询当前用户的所有菜单
     * @return
     */
    public List<AdminMenu> selectMenusByCurrentUser(){

        String loginName = SecurityUtils.getSubject().getPrincipal().toString();
        User currentUser = userService.getByLoinName(loginName);

        // 获得当前用户对应的所有角色的 id 列表
        List<Integer> rids = adminUserRoleService.listAllByUid(currentUser.getId())
                .stream().map(AdminUserRole::getRid).collect(Collectors.toList());

        // 查询出这些角色对应的所有菜单项
        List<Integer> menuIds = adminRoleMenuService.findAllByRid(rids)
                .stream().map(AdminRoleMenu::getMid).collect(Collectors.toList());
        List<AdminMenu> menus = adminMenuDao.findAllById(menuIds).stream().distinct().collect(Collectors.toList());

        // 处理菜单项的结构
        handleMenus(menus);
        return menus;
    }

    /**
     * Adjust the Structure of the menu.
     *
     * @param menus Menu items list without structure
     */
    public void handleMenus(List<AdminMenu> menus) {
        menus.forEach(m -> {
            List<AdminMenu> children = getAllByParentId(m.getId());
            m.setChildren(children);
        });

        menus.removeIf(m -> m.getParentId() != 0);
    }

    public List<AdminMenu> getAllByParentId(int parentId) {
        return adminMenuDao.findAllByParentId(parentId);
    }

    public List<AdminMenu> getMenusByRoleId(int rid) {
        List<Integer> menuIds = adminRoleMenuService.findAllByRid(rid)
                .stream().map(AdminRoleMenu::getMid).collect(Collectors.toList());
        List<AdminMenu> menus = adminMenuDao.findAllById(menuIds);

        handleMenus(menus);
        return menus;
    }
}
