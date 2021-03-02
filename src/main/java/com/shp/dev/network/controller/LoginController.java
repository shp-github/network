package com.shp.dev.network.controller;

import com.shp.dev.network.common.bean.ResultBean;
import com.shp.dev.network.common.bean.Tablepar;
import com.shp.dev.network.common.service.ICommonService;
import com.shp.dev.network.model.SysUser;
import com.shp.dev.network.service.ILoginService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;

/**
 * @CreateBy: shp
 * @Version: 1.0
 * @Description: TODO 登录控制层
 * @CreateTime: 2020/6/30 22:27
 * @PackageName: com.shp.dev.network.common.controller
 * @ProjectName: network
 */
@RestController //返回JSON
@CrossOrigin //跨域注解
@Api(tags = "登录相关接口")
public class LoginController {

    //导入登录业务类
    @Autowired
    private ILoginService loginService;
    @Autowired
    private ICommonService commonService;

    @PostMapping("/selectList")
    @ApiOperation("查询用户列表-plus")
    public ResultBean selectList(Tablepar tablepar) {
        return loginService.selectList(tablepar);
    }

    @GetMapping("/")
    @ApiOperation("跳转到swagger-ui")
    public void to(HttpServletResponse res) {
        commonService.to(res);
    }

    @ApiOperation("登录接口")
    @RequestMapping("/login")
    //public ResultBean login(@RequestBody SysUser sysUser) {
    public ResultBean login(SysUser sysUser) {
        return loginService.login(sysUser);
    }

    @RequestMapping(value = "/code", method = {RequestMethod.POST, RequestMethod.GET})
    @ApiOperation("获取验证码")
    public ResultBean code() {
        return commonService.code();
    }

    @PostMapping("/saveLogin")
    @ApiOperation("保存用户接口")
    public ResultBean addLogin(SysUser sysUser) {
        //重置密码
        if (sysUser.getId() != null && sysUser.getId() != 0 && sysUser.getPassword() != null && sysUser.getPassword().equals("000000")) {
            return loginService.restPassword(sysUser);
            //修改用户
        } else if (sysUser.getId() != null && sysUser.getId() != 0) {
            return loginService.editLogin(sysUser);
        }
        //添加用户ma
        return loginService.addLogin(sysUser);
    }

    @PostMapping("/delLogin")
    @ApiOperation("删除")
    public ResultBean delLogin(SysUser sysUser) {
        if (sysUser == null || sysUser.getId() == null) {
            ResultBean.error("id为空！");
        }
        return loginService.delLogin(sysUser.getId());
    }

    @PostMapping("/findUserList")
    @ApiOperation("查询用户列表")
    public ResultBean findUserList(SysUser sysUser, Tablepar tablepar) {
        return loginService.findUserList(sysUser, tablepar);
    }


}
