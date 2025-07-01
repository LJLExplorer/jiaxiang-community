package com.jiaxiang.auth.service.impl;

import cn.hutool.core.util.ObjectUtil;
import cn.hutool.json.JSONUtil;
import com.jiaxiang.auth.mapper.AuthMapper;
import com.jiaxiang.model.auth.dos.UserDO;
import com.jiaxiang.model.auth.dtos.SecurityUserDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    private final AuthMapper authMapper;

    public UserDetailsServiceImpl(AuthMapper authMapper) {
        this.authMapper = authMapper;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserDO userDO = authMapper.findByUserName(username);
        if (ObjectUtil.isEmpty(userDO)) {
            log.error("用户名错误: {}", username);
            throw new UsernameNotFoundException("用户名不正确");
        }
        List<String> permissions = authMapper.findPermissionsByUserId(userDO.getId());

        log.info(JSONUtil.toJsonStr(new SecurityUserDTO(userDO, permissions)));
        return new SecurityUserDTO(userDO, permissions);
    }
}
