package com.shp.dev.network.menu.controller;

import com.shp.dev.network.common.bean.ResultBean;
import com.shp.dev.network.menu.service.SysMenuService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @CreateBy: shp
 * @Version: 1.0
 * @Description: TODO 左侧导航栏接口
 * @CreateTime: 2020-07-06 10:28
 * @PackageName: com.shp.dev.network.sys.controller
 * @ProjectName: network
 */

@RestController
@Api(tags = "左侧导航栏接口")
public class SysMenuController {

    @Autowired
    private SysMenuService service;

    @RequestMapping(value = "/menu",method = {RequestMethod.POST, RequestMethod.GET})
    public ResultBean queryAllMenu(){
        return service.queryAllMenu();
    }

}
