package com.nia.wj.result;


/**
 * @program: wj
 * @description: 响应结果类
 * @author: ganwei
 * @create: 2021-03-26 14:36
 **/
public class Result {

    /**
     * 响应码
     */
    private Integer code;

    private String msg;

    private Object obj;

    public Result(int code) {
        this.code = code;
    }

    public Result(Integer code, String msg, Object obj) {
        this.code = code;
        this.msg = msg;
        this.obj = obj;
    }
    public Result(Integer code, String msg) {
        super();
        this.code = code;
        this.msg = msg;
    }
    public Result(String msg) {
        super();
        this.msg = msg;
    }
    public Result(Object obj) {
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
