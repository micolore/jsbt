package com.kubrick.sbt.web.test.api;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.RestTemplate;

/**
 * @author k
 * @version 1.0.0 @ClassName ApiTest
 * @description: TODO
 * @date 2021/1/5 下午11:41
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class ApiTest {

  @Autowired private RestTemplate restTemplate;

  /**
   * test restTemplate GET
   */
  @Test
  public void apiReqGet() {
    String url = "http://www.baiudu.com";
    Object result = restTemplate.getForObject(url, String.class);
    System.out.println(result);
  }
}
