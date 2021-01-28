package com.kubrick.sbt.tools.thread;

import com.google.common.util.concurrent.ThreadFactoryBuilder;

import java.util.concurrent.*;

/**
 * @author k
 * @version 1.0.0
 * @ClassName ThreadPool
 * @description: TODO
 * @date 2021/1/28 下午11:16
 */
public class ThreadPool {

    private static ExecutorService executor = new ThreadPoolExecutor(10, 10,
            60L, TimeUnit.SECONDS,
            new ArrayBlockingQueue(10));

    private static ThreadFactory namedThreadFactory = new ThreadFactoryBuilder()
            .setNameFormat("demo-pool-%d").build();

    /**
     * 1、核心poolSize
     * 2、最大poolSize
     * 3、保持时间
     * 4、队列存储线程
     * 5、线程工厂
     * 6、异常拒绝处理
     */
    private static ExecutorService pool = new ThreadPoolExecutor(5, 6,
            0L, TimeUnit.MILLISECONDS,
            new LinkedBlockingQueue<Runnable>(5), namedThreadFactory, new ThreadPoolExecutor.AbortPolicy());

    public static void main(String[] args) {

        int count = 20;
        for (int i = 0; i < count; i++) {
            pool.execute(new MyTask());
            executor.execute(new MyTask());
        }
    }
}
