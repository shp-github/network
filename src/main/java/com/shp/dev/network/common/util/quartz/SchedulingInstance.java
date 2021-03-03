package com.shp.dev.network.common.util.quartz;

import lombok.extern.slf4j.Slf4j;
import org.quartz.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @CreateBy: Administrator
 * @Version: 1.0
 * @Description: TODO
 * @CreateTime: 2021/3/3 14:19
 * @PackageName: com.shp.dev.network.common.util.task
 * @ProjectName: network
 */
@PersistJobDataAfterExecution
@DisallowConcurrentExecution
@Component
@Slf4j
public class SchedulingInstance implements Job {

    @Autowired
    private SchedulingService schedulingService;

    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {

        //获取JobDetail的内容
        JobKey jobKey = jobExecutionContext.getJobDetail().getKey();
       log.info("工作任务的名称:" + jobKey.getName() + ";工作任务组的名称:" + jobKey.getGroup());
       log.info("任务类的名称:(带路径)" + jobExecutionContext.getJobDetail().getJobClass().getName());
       log.info("任务类的名称:" + jobExecutionContext.getJobDetail().getJobClass().getSimpleName());
        //从JobDetail对象中获取jobDataMap数据
        /*JobDataMap jobDataMap = jobExecutionContext.getJobDetail().getJobDataMap();
       log.info("messageJobDetail:" + jobDataMap.getString("message"));*/
        //获取Trigger内容
        TriggerKey triggerKey = jobExecutionContext.getTrigger().getKey();
       log.info("触发器的名称:" + triggerKey.getName() + ";出发组的名称:" + triggerKey.getGroup());
       log.info("触发器类的名称(带路径):" + jobExecutionContext.getTrigger().getClass().getName());
       log.info("触发器类的名称:" + jobExecutionContext.getTrigger().getClass().getSimpleName());
        //从Trigger对象中获取jobDataMap数据
        JobDataMap data = jobExecutionContext.getTrigger().getJobDataMap();
        String invokeParam = (String) data.get("invokeParam");
        // 会覆盖jobDetail中的.usingData("message","打印日志jobDetail")
        Date date = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String dateString = simpleDateFormat.format(date);
       log.info("正在进行数据库的备份工作:数据库备份时间:" + dateString);
        //获取其他内容
       log.info("当前任务的执行时间:" + simpleDateFormat.format(jobExecutionContext.getFireTime()));
       log.info("下次任务的执行时间:" + simpleDateFormat.format(jobExecutionContext.getNextFireTime()));

        if(jobKey.getName().equalsIgnoreCase("synchronizedRedis")){
            schedulingService.synchronizedRedis();
        }

    }
}


