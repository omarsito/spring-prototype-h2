package com.piolin.spring.prototype.security.pojo;

import com.piolin.spring.prototype.database.entity.APIUserInfo;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class UserInfoDetails implements UserDetails {

    private String username; // Changed from 'name' to 'email' for clarity
    private String pwd;
    private List<GrantedAuthority> authorities;

    public UserInfoDetails(APIUserInfo APIUserInfo){
        this.username = APIUserInfo.getEmail();
        this.pwd = APIUserInfo.getPwd();
        this.authorities =  Stream.of(APIUserInfo.getRoles().split(",")).map(SimpleGrantedAuthority::new)
                .collect(Collectors.toList());

    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities(){
        return authorities;
    }

    @Override
    public String getPassword() {
        return pwd;
    }

    @Override
    public String getUsername(){
        return username;
    }

    @Override
    public boolean isAccountNonExpired(){
        return true;
    }

    @Override
    public boolean isAccountNonLocked(){
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired(){
        return true;
    }

    @Override
    public boolean isEnabled(){
        return true;
    }

}