package com.shp.dev.network.model;

import com.baomidou.mybatisplus.annotation.TableField;
import com.shp.dev.network.common.util.DateUtils;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @CreateBy: shp
 * @Version: 1.0
 * @Description: TODO 向页面输出信息对应实体类
 * @CreateTime: 2020/7/25 23:03
 * @PackageName: com.shp.dev.network.model
 * @ProjectName: network
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SysWrite {

    @TableField(exist = false)
    @ApiModelProperty("主键")
    private Long id;
    @ApiModelProperty("关键字")
    private String keyword;
    @ApiModelProperty("描述")
    private String description;
    @ApiModelProperty("网页主体")
    private String body;
    @ApiModelProperty("嵌入地址")
    private String uri;
    @ApiModelProperty("操作时间")
    private String updateTime = DateUtils.getDate();
    @ApiModelProperty("是否删除")
    private Integer isDel = 0;

}
