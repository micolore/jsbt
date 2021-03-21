package com.kubrick.sbt.web.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.kubrick.sbt.web.domain.entity.Menu;
import com.kubrick.sbt.web.domain.entity.Role;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author k
 * @version 1.0.0
 * @ClassName MenuMapper
 * @description: TODO
 * @date 2021/3/21 下午4:39
 */
@Component("menuMapper")
public interface MenuMapper extends BaseMapper<Menu> {

    /**
     * 根据角色获取菜单列表
     * @param roles
     * @return
     */
    List<Menu> getRoleMenuByRoles(@Param("roles") List<Role> roles);
}
