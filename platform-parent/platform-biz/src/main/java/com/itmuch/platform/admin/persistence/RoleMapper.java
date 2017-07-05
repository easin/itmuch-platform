package com.itmuch.platform.admin.persistence;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.itmuch.core.persistence.BaseMapper;
import com.itmuch.platform.admin.domain.Role;

public interface RoleMapper extends BaseMapper<Role> {

    /**
     * 查询所有
     * @return 所有用户
     */
    List<Role> listAll();

    Integer insertRoleMenu(@Param("roleId") Integer roleId, @Param("menuIds") List<Integer> menuIds);

    /**
     * 通过角色id, 查询菜单id列表
     * @param roleId 角色id
     * @return sys_menu.id
     */
    List<Integer> selectMenuIdsByRoleId(Integer roleId);

    /**
     * 通过role_id, 删除中间表sys_role_menu的内容
     * @param id sys_role.id
     */
    void deleteRoleMenuById(Integer id);

    /**
     * 通过角色id, 查询该角色id下的用户数(查询sys_admin_role)
     * @param roleId 角色id
     * @return 用户数
     */
    Integer selectCountAdminIdByRoleId(Integer roleId);
}
