package com.shp.dev.network.service.impl;

import com.shp.dev.network.common.config.SecurityUserDetails;
import com.shp.dev.network.service.IJwtUserDetailsService;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @CreateBy: shp
 * @Version: 1.0
 * @Description: TODO 权限认证业务
 * @CreateTime: 2020/6/30 22:18
 * @PackageName: com.shp.dev.network.service
 * @ProjectName: network
 */
@Service
public class JwtUserDetailsService implements IJwtUserDetailsService, UserDetailsService {

    @Override
    public UserDetails loadUserByUsername(String user) throws UsernameNotFoundException {
        System.out.println("当前用户为 : ----------------- " + user + " -----------------");

        //给该用户赋予角色
        List<GrantedAuthority> roles = new ArrayList<>();
        roles.add(new SimpleGrantedAuthority("ROLE_USER"));
        roles.add(new SimpleGrantedAuthority("user"));
        roles.add(new SimpleGrantedAuthority("admin"));

        System.out.print("当前用户的角色有：------------");
        for (GrantedAuthority role : roles) {
            System.out.print("   --   " + role.getAuthority());
        }
        System.out.println("  ------------");

        return new SecurityUserDetails(user, roles);
    }
}