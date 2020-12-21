package com.kubrick.sbt.distributed.test;

import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooKeeper;
import org.junit.Test;

import java.io.IOException;

/**
 * @author k
 * @version 1.0.0
 * @ClassName com.kubrick.sbt.distributed.com.kubrick.sbt.distributed.test.ZookeeperDemo
 * @description: TODO
 * @date 2020/12/22 上午1:46
 */
public class ZookeeperDemo {

	@Test
	public void connectZkCluster()
			throws IOException, KeeperException, InterruptedException {

		// 构造方法
		// ZooKeeper(String connectString, int sessionTimeout, Watcher watcher)

		// 匿名对象形式
		ZooKeeper zooKeeper = new ZooKeeper(
				"192.168.1.3:2181,192.168.1.4:2181,192.168.1.5:2181", 20000,
				new Watcher() {
					@Override
					public void process(WatchedEvent watchedEvent) {
						// 发生变更的节点路径
						String path = watchedEvent.getPath();
						System.out.println("path:" + path);

						// 通知状态
						Watcher.Event.KeeperState state = watchedEvent.getState();
						System.out.println("KeeperState:" + state);

						// 事件类型
						Watcher.Event.EventType type = watchedEvent.getType();
						System.out.println("EventType:" + type);
					}
				});

		// 关闭连接
		zooKeeper.close();

		// Lamdba形式
		ZooKeeper zk = new ZooKeeper("192.168.1.3:2181,192.168.1.4:2181,192.168.1.5:2181",
				20000, watchedEvent -> {
					// 发生变更的节点路径
					String path = watchedEvent.getPath();
					System.out.println("path:" + path);

					// 通知状态
					Watcher.Event.KeeperState state = watchedEvent.getState();
					System.out.println("KeeperState:" + state);

					// 事件类型
					Watcher.Event.EventType type = watchedEvent.getType();
					System.out.println("EventType:" + type);
				});

		zk.close();
	}

}
