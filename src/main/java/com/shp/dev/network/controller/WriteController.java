package com.shp.dev.network.controller;

import com.shp.dev.network.common.bean.ResultBean;
import com.shp.dev.network.common.bean.Tablepar;
import com.shp.dev.network.model.SysWrite;
import com.shp.dev.network.service.IWriteService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;

/**
 * @CreateBy: shp
 * @Version: 1.0
 * @Description: TODO 向页面输出网页信息
 * @CreateTime: 2020/7/25 22:50
 * @PackageName: com.shp.dev.network.controller
 * @ProjectName: network
 */

@RestController
@Api(tags = "向页面输出网页信息")
@RequestMapping("/write")
public class WriteController {

    @Autowired
    private IWriteService service;

    @RequestMapping(value = "/getWrite", method = {RequestMethod.GET})
    @ApiOperation("根据条件向页面输出信息")
    public void getWrite(HttpServletResponse res, SysWrite model) {
        service.getWrite(res, model);
    }

    @RequestMapping(value = "/getWriteList", method = {RequestMethod.POST})
    @ApiOperation("获取输出信息列表")
    public ResultBean getWriteList(SysWrite model, Tablepar tablepar) {
        return service.getWriteList(model, tablepar);
    }

    @RequestMapping(value = "/delWrite", method = {RequestMethod.POST})
    @ApiOperation("删除输出信息")
    public ResultBean delWrite(SysWrite model) {
        return service.delWrite(model);
    }

    @RequestMapping(value = "/writeSave", method = {RequestMethod.POST})
    @ApiOperation("保存输出信息")
    public ResultBean writeSave(SysWrite model) {
        return service.insert(model);
    }

}
