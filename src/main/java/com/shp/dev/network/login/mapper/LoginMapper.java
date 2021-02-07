package com.shp.dev.network.login.mapper;

import com.shp.dev.network.login.model.SysUser;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * @CreateBy: shp
 * @Version: 1.0
 * @Description: TODO 登录数据层
 * @CreateTime: 2020-07-05 0:28
 * @PackageName: com.shp.dev.network.common.mapper
 * @ProjectName: network
 */
@Mapper
public interface LoginMapper {

    //根据用户名查询用户基本信息
    @Select("SELECT id,username,`password`,phone,sex,age,address,operate_date,operate_by FROM tb_sys_user where username=#{username}")
    SysUser findByUserName(SysUser sysUser);

    //根据用户ID查询用户基本信息
    @Select("SELECT id,username,password FROM tb_sys_user where id=#{id}")
    SysUser findById(SysUser sysUser);

    //删除用户
    @Delete("DELETE FROM tb_sys_user WHERE id=#{id}")
    Integer delUserById(Long id);

    //修改用户
    @Update("UPDATE tb_sys_user SET password=#{password},username=#{username},phone=#{phone},sex=#{sex},age=#{age},address=#{address},`operate_by`=#{operateBy},operate_date=NOW() WHERE id=#{id}")
    Integer editUserById(SysUser sysUser);

    //添加用户
    @Insert("INSERT INTO tb_sys_user(username,password,phone,sex,age,address,operate_date,operate_by) VALUES(#{username},#{password},#{phone},#{sex},#{age},#{address},NOW(),#{operateBy})")
    Integer addByUserNameAndPassWrod(SysUser sysUser);

    @Select({
            "<script>",
                "SELECT * FROM tb_sys_user ",
                "WHERE 1=1 ",
                "<when test='sex!=null' >",
                    "AND sex = #{sex} ",
                "</when>",
                "<when test='username!=null' >",
                    "AND ( username like concat('%',#{username},'%') or phone like concat('%',#{username},'%') ) ",
                "</when>",
                " order by id desc",
            "</script>"
        })
    List<SysUser> findUserList(SysUser sysUser);

    //重置密码
    @Update("UPDATE tb_sys_user SET password=#{password} WHERE id=#{id}")
    Integer restPassword(SysUser sysUser);
}
