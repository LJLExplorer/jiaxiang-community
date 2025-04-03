package com.jiaxiang.community.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 登录类 接受登录用户
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class LoginDto {
    private String phone;
    private String password;
}
