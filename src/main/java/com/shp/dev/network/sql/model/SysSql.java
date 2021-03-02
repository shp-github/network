package com.shp.dev.network.sql.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @CreateBy: shp
 * @Version: 1.0
 * @Description: TODO
 * @CreateTime: 2020/9/25 12:02
 * @PackageName: com.shp.dev.network8.0.sql.model
 * @ProjectName: network
 */
@Data
@ApiModel
@NoArgsConstructor
@AllArgsConstructor
public class SysSql {
    @ApiModelProperty("sql名称")
    private String sqlName;
    @ApiModelProperty("sql语句")
    private String sqlText;
    @ApiModelProperty("参数 用逗号隔开")
    private String parms;
    @ApiModelProperty("参数 起始页,条数")
    private String limit;
    @ApiModelProperty("参数 传入数组参数")
    private Object[] objects;
}
