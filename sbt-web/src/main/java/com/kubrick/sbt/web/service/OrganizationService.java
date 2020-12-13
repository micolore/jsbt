package com.kubrick.sbt.web.service;

import java.util.List;

/**
 * @author k
 * @version 1.0.0
 * @ClassName OrganizationService
 * @description: TODO
 * @date 2020/12/13 下午7:27
 */
public interface OrganizationService {

    /**
     * list
     *
     * @param organizationId
     * @return
     */
    List<Long> list(Integer organizationId);

}
