package com.shp.dev.network.menu.mapper;

import com.shp.dev.network.menu.model.SysMenu;
import com.shp.dev.network.menu.model.SysMenuResult;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @CreateBy: shp
 * @Version: 1.0
 * @Description: TODO 按钮数据层
 * @CreateTime: 2020-07-06 16:05
 * @PackageName: com.shp.dev.network.menu.mapper
 * @ProjectName: network
 */

@Mapper
public interface SysMenuMapper {


    /**
     * @CreateBy: shp
     * @version：1.0
     * @Description: TODO 展示所有菜单信息
     * @CreateTime: 2020-07-06 17:22
     * @param: menu_id
     * @return: java.util.List<com.shp.dev.network.menu.bean.SysMenu>
     */
    @Select("SELECT id,icon,menu_id menuId,menu_name menuName,has_third hasThird,url,pid FROM tb_sys_menu WHERE menu_id<10 and is_del=0 ORDER BY order_id asc")
    List<SysMenuResult> findAllMenu();

    /**
     * @CreateBy: shp
     * @version：1.0
     * @Description: TODO 根据父级ids查询子级按钮
     * @CreateTime: 2020-07-06 17:22
     * @param: menu_id
     * @return: java.util.List<com.shp.dev.network.menu.bean.SysMenu>
     */
    @Select({
            "<script>",
                "SELECT icon,menu_id menuid,menu_name menuname,has_third hasThird,url,pid FROM tb_sys_menu WHERE menu_id>10 and is_del=0 ",
                "AND pid in",
                "<foreach collection='ids' item='id' open='(' separator=',' close=')'>",
                    "#{id}",
                "</foreach>",
            "</script>"
    })
    List<SysMenu> findMenuByIds(@Param("ids") List<Long> ids);

    /**
     * @CreateBy: shp
     * @version：1.0
     * @Description: TODO 根据父级ids查询子级按钮
     * @CreateTime: 2020-07-06 17:22
     * @param: menu_id
     * @return: java.util.List<com.shp.dev.network.menu.bean.SysMenu>
     */
    @Select("SELECT icon,menu_id menuid,menu_name menuname,has_third hasThird,url,pid FROM tb_sys_menu WHERE menu_id>10 AND pid=#{pid} and is_del=0")
    List<SysMenuResult> findMenuByPid(@Param("pid")Long pid);

}
