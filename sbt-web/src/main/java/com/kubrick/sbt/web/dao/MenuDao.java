package com.kubrick.sbt.web.dao;

import java.util.List;

import com.kubrick.sbt.web.entity.Menu;
import com.kubrick.sbt.web.entity.Role;
import org.apache.ibatis.annotations.Param;


/**
 * @author k
 */
public interface MenuDao {

    /**
     * 根据角色获取菜单列表
     *
     * @param roles
     * @return
     */
    List<Menu> getRoleMenuByRoles(@Param("roles") List<Role> roles);

}
