package com.kubrick.sbt.tools.thread;

/**
 * @author k
 * @version 1.0.0
 * @ClassName NormalThread
 * @description: TODO
 * @date 2021/1/29 上午11:01
 */
public class NormalThread extends Thread {

    NormalThread() {

    }

    @Override
    public void run() {
        Thread thread = Thread.currentThread();
        System.out.println("NormalThread:" + thread.getName());
    }

}
