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

    private String msg;

    private Object obj;

    public RetObj(int code) {
        this.code = code;
    }

    public RetObj(Integer code, String msg, Object obj) {
        this.code = code;
        this.msg = msg;
        this.obj = obj;
    }
    public RetObj(Integer code, String msg) {
        super();
        this.code = code;
        this.msg = msg;
    }
    public RetObj(String msg) {
        super();
        this.msg = msg;
    }
    public RetObj(Object obj) {
        super();
        this.obj = obj;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Object getObj() {
        return obj;
    }

    public void setObj(Object obj) {
        this.obj = obj;
    }
}
