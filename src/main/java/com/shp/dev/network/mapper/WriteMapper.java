package com.shp.dev.network.mapper;

import com.shp.dev.network.model.SysWrite;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

/**
 * @CreateBy: shp
 * @Version: 1.0
 * @Description: TODO 向页面输出信息到曾
 * @CreateTime: 2020/7/25 22:55
 * @PackageName: com.shp.dev.network.model
 * @ProjectName: network
 */

@Mapper
public interface WriteMapper {

    @Select("SELECT * FROM tb_sys_write WHERE is_del=0 and id=#{id} or is_del=0 and keyword=#{keyword} ")
    SysWrite getWrite(SysWrite model);

    /**
     * @CreateBy: shp
     * @version：1.0
     * @Description: TODO 获取错误信息
     * @CreateTime: 2020/7/25 23:35
     * @param: model
     * @return: com.shp.dev.network.model.WriteModel
     */
    @Select("SELECT * FROM tb_sys_write WHERE keyword=404")
    SysWrite getError(SysWrite model);

    /**
     * @CreateBy: shp
     * @version：1.0
     * @Description: TODO 获取信息列表
     * @CreateTime: 2020/7/26 15:32
     * @param: model
     * @return: java.util.List
     */

    //@Select("SELECT id,keyword,`description`,body,update_time updateTime" +
    //        " FROM tb_sys_write " +
    //        " WHERE is_del=0 and description like concat('%',#{keyword},'%') or keyword like concat('%',#{keyword},'%') ")
    @Select({
            "<script>",
            "SELECT id,keyword,`description`,body,update_time updateTime ",
            "FROM tb_sys_write ",
            "WHERE is_del=0 ",
            "<when test='keyword!=null' >",
            "and (description like concat('%',#{keyword},'%') or keyword like concat('%',#{keyword},'%') ) ",
            "</when>",
            " order by id desc",
            "</script>"
    })
    List<SysWrite> getWriteList(SysWrite model);

    @Update("update tb_sys_write set is_del=1 where id=#{id}")
    Integer delWrite(SysWrite model);
}
