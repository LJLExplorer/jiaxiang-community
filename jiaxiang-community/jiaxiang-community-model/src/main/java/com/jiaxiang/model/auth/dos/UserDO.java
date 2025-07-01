package com.jiaxiang.model.auth.dos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDO {

    private Long id;

    private String userName;

    /**
     * 密码
     */
    private String password;

    /**
     * 真实姓名
     */
    private String realName;

    /**
     * 性别（0女 1男）
     */
    private Integer sex;

    /**
     * 邮箱
     */
    private String email;

    /**
     * 联系方式
     */
    private String phone;

    /**
     * 部门id
     */
    private Long deptId;

    /**
     * 是否启用
     */
    private Boolean enabled;

    /**
     * 是否过期（0.未过期 1.过期）
     */
    private Boolean accountExpired;

    /**
     * 凭证是否过期(0 未过期 1 过期)
     */
    private Boolean credentialsExpired;

    /**
     * 是否锁定(0. 未锁定 1.锁定)
     */
    private Boolean accountLocked;

    /**
     * 创建时间
     */
    private LocalDateTime createTime;

    /**
     * 更新时间
     */
    private LocalDateTime updateTime;
}