package com.itmuch.platform.common.util;

import java.io.IOException;
import java.util.Map;
import java.util.Properties;

import org.apache.commons.lang3.StringUtils;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.support.PropertiesLoaderUtils;

import com.google.common.collect.Maps;

public class Global {
    private static Properties properties = null;
    static {
        ClassPathResource resource = new ClassPathResource("/param.properties");
        try {
            properties = PropertiesLoaderUtils.loadProperties(resource);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 保存全局属性值
     */
    private static Map<String, String> map = Maps.newHashMap();

    /**
     * 获取配置
     * @see ${fns:getConfig('adminPath')}
     */
    public static String getConfig(String key) {
        String value = map.get(key);
        if (value == null) {
            value = properties.getProperty(key);
            map.put(key, value != null ? value : StringUtils.EMPTY);
        }
        return value;
    }

    /**
     * 获取管理端根路径
     */
    public static String getAdminPath() {
        return getConfig("adminPath");
    }

    /**
     * 获取前端根路径
     */
    public static String getFrontPath() {
        return getConfig("frontPath");
    }

    /**
     * 获取URL后缀
     */
    public static String getUrlSuffix() {
        return getConfig("urlSuffix");
    }
}
