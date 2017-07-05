package com.itmuch.platform.shiro;

import java.util.List;

import javax.annotation.Resource;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.itmuch.core.util.SubjectUtil;
import com.itmuch.platform.admin.domain.User;
import com.itmuch.platform.admin.service.RoleService;
import com.itmuch.platform.admin.service.UserService;

public class ShiroDbRealm extends AuthorizingRealm {
    @Resource
    private UserService userService;
    @Resource
    private RoleService roleService;

    private static final Logger LOGGER = LoggerFactory.getLogger(ShiroDbRealm.class);

    /**
     * 授权查询回调函数, 进行鉴权但缓存中无用户的授权信息时调用.
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {

        SimpleAuthorizationInfo auth = new SimpleAuthorizationInfo();

        // 1. 通过用户id,查询用户具有的角色(查询sys_admin_role)
        Integer id = SubjectUtil.getUser().getId();
        // 如果是超级管理员, 则拥有所有权限, 不受角色约束
        List<String> permissions = null;
        if (1 == id) {
            permissions = this.userService.selectAllPermissions();
        }
        // 并非超管
        else {
            List<Integer> roleIds = this.userService.selectRoleIdListByUserId(id);
            if ((roleIds != null) && !roleIds.isEmpty()) {
                // 2. 通过角色id, 查询角色具有的权限
                permissions = this.userService.selectPermissionsByRoleIds(roleIds);

            }
        }
        if ((permissions != null) && !permissions.isEmpty()) {
            auth.addStringPermissions(permissions);
            return auth;
        }
        return null;
    }

    /**
     * 认证回调函数,登录时调用.
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        UsernamePasswordToken token = (UsernamePasswordToken) authenticationToken;
        String username = token.getUsername();
        LOGGER.info("用户:{}尝试登陆.", username);

        User user = this.userService.selectByUsername(username);

        if (user != null) {

            com.itmuch.core.util.User user2 = new com.itmuch.core.util.User();
            user2.setUsername(user.getUsername());
            user2.setId(user.getId());
            SimpleAuthenticationInfo info = new SimpleAuthenticationInfo(user2, user.getPassword(), ByteSource.Util.bytes(user.getSalt()),
                    this.getName());

            return info;
        } else {
            return null;
        }

    }

}
