package com.kubrick.sbt.web.domain.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

/**
 * @author k
 * @version 1.0.0
 * @ClassName Organization
 * @description: TODO
 * @date 2020/12/13 下午7:13
 */
@Data
@ToString
@EqualsAndHashCode(callSuper = true)
public class Organization extends BaseEntity {

	private String name;

	private Integer pid;

	private Integer deep;

}
