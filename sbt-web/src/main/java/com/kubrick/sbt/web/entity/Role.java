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
public class Role extends BaseEntity {

	private static final long serialVersionUID = -3572463217368803762L;

	private String roleName;

	private Integer dataScope;

}
