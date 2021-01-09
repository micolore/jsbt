package com.kubrick.sbt.es.domain;

import lombok.Data;
import lombok.ToString;
import org.springframework.data.elasticsearch.annotations.Document;

/**
 * @author k
 * @version 1.0.0
 * @ClassName Chat
 * @description: TODO
 * @date 2021/1/3 下午1:04
 */
@Data
@ToString
public class Chat {

	private Integer id;

	private String content;

	private Long chatTime;

	private String currentAccount;

	private String friendAccount;

}
