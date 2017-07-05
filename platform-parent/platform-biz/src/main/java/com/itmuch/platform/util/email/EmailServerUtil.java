package com.itmuch.platform.util.email;

import java.util.Map;

import com.google.common.collect.Maps;

public class EmailServerUtil {
    private static Map<String, String> map = Maps.newHashMap();
    static {
        map.put("qq.com", "http://mail.qq.com");
        map.put("gmail.com", "http://mail.google.com");
        map.put("sina.com", "http://mail.sina.com.cn");
        map.put("163.com", "http://mail.163.com");
        map.put("126.com", "http://mail.126.com");
        map.put("yeah.net", "http://www.yeah.net/");
        map.put("sohu.com", "http://mail.sohu.com/");
        map.put("tom.com", "http://mail.tom.com/");
        map.put("sogou.com", "http://mail.sogou.com/");
        map.put("139.com", "http://mail.10086.cn/");
        map.put("hotmail.com", "http://www.hotmail.com");
        map.put("live.com", "http://login.live.com/");
        map.put("live.cn", "http://login.live.cn/");
        map.put("live.com.cn", "http://login.live.com.cn");
        map.put("189.com", "http://webmail16.189.cn/webmail/");
        map.put("yahoo.com.cn", "http://mail.cn.yahoo.com/");
        map.put("yahoo.cn", "http://mail.cn.yahoo.com/");
        map.put("eyou.com", "http://www.eyou.com/");
        map.put("21cn.com", "http://mail.21cn.com/");
        map.put("188.com", "http://www.188.com/");
        map.put("foxmail.coom", "http://www.foxmail.com");
    }

    /**
     * 通过用户邮箱, 获得邮箱服务器
     * @param email 用户邮箱
     * @return 邮箱服务器
     */
    public static String queryEmailServerByEmail(String email) {
        if (email.contains("@")) {
            String serTemp = email.split("@")[1];
            String string = map.get(serTemp);
            if (string != null) {
                return string;
            }
        }
        return "";
    }
}
