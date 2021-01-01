package com.kubrick.sbt.agent;

import java.lang.instrument.Instrumentation;

/**
 * @author k
 * @version 1.0.0
 * @ClassName com.kubrick.sbt.agent.TestAgent
 * @description: TODO -javaagent:
 * /Users/kubrick/Documents/workspace/java/study/sbt/sbt-agent/target/sbt-agent-1.0-SNAPSHOT.jar=how
 * @date 2020/12/17 上午12:37
 */
public class TestAgent {

	public static void premain(String agentArgs, Instrumentation inst) {
		System.out.println("premain start");
		System.out.println(agentArgs);
	}

}
