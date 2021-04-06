package com.nia.wj.pojo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;


import javax.persistence.*;

/**
 * @program: wj
 * @description: 用户和角色对应关系类
 * @author: ganwei
 * @create: 2021-03-26 14:34
 **/
@Entity
@Table(name = "t_admin_user_role")
@JsonIgnoreProperties({"handler", "hibernateLazyInitializer"})
public class AdminUserRole {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    /**
     * User id.
     */
    private int uid;

    /**
     * Role id.
     */
    private int rid;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }

    public int getRid() {
        return rid;
    }

    public void setRid(int rid) {
        this.rid = rid;
    }
}
