package com.shp.dev.network.common.util.webservice;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;

/**
 * @CreateBy: shp
 * @Version: 1.0
 * @Description: TODO  创建服务接口
 * @CreateTime: 2020/10/31 9:42
 * @PackageName: com.shp.dev.network.common.util.webservice
 * @ProjectName: network
 */
@WebService(name = "HelloWebService")
public interface HelloWebService {
    @WebMethod
    public String Hello(@WebParam(name="name") String name);
}
