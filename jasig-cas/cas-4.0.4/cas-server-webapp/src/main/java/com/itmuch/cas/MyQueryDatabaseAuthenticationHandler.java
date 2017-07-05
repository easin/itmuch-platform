/*
 * Licensed to Jasig under one or more contributor license
 * agreements. See the NOTICE file distributed with this work
 * for additional information regarding copyright ownership.
 * Jasig licenses this file to you under the Apache License,
 * Version 2.0 (the "License"); you may not use this file
 * except in compliance with the License.  You may obtain a
 * copy of the License at the following location:
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */
package com.itmuch.cas;

import java.security.GeneralSecurityException;
import java.util.List;
import java.util.Map;

import javax.security.auth.login.AccountNotFoundException;
import javax.security.auth.login.FailedLoginException;
import javax.validation.constraints.NotNull;

import org.apache.log4j.Logger;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.jasig.cas.adaptors.jdbc.AbstractJdbcUsernamePasswordAuthenticationHandler;
import org.jasig.cas.authentication.HandlerResult;
import org.jasig.cas.authentication.PreventedException;
import org.jasig.cas.authentication.UsernamePasswordCredential;
import org.jasig.cas.authentication.principal.SimplePrincipal;

/**
 * 用于数据库认证
 * 参考文档: http://blog.csdn.net/feng27156/article/details/38060257
 * @author zhouli
 */
public class MyQueryDatabaseAuthenticationHandler extends AbstractJdbcUsernamePasswordAuthenticationHandler {
    private static Logger logger = Logger.getLogger(MyQueryDatabaseAuthenticationHandler.class);

    @NotNull
    private String sql;

    @Override
    protected final HandlerResult authenticateUsernamePasswordInternal(final UsernamePasswordCredential credential) throws GeneralSecurityException,
            PreventedException {

        final String username = credential.getUsername();
        logger.info("get sql  " + this.sql);

        List<Map<String, Object>> users = this.getJdbcTemplate().queryForList(this.sql, username);
        if (users.isEmpty()) {
            throw new AccountNotFoundException(username + " not found with SQL query");
        } else if (users.size() == 1) {
            Map<String, Object> user = users.get(0);
            String salt = (String) user.get("salt");

            final String encryptedPassword = new Md5Hash(credential.getPassword(), salt).toString();

            if (!encryptedPassword.equals(user.get("password"))) {
                throw new FailedLoginException("Password does not match value on record.");
            }

            return this.createHandlerResult(credential, new SimplePrincipal(username), null);
        } else {
            throw new FailedLoginException("Multiple records found for " + username);
        }
    }

    /**
     * @param sql The sql to set.
     */
    public void setSql(final String sql) {
        this.sql = sql;
    }
}
