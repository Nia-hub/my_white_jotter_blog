package com.nia.wj.pojo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.util.List;

/**
 * @program: wj
 * @description: 菜单类
 * @author: ganwei
 * @create: 2021-03-26 14:34
 **/
@Entity
@Table(name = "t_admin_menu")
@JsonIgnoreProperties({"handler", "hibernateLazyInitializer"})
public class AdminMenu {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    int id;

    /**
     * vue路由的路径
     */
    String path;

    /**
     * vue中路由名字
     */
    String name;

    /**
     * 菜单中文名
     */
    String nameZh;

    /**
     * 图标
     */
    String iconCls;

    /**
     * 组件
     */
    String component;

    /**
     * 父菜单节点主键
     */
    int parentId;
    /**
     *子菜单  Transient需要属性，但是这个属性你又不希望给存到数据库中去，仅仅是做个临时变量
     */
    @Transient
    List<AdminMenu> children;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNameZh() {
        return nameZh;
    }

    public void setNameZh(String nameZh) {
        this.nameZh = nameZh;
    }

    public String getIconCls() {
        return iconCls;
    }

    public void setIconCls(String iconCls) {
        this.iconCls = iconCls;
    }

    public String getComponent() {
        return component;
    }

    public void setComponent(String component) {
        this.component = component;
    }

    public int getParentId() {
        return parentId;
    }

    public void setParentId(int parentId) {
        this.parentId = parentId;
    }

    public List<AdminMenu> getChildren() {
        return children;
    }

    public void setChildren(List<AdminMenu> children) {
        this.children = children;
    }
}