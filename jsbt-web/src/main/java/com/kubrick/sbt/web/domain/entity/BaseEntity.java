package com.kubrick.sbt.web.domain.entity;

import lombok.Data;
import lombok.ToString;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * @author k 说明 :实体基础类,包含共有属性
 */
@Data
@ToString
public abstract class BaseEntity implements Serializable {

    private static final long serialVersionUID = 4034437877924885763L;

    protected Long id;

    protected Integer status;

    protected LocalDateTime createAt;

    protected LocalDateTime updateAt;

    protected Long createBy;

    protected Long updateBy;

    protected Long deleteBy;

    protected LocalDateTime deleteAt;

}
