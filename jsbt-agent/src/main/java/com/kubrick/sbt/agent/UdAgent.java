package com.kubrick.sbt.agent;

import java.lang.instrument.Instrumentation;

/**
 * @author k
 * @version 1.0.0
 * @ClassName UdAgent
 * @description: TODO
 * @date 2021/3/24 下午2:28
 */
public class UdAgent {
    public static void premain(String agentArgs, Instrumentation instrumentation) {
        instrumentation.addTransformer(new LogTransformer());
    }
}
