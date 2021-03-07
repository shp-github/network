package com.shp.dev.network.common.controller;

import com.shp.dev.network.common.bean.ResultBean;
import com.shp.dev.network.common.service.ICommonService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

/**
 * @CreateBy: shp
 * @Version: 1.0
 * @Description: TODO 服务控制
 * @CreateTime: 2020/12/15 19:30
 * @PackageName: com.shp.dev.network.common.controller
 * @ProjectName: network
 */
@RestController
@RequestMapping("/service")
@ApiOperation("更新服务")
public class ServiceController {

    @Autowired
    private ICommonService commonService;


    @ApiOperation("更新服务旧版")
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public ResultBean update(MultipartFile file) {
        return commonService.update(file);
    }

    @ApiOperation("更新服务新版")
    @RequestMapping(value = "/updateService", method = RequestMethod.POST)
    public ResultBean updateService(MultipartFile file) {
        return commonService.updateService(file);
    }

    @ApiOperation("重启服务")
    @RequestMapping(value = "/restart", method = RequestMethod.POST)
    public ResultBean restart() {
        return commonService.restart();
    }

    @ApiOperation("测试热更新")
    @RequestMapping(value = "/testUpdate", method = RequestMethod.POST)
    public ResultBean testUpdate() {
        return ResultBean.success("bbbbbbbbbb");
    }

    @ApiOperation("热更新 服务重启就会恢复之间的状态")
    @RequestMapping(value = "/hotUpdate", method = RequestMethod.POST)
    public ResultBean hotUpdate(MultipartFile file) {
        return commonService.hotUpdate(file);
    }



}
