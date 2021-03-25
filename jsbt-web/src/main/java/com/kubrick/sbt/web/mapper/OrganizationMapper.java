package com.kubrick.sbt.web.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.kubrick.sbt.web.domain.entity.Organization;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author k
 * @version 1.0.0
 * @ClassName OrganizationMapper
 * @description: TODO
 * @date 2021/3/21 下午4:47
 */
@Component("organizationMapper")
public interface OrganizationMapper extends BaseMapper<Organization> {

	List<Organization> list();

}
