package com.nia.wj.result;

/**
 * @program: wj
 * @description: 响应码枚举
 * @author: ganwei
 * @create: 2021-03-26 14:36
 **/
public enum ResultCode {
    SUCCESS(200),
    FAIL(400),
    UNAUTHORIZED(401),
    NOT_FOUND(404),
    INTERNAL_SERVER_ERROR(500);

    public int code;

    ResultCode(int code) {
        this.code = code;
    }
}
