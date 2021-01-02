package com.kubrick.sbt.es;


import org.elasticsearch.action.admin.indices.create.CreateIndexRequest;
import org.elasticsearch.action.admin.indices.create.CreateIndexResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
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
		//1. 创建索引请求
		CreateIndexRequest request = new CreateIndexRequest("move");
		//2. 客户端执行请求 IndicesClient,请求后获得响应
		CreateIndexResponse response = restHighLevelClient.indices().create(request, RequestOptions.DEFAULT);
		//输出是否创建成功
		System.out.println(response.isAcknowledged());
	}
}
