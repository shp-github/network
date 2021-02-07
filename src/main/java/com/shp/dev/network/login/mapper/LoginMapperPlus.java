package com.shp.dev.network.login.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.shp.dev.network.login.model.SysUser;
import org.apache.ibatis.annotations.Mapper;

/**
 * @CreateBy: shp
 * @Version: 1.0
 * @Description:
 * TODO mybatisplus dao层 默认会根据实体类来名来找表名
 * TODO 例如 SysUser 对应的表就是 tb_sys_user 前缀为tb，驼峰转为下划线
 * TODO 实体类的属性必须对应中的字段 不然会报错，或者添加不匹配表的注解
 *  //@TableField(exist = false)：表示该属性不为数据库表字段，但又是必须使用的。
 *  //@TableField(exist = true)：表示该属性为数据库表字段。
 * @CreateTime: 2020/9/29 14:48
 * @PackageName: com.shp.dev.network.login.mapper
 * @ProjectName: network
 */
@Mapper
public interface LoginMapperPlus extends BaseMapper<SysUser> {
}
