package com.shp.dev.network.common.util.sql.mapper;

import com.shp.dev.network.common.util.sql.model.SysSql;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * @CreateBy: shp
 * @Version: 1.0
 * @Description: TODO
 * @CreateTime: 2020/9/25 14:56
 * @PackageName: com.shp.dev.network8.0.sql.mapper
 * @ProjectName: network
 */
@Mapper
public interface SysSqlMapper {

    @Insert("INSERT INTO `tb_sys_sql`(`sql_name`,`sql_text`) VALUES(#{sqlName},#{sqlText})")
    Integer addSysSql(SysSql sql);

    @Update("UPDATE `tb_sys_sql` SET `sql_text`=#{sqlText} WHERE sql_name=#{sqlName}")
    Integer editSysSql(SysSql sql);

    @Delete("DELETE FROM `tb_sys_sql` WHERE `sql_name`=#{sqlName}")
    Integer delSysSql(SysSql sql);

    @Select("SELECT `sql_name` sqlName,`sql_text` sqlText FROM `tb_sys_sql`")
    List<SysSql> queryAll();

    @Select("SELECT `sql_name` sqlName,`sql_text` sqlText FROM `tb_sys_sql` WHERE sql_name= TRIM(#{sqlName})")
    SysSql queryBySqlName(SysSql sql);

}
