package com.lby.template.entity;

import com.lby.template.enums.RoleEnum;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "user")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SystemUser extends BaseEntity<Long> implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "username",nullable = false)
    private String username;

    @Column(name = "password",nullable = false)
    private String password;

    @ColumnDefault("1")
    @Column(name = "is_normal",nullable = false)
    private boolean isNormal;

    @Enumerated(EnumType.STRING)
    @Column(name = "role",nullable = false)
    private RoleEnum role;

    @Column(name = "avatar_url")
    private String avatarUrl;

    @Column(name = "real_name",nullable = false)
    private String realName;

    
}
