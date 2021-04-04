package com.nia.wj.pojo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * @ClassName Category
 * @Description TODO
 * @Author ganwei
 * @Date 2021/4/4 23:28
 * @VERSION 1.0
 **/

@Entity
@Table(name = "t_category")
@JsonIgnoreProperties({ "handler","hibernateLazyInitializer" })
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    int id;

    String name;

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
}
