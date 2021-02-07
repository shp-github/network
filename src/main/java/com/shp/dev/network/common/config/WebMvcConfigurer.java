package com.shp.dev.network.common.config;


import cn.hutool.json.JSONUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @CreateBy: shp
 * @Version: 1.0
 * @Description: TODO
 * @CreateTime: 2021/1/16 22:16
 * @PackageName: com.shp.dev.network.common.config
 * @ProjectName: network
 */
@Configuration
@Slf4j
public class WebMvcConfigurer implements org.springframework.web.servlet.config.annotation.WebMvcConfigurer {


    @Override
    public void addInterceptors(InterceptorRegistry registry) {

        registry.addInterceptor(new HandlerInterceptorAdapter() {
            @Override
            public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

                log.info("[请求地址:{}]", request.getRequestURI());
                log.info("[请求方式:{}]", request.getMethod());
                log.info("[请求参数:{}]", JSONUtil.toJsonStr(request.getParameterMap()));
                log.info("[请求 ip:{}]", request.getRemoteAddr());

                return true;
            }
        });


    }

}