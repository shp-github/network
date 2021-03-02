package com.shp.dev.network.service.impl;

import com.shp.dev.network.common.bean.PageBean;
import com.shp.dev.network.common.bean.PageHelperBean;
import com.shp.dev.network.common.bean.ResultBean;
import com.shp.dev.network.common.bean.Tablepar;
import com.shp.dev.network.common.util.JwtTokenUtil;
import com.shp.dev.network.common.util.MD5;
import com.shp.dev.network.mapper.LoginMapper;
import com.shp.dev.network.mapper.LoginMapperPlus;
import com.shp.dev.network.model.SysUser;
import com.shp.dev.network.service.ILoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @CreateBy: shp
 * @Version: 1.0
 * @Description: TODO 登录业务类
 * @CreateTime: 2020-07-05 0:22
 * @PackageName: com.shp.dev.network.service
 * @ProjectName: network
 */

@Service
@Transactional(rollbackFor = Exception.class)
public class LoginService implements ILoginService {

    @Autowired
    @Qualifier("jwtUserDetailsService")   //调用userDetailsService 时 会触发jwtUserDetailsService
    private UserDetailsService userDetailsService;

    @Autowired //注入jwt工具类
    private JwtTokenUtil jwtTokenUtil;

    @Autowired// 注入登录数据层
    private LoginMapper mapper;

    @Autowired
    private LoginMapperPlus plus;

    /**
     * @CreateBy: shp
     * @version：1.0
     * @Description: TODO 验证是否登录成功
     * @CreateTime: 2020-07-05 11:24
     * @param: sysUser
     * @return: com.shp.dev.network.common.bean.ResultBean
     */
    public ResultBean login(SysUser sysUser) {

        System.out.println("执行登录login接口");

        //判断用户信息时候获取到
        if (sysUser == null) {
            return ResultBean.error("请输入用户信息！！！");
        }

        //验证账号是否存在
        SysUser user = mapper.findByUserName(sysUser);

        if (user == null) {
            return ResultBean.error("没找到该用户，用户可能为空！！！");
        }

        //验证密码是否正确
        if (!user.getPassword().equals(MD5.md5(sysUser.getPassword()))) {
            return ResultBean.error("密码不正确！！！");
        }

        if (!sysUser.getUuid().equalsIgnoreCase(sysUser.getCode())) {
            return ResultBean.error("验证码不正确！！！");
        }

        //拿到当前用户信息
        final UserDetails userDetails = userDetailsService.loadUserByUsername(sysUser.getUsername());
        System.out.println("当前的用户的角色信息：----------------- " + userDetails.toString());

        //生成token
        final String token = jwtTokenUtil.generateToken(userDetails);
        System.out.println("生成的密钥为：  --------------------- " + token);

        return ResultBean.success("登录成功！！！", token);
    }

    /**
     * @CreateBy: Administrator
     * @version：1.0
     * @Description: TODO 获取用户列表
     * @CreateTime: 2021/3/2 11:47
     * @param: tablepar
     * @return: com.shp.dev.network.common.bean.ResultBean
     */
    @Override
    public ResultBean selectList(Tablepar tablepar) {
        PageHelperBean.startPage(tablepar);
        List<SysUser> sysUsers = plus.selectList(null);
        return ResultBean.success("获取用户列表成功！！！", new PageBean(sysUsers));
    }

    /**
     * @CreateBy: shp
     * @version：1.0
     * @Description: TODO 添加用户接口
     * @CreateTime: 2020-07-05 11:25
     * @param: sysUser
     * @return: com.shp.dev.network.common.bean.ResultBean
     */
    public ResultBean addLogin(SysUser sysUser) {

        //验证账号是否存在
        SysUser user = mapper.findByUserName(sysUser);
        if (user != null) {
            return ResultBean.error("用户已存在！！！");
        }

        //把用户的密码加密，保存到数据库
        sysUser.setPassword(MD5.md5(sysUser.getPassword()));
        return ResultBean.success("用户添加成功！！！", mapper.addByUserNameAndPassWrod(sysUser));
    }

    /**
     * @CreateBy: shp
     * @version：1.0
     * @Description: TODO 修改用户接口
     * @CreateTime: 2020-07-05 11:25
     * @param: sysUser
     * @return: com.shp.dev.network.common.bean.ResultBean
     */
    public ResultBean editLogin(SysUser sysUser) {

        SysUser user = mapper.findById(sysUser);
        //如果新输入的用户名和当前用户名不同，则判断是否和其他用户重名
        if (!user.getUsername().equals(sysUser.getUsername())) {
            SysUser user1 = mapper.findByUserName(sysUser);
            if (user1 != null) {
                return ResultBean.error("用户已存在！！！");
            }
        }

        //如果密码没有改变则不重复加密
        if (!user.getPassword().equals(sysUser.getPassword())) {
            sysUser.setPassword(MD5.md5(sysUser.getPassword()));
        }
        return ResultBean.success("修改成功！！！", mapper.editUserById(sysUser));
    }

    /**
     * @CreateBy: shp
     * @version：1.0
     * @Description: TODO 删除用户接口
     * @CreateTime: 2020-07-05 11:26
     * @param: sysUser
     * @return: com.shp.dev.network.common.bean.ResultBean
     */
    public ResultBean delLogin(Long id) {
        return ResultBean.success("删除成功！！！", mapper.delUserById(id));
    }

    /**
     * @CreateBy: shp
     * @version：1.0
     * @Description: TODO 查询用户列表
     * @CreateTime: 2020-07-06 23:39
     * @param: sysUser
     * @return: com.shp.dev.network.common.bean.ResultBean
     */
    public ResultBean findUserList(SysUser sysUser, Tablepar tablepar) {

        sysUser.setSex(sysUser.getSex().equals("")?null:sysUser.getSex());
        PageHelperBean.startPage(tablepar);
        List<SysUser> userList = mapper.findUserList(sysUser);
        return ResultBean.success("获取用户列表成功！！！", new PageBean(userList));
    }

    public ResultBean restPassword(SysUser sysUser) {
        //默认密码000000  6个0
        sysUser.setPassword(MD5.md5("000000"));
        return ResultBean.success("密码重置成功！！！", mapper.restPassword(sysUser));
    }
}
