package com.kubrick.sbt.tools.test.disruptor;

import com.kubrick.sbt.tools.disruptor.Order;
import com.kubrick.sbt.tools.disruptor.OrderFactory;
import com.kubrick.sbt.tools.disruptor.OrderHandler;
import com.lmax.disruptor.RingBuffer;
import com.lmax.disruptor.dsl.Disruptor;
import org.junit.Test;

import java.util.concurrent.Executors;

/**
 * @author k
 * @version 1.0.0
 * @ClassName DisruptorTest
 * @description: TODO
 * @date 2020/12/19 下午11:11
 */
public class DisruptorTest {

	@Test
	public void test1() throws InterruptedException {
		// 创建订单工厂
		OrderFactory orderFactory = new OrderFactory();

		// ringbuffer的大小
		int RINGBUFFER_SIZE = 1024;

		// 创建disruptor
		Disruptor<Order> disruptor = new Disruptor<Order>(orderFactory, RINGBUFFER_SIZE,
				Executors.defaultThreadFactory());

		// 设置事件处理器 即消费者
		disruptor.handleEventsWith(new OrderHandler());

		disruptor.start();

		RingBuffer<Order> ringBuffer = disruptor.getRingBuffer();

		// -------------生产数据
		for (int i = 0; i < 3; i++) {

			long sequence = ringBuffer.next();

			Order order = ringBuffer.get(sequence);

			order.setId(i);

			ringBuffer.publish(sequence);
			System.out.println(Thread.currentThread().getName() + " 生产者发布一条数据:" + sequence
					+ " 订单ID：" + i);
		}

		Thread.sleep(1000);

		disruptor.shutdown();
	}

}
