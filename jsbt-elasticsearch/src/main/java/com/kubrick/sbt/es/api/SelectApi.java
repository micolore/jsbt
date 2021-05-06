package com.kubrick.sbt.es.api;

import com.kubrick.sbt.es.service.EsService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

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

    //    String list = esService.selectIndexInfo("61470452106@c.us", "61470068923@c.us");
    //    String list = esService.selectIndexInfo("", "");
    LocalDateTime localDateTime = LocalDateTime.of(2020, 02, 01, 0, 0, 0);
    LocalDateTime localDateTimeT = LocalDateTime.of(2020, 02, 01, 23, 59, 59);

    List<Map> list = esService.recordRangeHourTwo(localDateTime, localDateTimeT);

    System.out.println(list);
    return null;
  }
}
