package com.kubrick.sbt.agent;

import java.lang.instrument.Instrumentation;

/**
 * @author k
 * @version 1.0.0
 * @ClassName com.kubrick.sbt.agent.TestAgent
 * @description: TODO
 * @date 2020/12/17 上午12:37
 */
public class TestAgent {

    public static void premain(String agentArgs, Instrumentation inst){
        System.out.println("premain start");
        System.out.println(agentArgs);
    }
}
