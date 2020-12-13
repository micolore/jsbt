package com.kubrick.sbt.web.dao;

import com.kubrick.sbt.web.entity.Organization;

import java.util.List;

/**
 * @author k
 * @version 1.0.0
 * @ClassName OrganizationDao
 * @description: TODO
 * @date 2020/12/13 下午7:17
 */
public interface OrganizationDao {

    List<Organization> list();
}
