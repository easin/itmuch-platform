package com.itmuch.core.util;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;

public class SubjectUtil {

    public static User getUser() {
        Subject subject = SecurityUtils.getSubject();
        User principal = (User) subject.getPrincipal();
        if (principal != null) {
            return principal;
        } else {
            principal = new User();
            principal.setId(0);
        }
        return principal;

    }
}
