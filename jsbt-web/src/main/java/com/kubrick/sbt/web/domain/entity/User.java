package com.kubrick.sbt.web.domain.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author k
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("sys_user")
public class User extends BaseEntity {

	private static final long serialVersionUID = -9005214545793249373L;

	private Long id;

	private String username;

	private String password;

	private Integer organization;

}
