package com.kubrick.sbt.log;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author k
 * @version 1.0.0
 * @ClassName com.moppo.common.server.component.com.kubrick.sbt.log.WebLog
 * @description: TODO
 * @date 2020/12/6 下午2:13
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class WebLog {

	private String threadId;

	private String threadName;

	private String ip;

	private String url;

	private String httpMethod;

	private String classMethod;

	private Object requestParams;

	private Object result;

	private Long timeCost;

	private String os;

	private String browser;

	private String userAgent;

}
