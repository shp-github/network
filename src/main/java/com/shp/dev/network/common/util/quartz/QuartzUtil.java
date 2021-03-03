package com.shp.dev.network.common.util.quartz;

import lombok.extern.slf4j.Slf4j;
import org.quartz.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * @CreateBy: Administrator
 * @Version: 1.0
 * @Description: TODO
 * @CreateTime: 2021/3/3 14:19
 * @PackageName: com.shp.dev.network.common.util.task
 * @ProjectName: network
 */

@Service
@Slf4j
public class QuartzUtil {

    @Autowired
    @Qualifier("scheduler")
    private Scheduler scheduler;

    /**
     * @CreateBy: Administrator
     * @version：1.0
     * @Description: TODO 添加任务
     * @CreateTime: 2021/3/3 15:49
     * @param: quartz
     * @return: java.lang.String
     */
    public String addQuartz(Quartz quartz) throws Exception {
        //Date date = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(quartz.getStartTime());
        Date date = new Date();

        if (!CronExpression.isValidExpression(quartz.getCronExpression())) {
            log.error("表达式格式不正确");
            return "表达式格式不正确";
        }

        JobDetail jobDetail = JobBuilder.newJob(SchedulingInstance.class).withIdentity(quartz.getJobName(), quartz.getJobGroup()).build();

        //表达式调度构建器(即任务执行的时间,不立即执行)
        CronScheduleBuilder scheduleBuilder = CronScheduleBuilder.cronSchedule(quartz.getCronExpression()).withMisfireHandlingInstructionDoNothing();

        //按新的cronExpression表达式构建一个新的trigger
        CronTrigger trigger = TriggerBuilder.newTrigger().withIdentity(quartz.getJobName(), quartz.getJobGroup()).startAt(date)
                .withSchedule(scheduleBuilder).build();

        //传递参数
        if (quartz.getInvokeParam() != null && !"".equals(quartz.getInvokeParam())) {
            trigger.getJobDataMap().put("invokeParam", quartz.getInvokeParam());
        }
        scheduler.scheduleJob(jobDetail, trigger);
        return "success";
    }


    //暂停所有任务
    public void pauseAllJob() throws SchedulerException {
        scheduler.pauseAll();
    }

    //恢复所有任务
    public void resumeAllJob() throws SchedulerException {
        scheduler.resumeAll();
    }

    List<String> getJobGroupNames() throws SchedulerException {
        return scheduler.getJobGroupNames();
    }

    /**
     * @CreateBy: Administrator
     * @version：1.0
     * @Description: TODO 获取Job状态
     * @CreateTime: 2021/3/3 15:53
     * @param: jobName
     * @param: jobGroup
     * @return: java.lang.String
     */
    public String getJobState(String name, String group) throws SchedulerException {
        return scheduler.getTriggerState(new TriggerKey(name, group)).name();
    }

    //暂停任务
    public String pauseJob(String name, String group) throws SchedulerException {
        JobDetail jobDetail = scheduler.getJobDetail(new JobKey(name, group));
        if (jobDetail == null) {
            return "fail";
        } else {
            scheduler.pauseJob(new JobKey(name, group));
            return "success";
        }
    }

    // 恢复任务
    public String resumeJob(String name, String group) throws SchedulerException {
        JobKey jobKey = new JobKey(name, group);
        JobDetail jobDetail = scheduler.getJobDetail(jobKey);
        if (jobDetail == null) {
            return "fail";
        } else {
            scheduler.resumeJob(jobKey);
            return "success";
        }
    }

    //删除任务
    public String deleteJob(Quartz quartz) throws SchedulerException {
        JobKey jobKey = new JobKey(quartz.getJobName(), quartz.getJobGroup());
        JobDetail jobDetail = scheduler.getJobDetail(jobKey);
        if (jobDetail == null) {
            return "jobDetail is null";
        } else if (!scheduler.checkExists(jobKey)) {
            return "jobKey is not exists";
        } else {
            scheduler.deleteJob(jobKey);
            return "success";
        }

    }

    //修改任务
    public String modifyJob(Quartz quartz) throws SchedulerException {
        if (!CronExpression.isValidExpression(quartz.getCronExpression())) {
            return "Illegal cron expression";
        }
        TriggerKey triggerKey = TriggerKey.triggerKey(quartz.getJobName(), quartz.getJobGroup());
        JobKey jobKey = new JobKey(quartz.getJobName(), quartz.getJobGroup());
        if (scheduler.checkExists(jobKey) && scheduler.checkExists(triggerKey)) {
            CronTrigger trigger = (CronTrigger) scheduler.getTrigger(triggerKey);
            //表达式调度构建器,不立即执行
            CronScheduleBuilder scheduleBuilder = CronScheduleBuilder.cronSchedule(quartz.getCronExpression()).withMisfireHandlingInstructionDoNothing();
            //按新的cronExpression表达式重新构建trigger
            trigger = trigger.getTriggerBuilder().withIdentity(triggerKey)
                    .withSchedule(scheduleBuilder).build();
            //修改参数
            if (!trigger.getJobDataMap().get("invokeParam").equals(quartz.getInvokeParam())) {
                trigger.getJobDataMap().put("invokeParam", quartz.getInvokeParam());
            }
            //按新的trigger重新设置job执行
            scheduler.rescheduleJob(triggerKey, trigger);
            return "success";
        } else {
            return "job or trigger not exists";
        }
    }


}