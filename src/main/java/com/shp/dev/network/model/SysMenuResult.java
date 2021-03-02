package com.shp.dev.network.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @CreateBy: shp
 * @Version: 1.0
 * @Description: TODO 左侧菜单树对应返回值类
 * @CreateTime: 2020-07-06 14:54
 * @PackageName: com.shp.dev.network.menu.bean
 * @ProjectName: network
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel("左侧菜单树对应实体类")
public class SysMenuResult {

    @ApiModelProperty("按钮id")
    private Long menuid=0L;

    @ApiModelProperty("图标")
    private String icon;

    @ApiModelProperty("按钮名称")
    private String menuname;

    @ApiModelProperty("分类")
    private String hasThird=null;

    @ApiModelProperty("按钮地址")
    private String url=null;

    @ApiModelProperty("按钮集合")
    private List<SysMenuResult> menus=null;

}
