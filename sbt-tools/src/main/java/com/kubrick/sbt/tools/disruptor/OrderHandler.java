package com.kubrick.sbt.tools.disruptor;

import com.lmax.disruptor.EventHandler;

/**
 * @author k
 * @version 1.0.0
 * @ClassName OrderHandler
 * @description: TODO
 * @date 2020/12/19 下午11:10
 */
public class OrderHandler implements EventHandler<Order> {

	@Override
	public void onEvent(Order order, long l, boolean b) throws Exception {

		System.out.println(Thread.currentThread().getName() + " 消费者处理中:" + l);
		order.setInfo("info" + order.getId());
		order.setPrice(Math.random());
	}

}
