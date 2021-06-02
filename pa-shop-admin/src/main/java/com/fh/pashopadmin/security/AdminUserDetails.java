package com.fh.pashopadmin.security;

import com.fh.pashopadmin.entity.UmsAdmin;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;


public class AdminUserDetails implements UserDetails {

    private List<String>  userPermissionKey;
    private List<String>  userRoleKey;
    private UmsAdmin umsAdmin;


   public AdminUserDetails( List<String> userPermissionKey,List<String>   userRoleKey,UmsAdmin   umsAdmin){
        this.umsAdmin=umsAdmin;
        this.userPermissionKey =userPermissionKey;
        this.userRoleKey = userRoleKey;
   }

    public AdminUserDetails(){
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
       List<SimpleGrantedAuthority> list = new ArrayList<>();
        userPermissionKey.forEach(p->{
            list.add(new SimpleGrantedAuthority(p));
        });
        userRoleKey.forEach(r->{
            list.add(new SimpleGrantedAuthority(r));
        });

        return list;
    }

    @Override
    public String getPassword() {
        return umsAdmin.getPassword();
    }

    @Override
    public String getUsername() {
        return umsAdmin.getUsername();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return umsAdmin.getStatus().equals(1);
    }

    public List<String> getUserRoleKey() {
        return userRoleKey;
    }

    public void setUserRoleKey(List<String> userRoleKey) {
        this.userRoleKey = userRoleKey;
    }

    public UmsAdmin getUmsAdmin() {
        return umsAdmin;
    }

    public void setUmsAdmin(UmsAdmin umsAdmin) {
        this.umsAdmin = umsAdmin;
    }

    public List<String> getUserPermissionKey() {
        return userPermissionKey;
    }

    public void setUserPermissionKey(List<String> userPermissionKey) {
        this.userPermissionKey = userPermissionKey;
    }
}
