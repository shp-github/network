package com.shp.dev.network.common.config;

import com.shp.dev.network.login.model.SysUser;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.Collection;

/**
 * @CreateBy: shp
 * @Version: 1.0
 * @Description: TODO Security的详细配置
 * @CreateTime: 2020/6/30 22:17
 * @PackageName: com.shp.dev.network.common.config
 * @ProjectName: network
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class SecurityUserDetails extends SysUser implements UserDetails {

    private Collection<? extends GrantedAuthority> authorities;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    public SecurityUserDetails(String userName, Collection<? extends GrantedAuthority> authorities){
        this.authorities = authorities;
        this.setUsername(userName);
        String encode = new BCryptPasswordEncoder().encode("123456");
        this.setPassword(encode);
        this.setAuthorities(authorities);
    }

    /**
     * 账户是否过期
     * @return
     */
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    /**
     * 是否禁用
     * @return
     */
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    /**
     * 密码是否过期
     * @return
     */
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    /**
     * 是否启用
     * @return
     */
    @Override
    public boolean isEnabled() {
        return true;
    }
}
