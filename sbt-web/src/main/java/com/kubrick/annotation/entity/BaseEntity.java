package com.kubrick.annotation.entity;

import lombok.Data;
import lombok.ToString;

import java.io.Serializable;

/**
 * @author k
 * 说明 :实体基础类,包含共有属性
 */
@Data
@ToString
public abstract class BaseEntity implements Serializable {

	private static final long serialVersionUID = 4034437877924885763L;
	protected Long id;
	protected Integer state;
	protected String creator;
	protected String createTime;
	protected String updator;
	protected String updateTime;
	protected Boolean deleted=false;

}
