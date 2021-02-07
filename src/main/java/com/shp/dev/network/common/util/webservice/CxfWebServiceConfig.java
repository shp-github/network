package com.shp.dev.network.common.util.webservice;


import org.apache.cxf.Bus;
import org.apache.cxf.jaxws.EndpointImpl;
import org.apache.cxf.transport.servlet.CXFServlet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.xml.ws.Endpoint;

/**
 * @CreateBy: shp
 * @Version: 1.0
 * @Description: TODO 配置webservice
 * @CreateTime: 2020/10/31 9:44
 * @PackageName: com.shp.dev.network.common.util.webservice
 * @ProjectName: network
 */
@Configuration
public class CxfWebServiceConfig {

    @Autowired
    private Bus bus;
    @Autowired
    private HelloWebService helloWebService;

    @Bean("cxfServletRegistration")
    public ServletRegistrationBean dispatcherServlet() {
        //注册servlet 拦截/ws 开头的请求 不设置 默认为：/services/*
        return new ServletRegistrationBean(new CXFServlet(), "/ws/*");
    }

    /*
     * 发布endpoint
     */
    @Bean
    public Endpoint endpoint() {
        EndpointImpl endpoint = new EndpointImpl(bus, helloWebService);
        endpoint.publish("/helloWebService");//发布地址
        return endpoint;
    }
}
