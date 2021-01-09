package com.kubrick.sbt.web.service.impl;

import com.kubrick.sbt.web.dao.OrganizationDao;
import com.kubrick.sbt.web.domain.entity.Organization;
import com.kubrick.sbt.web.service.OrganizationService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @author k
 * @version 1.0.0
 * @ClassName OrganizationService
 * @description: TODO
 * @date 2020/12/13 下午7:28
 */
@Slf4j
@Service
public class OrganizationServiceImpl implements OrganizationService {

	@Autowired
	private OrganizationDao organizationDao;

	@Override
	public List<Long> list(Integer organizationId) {
		List<Organization> organizations = organizationDao.list();
		List<Long> result = new ArrayList<>();
		recursiveOrganizations(result, organizations, organizationId);
		return result;
	}

	public List<Long> recursiveOrganizations(List<Long> result,
			List<Organization> organizations, Integer organizationId) {

		for (Organization og : organizations) {
			Integer parentId = og.getPid();
			Long id = og.getId();
			if (parentId.equals(organizationId)) {
				recursiveOrganizations(result, organizations,
						Integer.valueOf(String.valueOf(id)));
				result.add(id);
			}
		}
		return result;
	}

}
