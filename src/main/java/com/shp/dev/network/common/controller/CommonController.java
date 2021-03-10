package com.shp.dev.network.common.controller;

import com.shp.dev.network.common.bean.ResultBean;
import com.shp.dev.network.common.service.ICommonService;
import com.shp.dev.network.common.util.file.CommonFileUtils;
import com.shp.dev.network.common.util.quartz.QuartzUtil;
import com.shp.dev.network.common.util.sql.model.SysSql;
import com.shp.dev.network.common.util.sql.service.ISysSqlService;
import com.shp.dev.network.common.util.validation.Name;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.util.List;
import java.util.Map;

/**
 * @CreateBy: shp
 * @Version: 1.0
 * @Description: TODO
 * @CreateTime: 2020/11/29 21:36
 * @PackageName: com.shp.dev.network.common
 * @ProjectName: network   /common/code
 */
@RestController
@RequestMapping("/common")
@CrossOrigin
public class CommonController {

    @Autowired
    private ICommonService commonService;
    @Autowired
    private ISysSqlService service;
    @Autowired
    private QuartzUtil quartzUtil;

    @RequestMapping(value = "/opsForValueSet", method = {RequestMethod.POST})
    @ApiOperation("插入redis值单个值")
    public ResultBean opsForValueSet(String key, String value, Integer db) {
        return commonService.opsForValueSet(key, value, db);
    }

    @RequestMapping(value = "/opsForValueGet", method = {RequestMethod.POST})
    @ApiOperation("获取redis值单个值")
    public ResultBean opsForValueGet(String key, Integer db) {
        return commonService.opsForValueGet(key, db);
    }

    @RequestMapping(value = "/opsForListSet", method = {RequestMethod.POST})
    @ApiOperation("插入redis值List格式")
    public ResultBean opsForListSet(String key, List list, Integer db) {
        return commonService.opsForListSet(key, list, db);
    }

    @RequestMapping(value = "/opsForListGet", method = {RequestMethod.POST})
    @ApiOperation("获取redis值List格式")
    public ResultBean opsForListGet(String key, Integer db) {
        return commonService.opsForListGet(key, db);
    }

    @RequestMapping(value = "/opsForHashSet", method = {RequestMethod.POST})
    @ApiOperation("插入redis值Hash格式")
    public ResultBean opsForHashSet(String key, Map map, Integer db) {
        return commonService.opsForHashSet(key, map, db);
    }

    @RequestMapping(value = "/opsForHashGet", method = {RequestMethod.POST})
    @ApiOperation("获取redis值Hash格式")
    public ResultBean opsForHashGet(String key, Integer db) {
        return commonService.opsForHashGet(key, db);
    }

    @ApiOperation("jpg图片转base64")
    @RequestMapping(value = "/toBase64", method = RequestMethod.POST)
    public ResultBean toBase64(@RequestParam(value = "file", required = false) MultipartFile file) {
        return commonService.toBase64(file);
    }

    @ApiOperation("文件转base64")
    @RequestMapping(value = "/file2base", method = RequestMethod.POST)
    public ResultBean file2base(@RequestParam(value = "file", required = false) MultipartFile file) {
        return commonService.file2base(file);
    }

    @ApiOperation("base64转文件")
    @RequestMapping(value = "/base2file", method = RequestMethod.POST)
    public void base2file(String base64,HttpServletResponse r) {
         commonService.base2file(base64,r);
    }


    @RequestMapping(value = "/select", method = {RequestMethod.POST})
    @ApiOperation("通用查询")
    public ResultBean select(SysSql parm) {
        return service.select(parm);
    }

    @RequestMapping(value = "/update", method = {RequestMethod.POST})
    @ApiOperation("通用增删改")
    public ResultBean update(SysSql parm) {
        return service.update(parm);
    }

    @RequestMapping(value = "/uploadFastdfs", method = {RequestMethod.POST})
    @ApiOperation("上传文件到fastdfs")
    public ResultBean uploadFile(MultipartFile file) {
        CommonFileUtils.isNull(file);
        return service.uploadFile(file);
    }

    @RequestMapping(value = "/uploadMinio", method = {RequestMethod.POST})
    @ApiOperation("上传文件到uploadMinio")
    public ResultBean uploadMinio(String fileName, String objectName) {
        return commonService.uploadMinio(fileName, objectName);
    }

    @ApiOperation("上传文件")
    @RequestMapping(value = "/upload", method = RequestMethod.POST)
    public ResultBean upload(MultipartFile file, String fileName, String frist, String last) {
        return commonService.upload(file, fileName, frist, last);
    }

    //测试变量校验验证
    @PostMapping("/name")
    public static Name name(@Valid @RequestBody Name name){
        return name;
    }

    //测试全局异常处理
    @RequestMapping(value = "/test", method = RequestMethod.GET)
    public ResultBean test() {
        int i = 1 / 0;
        return ResultBean.success();
    }

    @RequestMapping(value="/generateCode",method=RequestMethod.POST)
    @ApiOperation("生成代码")
    public void generateCode(String tables, HttpServletResponse response){
        commonService.generateCode(tables,response);
    }

    @RequestMapping(value="/shell",method=RequestMethod.POST)
    @ApiOperation("模拟shell命令")
    public ResultBean shell(String str){
        return commonService.shell(str);
    }

    @RequestMapping(value="/request",method=RequestMethod.POST)
    @ApiOperation("发送请求")
    public ResultBean request(String url,String parm){
        return commonService.request(url,parm);
    }

    @RequestMapping(value="/concurrentRequest",method=RequestMethod.POST)
    @ApiOperation("并发发送请求")
    public ResultBean concurrentRequest(String url,String parm,Integer max){
        return commonService.concurrentRequest(url,parm,max);
    }

    @RequestMapping(value="/getDateTime",method=RequestMethod.POST)
    @ApiOperation("获取当前时间")
    public ResultBean getDateTime(){
        return commonService.getDateTime();
    }



}
