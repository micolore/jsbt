package com.kubrick.sbt.es.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.kubrick.sbt.es.utils.EsConstant;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.Strings;
import org.elasticsearch.common.unit.TimeValue;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.Operator;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.rest.RestStatus;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.SearchHits;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.springframework.stereotype.Service;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.concurrent.TimeUnit;

/**
 * @author k
 * @version 1.0.0 @ClassName EsService
 * @description: TODO
 * @date 2021/4/13 下午8:08
 */
@Slf4j
@RequiredArgsConstructor
@Service
public class EsService {

  private final RestHighLevelClient restHighLevelClient;
  private final ObjectMapper objectMapper;

  public String selectIndexInfo(String currentWhatsId, String friendWhatsId)
      throws Exception {

    // 1、创建search请求
    SearchRequest searchRequest = new SearchRequest(EsConstant.INDEX);
    searchRequest.types(EsConstant.TYPE);

    // 2、用SearchSourceBuilder来构造查询请求体 ,请仔细查看它的方法，构造各种查询的方法都在这。
    SearchSourceBuilder sourceBuilder = new SearchSourceBuilder();

    // 集合查询
    BoolQueryBuilder queryBuilder = QueryBuilders.boolQuery();

    if (!Strings.isNullOrEmpty(currentWhatsId)) {
      queryBuilder.must(
          QueryBuilders.matchQuery("current_whats_id", currentWhatsId).operator(Operator.AND));
    }
    if (!Strings.isNullOrEmpty(friendWhatsId)) {
      queryBuilder.must(
          QueryBuilders.matchQuery("friend_whats_id", friendWhatsId).operator(Operator.AND));
    }

    sourceBuilder.query(queryBuilder);
    // 设置from确定结果索引以开始搜索的选项。预设为0。
    sourceBuilder.from(0);
    // 设置size用于确定要返回的搜索命中次数的选项。默认为10
    sourceBuilder.size(2000);
    // 设置一个可选的超时时间，以控制允许搜索的时间。
    sourceBuilder.timeout(new TimeValue(60, TimeUnit.SECONDS));

    // 将请求体加入到请求中
    searchRequest.source(sourceBuilder);

    // 3、发送请求
    SearchResponse searchResponse =
        restHighLevelClient.search(searchRequest, RequestOptions.DEFAULT);

    RestStatus status = searchResponse.status();
    log.info(String.valueOf(status.getStatus()));

    // 处理搜索命中文档结果
    SearchHits hits = searchResponse.getHits();
    SearchHit[] searchHits = hits.getHits();
    List<Map> mapList = new ArrayList<>();
    for (SearchHit hit : searchHits) {
      // 取_source字段值
      mapList.add(hit.getSourceAsMap());
    }
    long total = hits.getTotalHits().value;
    log.info("总记录数:" + total);

    List<Map<String, String>> maps = formatMsg(mapList);

    String result = objectMapper.writeValueAsString(maps);
    writeFIle("/1.txt",result);
    log.info("select result:{}", result);
    return result;
  }

  private List<Map<String, String>> formatMsg(List<Map> mapList) {
    ArrayList<Map<String, String>> list = new ArrayList<>();
    for (Map map : mapList) {
      Map<String, String> newMap = new HashMap<>();

      newMap.put("content", (String) map.get("content"));
      newMap.put("current_whats_id", (String) map.get("current_whats_id"));
      newMap.put("friend_whats_id", (String) map.get("friend_whats_id"));
      list.add(newMap);
    }

    return list;
  }

  private List<Map> formatClientMsg(List<Map> mapList) {

    if (mapList.size() > 0) {
      for (Map map : mapList) {
        map.put("msgType", map.get("content_type"));
        map.put("isSend", Objects.equals("1", map.get("action_type").toString()));
        map.put("whatsTime", map.get("send_time"));
        map.put("isChatGroup", Objects.equals(map.get("chat_type").toString(), "2"));
        map.put("msgType", map.get("content_type"));
        map.put("accountId", map.get("user_id"));
        map.put("tenantId", map.get("tenant_id"));
        map.put("msg_id", map.get("msgSvrId"));
        map.put(
            "senderWhatsId",
            Objects.equals(map.get("action_type").toString(), "1")
                ? map.get("current_whats_id").toString()
                : map.get("friend_whats_id").toString());
        map.put(
            "sendTime",
            Objects.equals(map.get("send_time"), null)
                ? ""
                : new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(map.get("send_time")));
        map.put("chatGroup", Objects.equals((boolean) map.get("isChatGroup"), true) ? "群" : "个人");
      }
    }
    return mapList;
  }

  public void writeFIle(String filePath, String content) {

    try {
      File file = new File(filePath);

      BufferedWriter bw1 =
          new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file), "UTF-8"));

      bw1.write(content);
      bw1.newLine();
      bw1.flush();
      bw1.close();

    } catch (Exception e) {
      e.printStackTrace();
    }
  }
}
