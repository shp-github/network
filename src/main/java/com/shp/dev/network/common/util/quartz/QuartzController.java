package com.shp.dev.network.common.util.quartz;

import com.shp.dev.network.common.bean.ResultBean;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @CreateBy: shp
 * @Version: 1.0
 * @Description: TODO
 * @CreateTime: 2020/11/29 21:36
 * @PackageName: com.shp.dev.network.common
 * @ProjectName: network   /common/code
 */
@RestController
@RequestMapping("/quartz")
@CrossOrigin
public class QuartzController {

    @Autowired
    private QuartzUtil quartzUtil;

    @ApiOperation("添加任务")
    @RequestMapping(value = "/addQuartz", method = RequestMethod.POST)
    public ResultBean addQuartz(Quartz quartz) throws Exception {
        quartzUtil.addQuartz(quartz);
        return ResultBean.success();
    }

    @ApiOperation("暂停所有任务")
    @RequestMapping(value = "/pauseAllJob", method = RequestMethod.POST)
    public ResultBean pauseAllJob() throws Exception {
        quartzUtil.pauseAllJob();
        return ResultBean.success();
    }

    @ApiOperation("恢复所有任务")
    @RequestMapping(value = "/resumeAllJob", method = RequestMethod.POST)
    public ResultBean resumeAllJob() throws Exception {
        quartzUtil.resumeAllJob();
        return ResultBean.success();
    }

    @ApiOperation("获取任务状态")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "name", value = "名称", required = true, paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "group", value = "组", required = true, paramType = "query", dataType = "String")}
    )
    @RequestMapping(value = "/getJobState", method = RequestMethod.POST)
    public ResultBean getJobState(String name, String group) throws Exception {
        return ResultBean.success(quartzUtil.getJobState(name, group).equalsIgnoreCase("NORMAL")?"服务正常！！!":"服务异常！！！");
    }

    @ApiOperation("暂停任务")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "name", value = "名称", required = true, paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "group", value = "组", required = true, paramType = "query", dataType = "String")}
    )
    @RequestMapping(value = "/pauseJob", method = RequestMethod.POST)
    public ResultBean pauseJob(String name, String group) throws Exception {
        return ResultBean.success(quartzUtil.pauseJob(name, group));
    }
    @ApiOperation("恢复任务")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "name", value = "名称", required = true, paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "group", value = "组", required = true, paramType = "query", dataType = "String")}
    )
    @RequestMapping(value = "/resumeJob", method = RequestMethod.POST)
    public ResultBean resumeJob(String name, String group) throws Exception {
        return ResultBean.success(quartzUtil.resumeJob(name, group));
    }

    @ApiOperation("删除任务")
    @RequestMapping(value = "/deleteJob", method = RequestMethod.POST)
    public ResultBean deleteJob(Quartz quartz) throws Exception {
        return ResultBean.success(quartzUtil.deleteJob(quartz));
    }

    @ApiOperation("修改任务")
    @RequestMapping(value = "/modifyJob", method = RequestMethod.POST)
    public ResultBean modifyJob(Quartz quartz) throws Exception {
        return ResultBean.success(quartzUtil.modifyJob(quartz));
    }


}
