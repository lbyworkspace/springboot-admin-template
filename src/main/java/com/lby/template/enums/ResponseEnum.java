package com.lby.template.enums;

import lombok.Getter;
import lombok.ToString;

/**
 * Author: laishao
 * Date: 2022/5/11
 */
@Getter
@ToString
public enum ResponseEnum {

    OK(0,"请求成功"),

    ACCESS_TOKEN_INVALID(1001,"令牌无效或者已过期"),
    UNAUTHORIZED(1002,"该请求需要身份验证"),
    LOGGED_IN(1003,"当前账号已被登录"),

    ;


    private Integer code;

    private String msg;

    ResponseEnum(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }
}
