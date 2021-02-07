package com.shp.dev.network.common.util.webservice;


import org.springframework.context.annotation.Configuration;

import javax.jws.WebParam;
import javax.jws.WebService;

/**
 * @CreateBy: shp
 * @Version: 1.0
 * @Description: TODO 实现webservice服务
 * @CreateTime: 2020/10/31 9:43
 * @PackageName: com.shp.dev.network.common.util.webservice
 * @ProjectName: network
 */
@WebService(
        targetNamespace = "webservice.util.common.network.dev.shp.com", //wsdl命名空间
        serviceName = "HelloWebService",                 //portType名称 客户端生成代码时 为接口名称
        endpointInterface = "com.shp.dev.network.common.util.webservice.HelloWebService")//指定发布webservcie的接口类，此类也需要接入@WebService注解
@Configuration
public class HelloWebServiceImpl implements HelloWebService{

    @Override
    public String Hello(@WebParam(name="name") String name) {
        System.out.println("欢迎你"+name);
        return "欢迎你"+name;
    }
}
