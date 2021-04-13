package com.kubrick.sbt.es.api;

import com.kubrick.sbt.es.service.EsService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author k
 * @version 1.0.0 @ClassName SelectApi
 * @description: TODO
 * @date 2021/4/13 下午8:03
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/es")
public class SelectApi {

  private final EsService esService;

  /** @return */
  @RequestMapping(value = "/getIndexInfo", method = RequestMethod.GET)
  public String getIndexInfo() throws Exception {

    String list = esService.selectIndexInfo("61470452106@c.us", "61470068923@c.us");

    return list;
  }
}
