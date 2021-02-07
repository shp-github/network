package com.shp.dev.network.common.config;

import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.validation.constraints.NotEmpty;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

@Component
public class ThreadPollUtils {

    private static final int CORE_THREAD_SIZE = 100;

    public static ThreadPoolExecutor pullStreamThreadPool;

    public static ThreadPoolExecutor sendStreamThreadPool;

    @PostConstruct
    public void init() {
        // 拉流线程池
        pullStreamThreadPool = new ThreadPoolExecutor(CORE_THREAD_SIZE,
                CORE_THREAD_SIZE,
                0,
                TimeUnit.MILLISECONDS,
                new LinkedBlockingDeque<>(),
                new ThreadFactoryLocal("Thread_pullStream_%s"));

        // 推流线程池
        sendStreamThreadPool = new ThreadPoolExecutor(CORE_THREAD_SIZE,
                CORE_THREAD_SIZE,
                0,
                TimeUnit.MILLISECONDS,
                new LinkedBlockingDeque<>(),
                new ThreadFactoryLocal("Thread_pullStream_%s"));
    }

    // 添加每条链路的线程任务
    public static void addLinkThreadPool() {


        // 拉流线程任务
        pullStreamThreadPool.execute(new Runnable() {
            @Override
            public void run() {


            }
        });

        // 推流线程任务
        sendStreamThreadPool.execute(new Runnable() {
            @Override
            public void run() {


            }
        });

    }

    public static class ThreadFactoryLocal implements ThreadFactory {
        private final AtomicInteger poolNumber = new AtomicInteger(1);
        private final ThreadGroup group;
        private final AtomicInteger threadNumber = new AtomicInteger(1);
        private final String namePrefix;

        public ThreadFactoryLocal(@NotEmpty String namePrefix) {
            SecurityManager s = System.getSecurityManager();
            this.group = (s != null) ? s.getThreadGroup() :
                    Thread.currentThread().getThreadGroup();
            this.namePrefix = namePrefix;
        }

        @Override
        public Thread newThread(Runnable r) {
            Thread t = new Thread(group, r, namePrefix.replace("%s", String.valueOf(threadNumber.getAndIncrement())), 0);
            if (t.isDaemon()) {
                t.setDaemon(false);
            }
            if (t.getPriority() != Thread.NORM_PRIORITY) {
                t.setPriority(Thread.NORM_PRIORITY);
            }
            return t;
        }
    }

}
