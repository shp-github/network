package com.shp.dev.network.common.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.task.AsyncTaskExecutor;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.ThreadPoolExecutor;

/**
 * @CreateBy: shp
 * @Version: 1.0
 * @Description: TODO 多线程工具类
 * @CreateTime: 2020/8/14 10:36
 * @PackageName: com.vimochina.vimo.config
 * @ProjectName: wisdomeyeapi0114
 */
@EnableAsync
@Configuration
public class ThreadConfig {

    /**
     * 线程池维护线程的最少数量
     */
    private int corePoolSize = 10;

    /**
     * 线程池维护线程的最大数量
     */
    private int maxPoolSize = 50;

    /**
     * 缓存队列
     */
    private int queueCapacity = 100;

    /**
     * 允许的空闲时间
     */
    private int keepAlive = 60;

    //同步线程池,如果不指定name值，则按methodName做beanName
    @Bean(name = "syncExceutor")
    public ThreadPoolTaskExecutor syncExceutor() {
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        executor.setCorePoolSize(corePoolSize);
        executor.setMaxPoolSize(maxPoolSize);
        executor.setKeepAliveSeconds(keepAlive);
        executor.setQueueCapacity(queueCapacity);
        executor.setThreadNamePrefix("mqExecutor-");
        // rejection-policy：当pool已经达到max size的时候，如何处理新任务
        // CALLER_RUNS：不在新线程中执行任务，而是由调用者所在的线程来执行
        //对拒绝task的处理策略
        executor.setRejectedExecutionHandler(new ThreadPoolExecutor.CallerRunsPolicy());
        executor.initialize();
        return executor;
    }

    //异步线程池,如果不指定name值，则按methodName做beanName
    @Bean(name = "asyncExceutor")
    public AsyncTaskExecutor asyncExceutor() {
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        executor.setCorePoolSize(corePoolSize);
        executor.setMaxPoolSize(maxPoolSize);
        executor.setKeepAliveSeconds(keepAlive);
        executor.setQueueCapacity(queueCapacity);
        executor.setThreadNamePrefix("mqExecutor-");
        // rejection-policy：当pool已经达到max size的时候，如何处理新任务
        // CALLER_RUNS：不在新线程中执行任务，而是由调用者所在的线程来执行
        //对拒绝task的处理策略
        executor.setRejectedExecutionHandler(new ThreadPoolExecutor.CallerRunsPolicy());
        executor.initialize();
        return executor;
    }

    //异步线程池,如果不指定name值，则按methodName做beanName
    @Bean(name = "process")
    public AsyncTaskExecutor video() {
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        executor.setCorePoolSize(25*10);
        executor.setMaxPoolSize(25*10);
        executor.setKeepAliveSeconds(25*10);
        executor.setQueueCapacity(0);
        executor.setThreadNamePrefix("video-");
        // rejection-policy：当pool已经达到max size的时候，如何处理新任务
        // CALLER_RUNS：不在新线程中执行任务，而是由调用者所在的线程来执行
        //对拒绝task的处理策略
        executor.setRejectedExecutionHandler(new ThreadPoolExecutor.CallerRunsPolicy());
        executor.initialize();
        return executor;
    }

}