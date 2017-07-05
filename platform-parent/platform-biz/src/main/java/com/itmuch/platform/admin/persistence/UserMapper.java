package com.itmuch.platform.admin.persistence;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.itmuch.core.persistence.BaseMapper;
import com.itmuch.platform.admin.domain.User;

public interface UserMapper extends BaseMapper<User> {

    /**
     * 查询所有
     * @return 所有用户
     */
    List<User> listAll();

    User selectByUsername(String loginName);

    /**
     * 通过用户id, 查询该用户拥有的角色id列表
     * @param id id
     * @return 角色id列表
     */
    List<Integer> selectRoleIdListByUserId(Integer id);

    /**
     * 通过角色id列表, 查询拥有的权限字符串列表
     * @param roleIds 角色id列表
     * @return 权限字符串列表
     */
    List<String> selectPermissionsByRoleIds(List<Integer> roleIds);

    /**
     * 插入到中间表sys_admin_role 
     * @param adminId sys_admin.id
     * @param roleIds 角色id列表
     */
    void insertUserRole(@Param("userId") Integer userId, @Param("roleIds") List<Integer> roleIds);

    /**
     * 通过用户id, 删除中间表sys_admin_role的内容
     * @param id sys_admin.id
     */
    void deleteUserRoleByUserId(Integer id);

    /**
     * 查询所有的权限字符串, 用于超管授权
     * @return 所有权限字符串
     */
    List<String> selectAllPermissions();

    /**
     * 通过登录名查询用户id
     * @param username 登录名
     * @return 用户id
     */
    Integer selectIdByUsername(String username);

}
