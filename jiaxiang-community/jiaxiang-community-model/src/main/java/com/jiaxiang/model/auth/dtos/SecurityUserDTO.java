package com.jiaxiang.model.auth.dtos;

import com.jiaxiang.model.auth.dos.UserDO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SecurityUserDTO implements UserDetails {

    @Getter
    private UserDO userDO;
    private List<String> permissions;


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return permissions.stream()
                .map(SimpleGrantedAuthority::new
                ).collect(Collectors.toList());
    }

    @Override
    public String getPassword() {
        return userDO.getPassword();
    }

    @Override
    public String getUsername() {
        return userDO.getUserName();
    }

    @Override
    public boolean isAccountNonExpired() {
        return !userDO.getAccountExpired();
    }

    @Override
    public boolean isAccountNonLocked() {
        return !userDO.getAccountLocked();
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return !userDO.getCredentialsExpired();
    }

    @Override
    public boolean isEnabled() {
        return userDO.getEnabled();
    }
}
