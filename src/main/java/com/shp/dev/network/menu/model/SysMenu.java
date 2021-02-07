package com.shp.dev.network.menu.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

/**
 * @CreateBy: shp
 * @Version: 1.0
 * @Description: TODO 左侧菜单树对应实体类
 * @CreateTime: 2020-07-06 14:54
 * @PackageName: com.shp.dev.network.menu.bean
 * @ProjectName: network
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel("左侧菜单树对应实体类")
public class SysMenu {

    @ApiModelProperty("主键id")
    private Long id;

    @ApiModelProperty("按钮id")
    private Long menuId;

    @ApiModelProperty("图标")
    private String icon;

    @ApiModelProperty("按钮名称")
    private String menuName;

    @ApiModelProperty("分类")
    private String hasThird;

    @ApiModelProperty("按钮地址")
    private String url;

    @ApiModelProperty("父级id")
    private Integer pid;

    @ApiModelProperty("是否删除")
    private Integer idDel;

    @ApiModelProperty("排序id")
    private Integer orderId;

    @ApiModelProperty("按钮集合")
    private List<SysMenu> menus = new ArrayList<>();

}
