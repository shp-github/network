package com.shp.dev.network.service;

import com.shp.dev.network.common.bean.ResultBean;
import com.shp.dev.network.common.bean.Tablepar;
import com.shp.dev.network.model.SysUser;

/**
 * @CreateBy: shp
 * @Version: 1.0
 * @Description: TODO 登录业务类
 * @CreateTime: 2020-07-05 0:22
 * @PackageName: com.shp.dev.network.service
 * @ProjectName: network
 */

public interface ILoginService {


    /**
     * @CreateBy: shp
     * @version：1.0
     * @Description: TODO 验证是否登录成功
     * @CreateTime: 2020-07-05 11:24
     * @param: sysUser
     * @return: com.shp.dev.network.common.bean.ResultBean
     */
    ResultBean login(SysUser sysUser);

    /**
     * @CreateBy: Administrator
     * @version：1.0
     * @Description: TODO 查询用户列表
     * @CreateTime: 2021/3/2 11:46
     * @param: tablepar
     * @return: com.shp.dev.network.common.bean.ResultBean
     */
    ResultBean selectList(Tablepar tablepar);

    /**
     * @CreateBy: shp
     * @version：1.0
     * @Description: TODO 添加用户接口
     * @CreateTime: 2020-07-05 11:25
     * @param: sysUser
     * @return: com.shp.dev.network.common.bean.ResultBean
     */
    ResultBean addLogin(SysUser sysUser);

    /**
     * @CreateBy: shp
     * @version：1.0
     * @Description: TODO 修改用户接口
     * @CreateTime: 2020-07-05 11:25
     * @param: sysUser
     * @return: com.shp.dev.network.common.bean.ResultBean
     */
    ResultBean editLogin(SysUser sysUser);

    /**
     * @CreateBy: shp
     * @version：1.0
     * @Description: TODO 删除用户接口
     * @CreateTime: 2020-07-05 11:26
     * @param: sysUser
     * @return: com.shp.dev.network.common.bean.ResultBean
     */
    ResultBean delLogin(Long id);

    /**
     * @CreateBy: shp
     * @version：1.0
     * @Description: TODO 查询用户列表
     * @CreateTime: 2020-07-06 23:39
     * @param: sysUser
     * @return: com.shp.dev.network.common.bean.ResultBean
     */
    ResultBean findUserList(SysUser sysUser, Tablepar tablepar);

    ResultBean restPassword(SysUser sysUser);
}
