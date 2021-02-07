package com.shp.dev.network.common.bean;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @CreateBy: shp
 * @Version: 1.0
 * @Description: TODO 分页的参数类
 * @CreateTime: 2020-07-08 9:59
 * @PackageName: com.shp.dev.network.common.bean
 * @ProjectName: network
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@ApiModel("分页的参数类")
public class Tablepar {

    @ApiModelProperty("页码")
    private Integer pageNum=1;

    @ApiModelProperty("每页显示数量")
    private Integer pageSize=10;

    @ApiModelProperty("是否进行count查询")
    private boolean count=true;

    @ApiModelProperty("分页合理化,null时用默认配置")
    private String reasonable=null;

    @ApiModelProperty("true且pageSize=0时返回全部结果，false时分页,null时用默认配置")
    private boolean pageSizeZero=true;

}