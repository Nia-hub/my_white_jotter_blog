package com.nia.wj.pojo;

/**
 * @program: wj
 * @description: 响应结果类
 * @author: ganwei
 * @create: 2021-03-26 14:36
 **/
public class RetObj {

    /**
     * 响应码
     */
    private Integer code;

    public RetObj(int code) {
        this.code = code;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }
}
