package com.lby.template.enums;

import lombok.Getter;
import lombok.ToString;

/**
 * Author: laishao
 * Date: 2022/5/11
 */
@Getter
@ToString
public enum RoleEnum {


    ADMIN("ADMIN"),
    USER("USER")

    ;

    private String name;

    RoleEnum(String name) {
        this.name = name;
    }
}
