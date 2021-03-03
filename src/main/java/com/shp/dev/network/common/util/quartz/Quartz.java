package com.shp.dev.network.common.util.quartz;

import com.shp.dev.network.common.util.DateUtils;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @CreateBy: Administrator
 * @Version: 1.0
 * @Description: TODO
 * @CreateTime: 2021/3/3 14:18
 * @PackageName: com.shp.dev.network.common.util.task
 * @ProjectName: network
 */
@Data
@ApiModel("定时任务对应实体类")
public class Quartz {
    @ApiModelProperty("主键")
    private Integer quartzId;  //id  主键
    @ApiModelProperty("任务名称")
    private String jobName;  //任务名称
    @ApiModelProperty("任务分组")
    private String jobGroup;  //任务分组
    @ApiModelProperty("任务开始时间")
    private String startTime= DateUtils.getDateTime();  //任务开始时间
    @ApiModelProperty("corn表达式 https://cron.qqe2.com/")
    private String cronExpression;  //corn表达式
    @ApiModelProperty("需要传递的参数")
    private String invokeParam;//需要传递的参数
}
