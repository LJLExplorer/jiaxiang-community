package com.jiaxiang.auth.mapper;

import com.jiaxiang.model.auth.dos.UserDO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface AuthMapper {

    @Select("select * from community_springcloud.user where user_name = #{username}")
    UserDO findByUserName(String username);

    @Select("select DISTINCT m.perms from menu as m, role_menu as rm, " +
            "role_user as ru, role as r, user as u where u.id = #{id} and u.id = ru.user_id" +
            " and ru.role_id = r.id and rm.role_id = r.id and rm.menu_id = m.id " +
            "and m.status = 1 and m.perms != ''")
    List<String> findPermissionsByUserId(Long id);
}
