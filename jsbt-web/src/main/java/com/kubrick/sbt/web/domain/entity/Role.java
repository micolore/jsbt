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
@TableName("sys_role")
@EqualsAndHashCode(callSuper = true)
public class Role extends BaseEntity {

	private static final long serialVersionUID = -3572463217368803762L;

	private String roleName;

	private Integer dataScope;

}
