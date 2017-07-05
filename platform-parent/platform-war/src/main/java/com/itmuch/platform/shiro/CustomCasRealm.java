package com.itmuch.platform.shiro;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.cas.CasAuthenticationException;
import org.apache.shiro.cas.CasRealm;
import org.apache.shiro.cas.CasToken;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.SimplePrincipalCollection;
import org.apache.shiro.util.StringUtils;
import org.jasig.cas.client.authentication.AttributePrincipal;
import org.jasig.cas.client.validation.Assertion;
import org.jasig.cas.client.validation.TicketValidationException;
import org.jasig.cas.client.validation.TicketValidator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.itmuch.core.util.SubjectUtil;
import com.itmuch.core.util.User;
import com.itmuch.platform.admin.service.RoleService;
import com.itmuch.platform.admin.service.UserService;

public class CustomCasRealm extends CasRealm {
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
        if (id == 1) {
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
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        CasToken casToken = (CasToken) token;
        if (token == null) {
            return null;
        }

        String ticket = (String) casToken.getCredentials();
        if (!StringUtils.hasText(ticket)) {
            return null;
        }

        TicketValidator ticketValidator = this.ensureTicketValidator();

        try {
            // contact CAS server to validate service ticket
            Assertion casAssertion = ticketValidator.validate(ticket, this.getCasService());
            // get principal, user id and attributes
            AttributePrincipal casPrincipal = casAssertion.getPrincipal();
            String userId = casPrincipal.getName();
            LOGGER.debug("Validate ticket : {} in CAS server : {} to retrieve user : {}",
                    new Object[] { ticket, this.getCasServerUrlPrefix(), userId });

            Map<String, Object> attributes = casPrincipal.getAttributes();

            String id = (String) attributes.get("id");
            String username = (String) attributes.get("username");

            // refresh authentication token (user id + remember me)
            casToken.setUserId(id);
            String rememberMeAttributeName = this.getRememberMeAttributeName();
            String rememberMeStringValue = (String) attributes.get(rememberMeAttributeName);
            boolean isRemembered = (rememberMeStringValue != null) && Boolean.parseBoolean(rememberMeStringValue);
            if (isRemembered) {
                casToken.setRememberMe(true);
            }
            // create simple authentication info

            User user = new User();
            user.setUsername(username);
            user.setId(Integer.parseInt(id));

            PrincipalCollection principalCollection = new SimplePrincipalCollection(user, this.getName());
            return new SimpleAuthenticationInfo(principalCollection, ticket);
        } catch (TicketValidationException e) {
            throw new CasAuthenticationException("Unable to validate ticket [" + ticket + "]", e);
        }
    }
}
