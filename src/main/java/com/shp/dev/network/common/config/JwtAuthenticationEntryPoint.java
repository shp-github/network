package com.shp.dev.network.common.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @CreateBy: shp
 * @Version: 1.0
 * @Description: TODO jwt相关类
 * @CreateTime: 2020/6/30 22:26
 * @PackageName: com.shp.dev.network.common.config
 * @ProjectName: network
 */
@Component
@Slf4j
public class JwtAuthenticationEntryPoint implements AuthenticationEntryPoint {
    @Override
    public void commence(HttpServletRequest request,
                         HttpServletResponse response,
                         AuthenticationException authException)
            throws IOException, ServletException {
        response.sendError(HttpServletResponse.SC_UNAUTHORIZED,"没有凭证");
        log.info(authException.getMessage());
    }
}