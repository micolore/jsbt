package com.kubrick.sbt.tools.thread;

import java.util.concurrent.Callable;

/**
 * @author k
 * @version 1.0.0
 * @ClassName NewThread
 * @description: TODO
 * @date 2021/1/29 上午11:04
 */
public class NewThread implements Callable<String> {
    @Override
    public String call() throws Exception {
        System.out.println("NewThread:" + Thread.currentThread().getName());
        return "task over";

    }
}
