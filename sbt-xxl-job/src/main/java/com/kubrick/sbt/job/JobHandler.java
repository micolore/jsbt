package com.kubrick.sbt.job;

import org.springframework.stereotype.Component;

import com.xxl.job.core.biz.model.ReturnT;
import com.xxl.job.core.handler.IJobHandler;
import com.xxl.job.core.handler.annotation.XxlJob;

/**
 * @author k
 * @version 1.0.0 @ClassName JobHandler
 * @description: xxlJob demo
 * @date 2020/12/8 下午6:48
 */
@Component
public class JobHandler extends IJobHandler {

  @XxlJob("jobHandler")
  @Override
  public ReturnT<String> execute(String s) throws Exception {
    System.out.println("hello xxljob JobHandler");
    return null;
  }
}
