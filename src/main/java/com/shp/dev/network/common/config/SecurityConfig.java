package com.shp.dev.network.common.config;//package com.shp.dev.network.common.config;


import com.shp.dev.network.login.service.JwtUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

/**
 * @CreateBy: shp
 * @Version: 1.0
 * @Description: TODO Security配置类
 * @CreateTime: 2020/6/25 12:27
 * @PackageName: com.shp.dev.network.common.config
 * @ProjectName: network
 */

@Configuration
@EnableWebSecurity// 这个注解必须加，开启Security
@EnableGlobalMethodSecurity(prePostEnabled = true)//保证post之前的注解可以使用
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint;

    @Autowired
    private JwtUserDetailsService jwtUserDetailsService;

    @Autowired
    private JwtAuthorizationTokenFilter authenticationTokenFilter;

    //先来这里认证一下
    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(jwtUserDetailsService).passwordEncoder(passwordEncoderBean());
    }

    //拦截在这配
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .exceptionHandling().authenticationEntryPoint(jwtAuthenticationEntryPoint)
                .and()
                .authorizeRequests()


                //释放所有接口权限，方便测试
                .antMatchers("/**/**").permitAll()


                //排除登录接口
                .antMatchers("/login").permitAll()
                //排除向页面输出信息
                .antMatchers("/write/**").permitAll()
                //排除swagger
                .antMatchers("/swagger-resources/**").permitAll()
                .antMatchers("/webjars/**").permitAll()
                .antMatchers("/swagger-ui.html/**").permitAll()
                .antMatchers("/v2/**").permitAll()
                .antMatchers("/gx.swagger-ui/**").permitAll()
                //file
                .antMatchers("/file/**").permitAll()
                .antMatchers(HttpMethod.OPTIONS, "/**").anonymous()
                .anyRequest().authenticated()       // 剩下所有的验证都需要验证
                .and()
                .csrf().disable();
        // 禁用 Spring Security 自带的跨域处理
        //.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
        // 定制我们自己的 session 策略：调整为让 Spring Security 不创建和使用 session
        http.addFilterBefore(authenticationTokenFilter, UsernamePasswordAuthenticationFilter.class);

    }

    //配置静态资源
    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring();
    }

    @Bean
    public PasswordEncoder passwordEncoderBean() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

}

