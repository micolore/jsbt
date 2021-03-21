package com.kubrick.sbt.web.domain.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

/**
 * @author k
 */
@Data
@ToString
@EqualsAndHashCode(callSuper = true)
@TableName("t_menu")
public class Menu extends BaseEntity {

	private static final long serialVersionUID = 4245833784962015367L;

	private String menuName;

	private String menuUrl;

	private String menuCode;

	private Long parentId;

	private Integer menuType;

	private Integer orderNum;

}
