package com.lby.template.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

/**
 * Author: laishao
 * Date: 2022/5/11
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserInfoVo implements Serializable {

    /**
     * 用户ID
     */
    private Integer id;

    /**
     * 用户名
     */
    private String nickName;

    /**
     * 头像地址
     */
    private String avatarUrl;

    /**
     * 权限
     */
    private List roles;
}
