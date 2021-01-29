package com.kubrick.sbt.tools.thread;

import com.google.common.util.concurrent.ThreadFactoryBuilder;

import java.util.concurrent.*;

/**
 * @author k
 * @version 1.0.0
 * @ClassName ThreadPool
 * @description: 线程相关
 * @date 2021/1/28 下午11:16
 */
public class ThreadPool {

    public final static int EXECUTE_COUNT = 1;
    public final static int LOOP_COUNT = 1;
    public final static int POOL_SIZE = 5;

    private static final ThreadFactory NAMED_THREAD_FACTORY = new ThreadFactoryBuilder().setNameFormat("thread-pool-%d").build();
    private static final ExecutorService EXECUTOR = new ThreadPoolExecutor(10, 10,
            60L, TimeUnit.SECONDS,
            new ArrayBlockingQueue(10));
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
            new LinkedBlockingQueue<Runnable>(5), NAMED_THREAD_FACTORY, new ThreadPoolExecutor.AbortPolicy());

    public static void main(String[] args) throws Exception {


        for (int i = 0; i < EXECUTE_COUNT; i++) {
            pool.execute(new ThreadRunnable());
            EXECUTOR.execute(new ThreadRunnable());
        }

        Callable<String> callable = new NewThread();
        FutureTask<String> futureTask = new FutureTask<>(callable);
        Thread mThread1 = new Thread(futureTask);
        mThread1.setName("Callable thread");
        mThread1.start();
        System.out.println(futureTask.get());
        createNewFixedThreadPool();
        createNewSingleThreadExecutor();
        createNewCachedThreadPool();

    }

    /**
     * 4、newFixedThreadPool
     */
    public static void createNewFixedThreadPool() {
        ExecutorService ex = Executors.newFixedThreadPool(POOL_SIZE);
        for (int i = 0; i < EXECUTE_COUNT; i++) {
            ex.submit(new Runnable() {
                @Override
                public void run() {
                    for (int j = 0; j < LOOP_COUNT; j++) {
                        System.out.println("newFixedThreadPool:" + Thread.currentThread().getName() + j);
                    }
                }
            });
        }
        ex.shutdown();
    }

    /**
     * 5、newSingleThreadExecutor
     */
    public static void createNewSingleThreadExecutor() {
        ExecutorService ex = Executors.newSingleThreadExecutor();
        for (int i = 0; i < EXECUTE_COUNT; i++) {
            ex.submit(new Runnable() {
                @Override
                public void run() {
                    for (int j = 0; j < LOOP_COUNT; j++) {
                        System.out.println("newSingleThreadExecutor:" + Thread.currentThread().getName() + j);
                    }
                }
            });
        }
        ex.shutdown();
    }

    /**
     * 6、newCachedThreadPool
     */
    public static void createNewCachedThreadPool() {
        ExecutorService ex = Executors.newCachedThreadPool();
        for (int i = 0; i < EXECUTE_COUNT; i++) {
            ex.submit(new Runnable() {
                @Override
                public void run() {
                    for (int j = 0; j < LOOP_COUNT; j++) {
                        System.out.println("newCachedThreadPool:" + Thread.currentThread().getName() + j);
                    }
                }
            });
        }
        ex.shutdown();
    }

}
