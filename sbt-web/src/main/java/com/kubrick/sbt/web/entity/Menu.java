package com.kubrick.sbt.web.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

/**
 * @author k
 */
@Data
@ToString
@EqualsAndHashCode(callSuper = true)
public class Menu extends BaseEntity {

	private static final long serialVersionUID = 4245833784962015367L;

	private String menuName;

	private String menuUrl;

	private String menuCode;

	private Long parentId;

	private Integer menuType;

	private Integer orderNum;

}
