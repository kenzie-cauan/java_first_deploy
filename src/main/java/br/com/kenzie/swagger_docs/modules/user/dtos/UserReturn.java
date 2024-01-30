package br.com.kenzie.swagger_docs.modules.user.dtos;

import java.util.List;

import org.springframework.security.core.GrantedAuthority;

import lombok.Data;

@Data
public class UserReturn {
    private Long id;
    private String username;
    private Boolean admin;
    private Boolean accountNonExpired;
    private Boolean accountNonLocked;
    private Boolean credentialsNonExpired;
    private Boolean enabled;
    private List<GrantedAuthority> authorities;
}
