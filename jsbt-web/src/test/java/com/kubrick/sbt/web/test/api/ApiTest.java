package com.kubrick.sbt.web.test.api;

import cn.hutool.json.JSONException;
import cn.hutool.json.JSONObject;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.*;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.util.StringUtils;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

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

  /** test restTemplate GET */
  @Test
  public void apiReqGet() {
    String url = "http://www.baiudu.com";
    Object result = restTemplate.getForObject(url, String.class);
    System.out.println(result);
  }

  @Test
  public void apiReqGetResponseEntity() {
    String url = "http://www.baiudu.com";
    ResponseEntity<String> result = restTemplate.getForEntity(url, String.class);
    System.out.println(result.getStatusCode());
  }

  @Test
  public void apiReqGetParams() {
    Map<String, String> map = new HashMap();
    map.put("start", "1");
    map.put("page", "5");
    String url = "http://www.baiudu.com";
    Object result = restTemplate.getForObject(url, String.class, map);
    System.out.println(result);
  }

  @Test
  public void apiPostObject() {
    RestTemplate restTemplate = new RestTemplate();
    String url = "http://www.baiudu.com";
    HttpHeaders headers = new HttpHeaders();
    headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
    MultiValueMap<String, String> map = new LinkedMultiValueMap<>();
    map.add("email", "844072586@qq.com");
    HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<>(map, headers);
    ResponseEntity<String> response = restTemplate.postForEntity(url, request, String.class);
    System.out.println(response.getBody());
  }

  @Test
  public void apiExchangeTest() throws JSONException {
    RestTemplate restTemplate = new RestTemplate();
    String url = "http://www.baiudu.com";
    HttpHeaders headers = new HttpHeaders();
    headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
    JSONObject jsonObj = new JSONObject();
    jsonObj.put("start", 1);
    jsonObj.put("page", 5);

    HttpEntity<String> entity = new HttpEntity<>(jsonObj.toString(), headers);
    ResponseEntity<JSONObject> exchange =
        restTemplate.exchange(url, HttpMethod.GET, entity, JSONObject.class);
    System.out.println(exchange.getBody());
  }

  /**
   * 加密
   */
  @Test
  public void encrypt() {
    PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
    for (int i = 0; i < 1; ++i) {
      // 每次生成的密码都不一样
      String encryptedPassword = passwordEncoder.encode("123456");
      System.out.println(encryptedPassword);
      System.out.println(passwordEncoder.matches("Passw0rd", encryptedPassword)); // true
      System.out.println(passwordEncoder.matches("Password", encryptedPassword)); // false
    }
  }

  @Test
  public void stringUtilsTest(){
    boolean b = StringUtils.substringMatch("token11111", 0, "token");
    String delete = StringUtils.delete("token11111", "token");
    System.out.println(b);
    System.out.println(delete);

  }
}
