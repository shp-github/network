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
 * @Description: TODO 更新接口专用
 * @CreateTime: 2020/12/15 19:30
 * @PackageName: com.shp.dev.network.common.controller
 * @ProjectName: network
 */
@RestController
@RequestMapping("/update")
public class AController {
    @Autowired
    private ICommonService commonService;

    @ApiOperation("更新接口服务")
    @RequestMapping(value = "/updateService", method = RequestMethod.POST)
    public ResultBean updateService(MultipartFile file) {
        return commonService.updateService(file);
    }


}
