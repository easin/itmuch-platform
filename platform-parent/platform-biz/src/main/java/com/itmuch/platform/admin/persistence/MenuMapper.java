package com.itmuch.platform.admin.persistence;

import java.util.List;

import com.itmuch.core.persistence.TreeMapper;
import com.itmuch.platform.admin.domain.Menu;

public interface MenuMapper extends TreeMapper<Menu> {
    /**
     * 查询所有可见菜单
     * @return 菜单列表
     */
    List<Menu> selectAllVisable();

    /**
     * 通过角色id列表, 查询角色可以访问的可见菜单
     * @param roleIds 角色id列表
     * @return 菜单列表
     */
    List<Menu> selectVisableByRoleIds(List<Integer> roleIds);
}
