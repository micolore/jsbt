package com.kubrick.sbt.es;


import org.elasticsearch.action.admin.indices.delete.DeleteIndexRequest;
import org.elasticsearch.action.support.master.AcknowledgedResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.client.indices.CreateIndexRequest;
import org.elasticsearch.client.indices.CreateIndexResponse;
import org.elasticsearch.client.indices.GetIndexRequest;
import org.elasticsearch.common.settings.Settings;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;

/**
 * @author k
 * @version 1.0.0
 * @ClassName ElasticsearchTest
 * @description: TODO
 * @date 2021/1/2 下午5:54
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class ElasticsearchTest {

	@Autowired
	private RestHighLevelClient restHighLevelClient;

	@Test
	public void testCreateIndex() throws IOException {
		CreateIndexRequest request = new CreateIndexRequest("scrm_chat");
		// 分片、备份
		request.settings(Settings.builder().put("index.number_of_shards", 3).put("index.number_of_replicas", 2));
		CreateIndexResponse createIndexResponse = restHighLevelClient.indices().create(request, RequestOptions.DEFAULT);
		boolean acknowledged = createIndexResponse.isAcknowledged();//指示是否所有节点都已确认请求
		boolean shardsAcknowledged = createIndexResponse.isShardsAcknowledged();//指示是否在超时之前为索引中的每个分片启动了必需的分片副本数
		System.out.println("acknowledged:"+acknowledged);
		System.out.println("shardsAcknowledged:"+shardsAcknowledged);
		System.out.println(createIndexResponse.index());
	}
	@Test
	public void testExistIndex() throws IOException {
		GetIndexRequest request = new GetIndexRequest("scrm_chat");
		boolean exists = restHighLevelClient.indices().exists(request, RequestOptions.DEFAULT);
		System.out.println(exists);
	}
	@Test
	public void testDeleteIndex() throws IOException {
		DeleteIndexRequest request = new DeleteIndexRequest("scrm_chat");
		// 删除
		AcknowledgedResponse delete = restHighLevelClient.indices().delete(request, RequestOptions.DEFAULT);
		System.out.println(delete.isAcknowledged());
	}
}
