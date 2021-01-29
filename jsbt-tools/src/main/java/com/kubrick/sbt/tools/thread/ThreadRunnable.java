package com.kubrick.sbt.tools.thread;

/**
 * @author k
 * @version 1.0.0
 * @ClassName MyTask
 * @description: TODO
 * @date 2021/1/28 下午11:22
 */
public class ThreadRunnable implements Runnable{
    @Override
    public void run() {
        Thread thread = Thread.currentThread();
        System.out.println("ThreadRunnable :"+thread.getName());
    }
}
