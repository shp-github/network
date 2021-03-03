package com.shp.dev.network.service;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

/**
 * @CreateBy: shp
 * @Version: 1.0
 * @Description: TODO 权限认证业务
 * @CreateTime: 2020/6/30 22:18
 * @PackageName: com.shp.dev.network.service
 * @ProjectName: network
 */
public interface IJwtUserDetailsService {
    UserDetails loadUserByUsername(String user) throws UsernameNotFoundException;
}