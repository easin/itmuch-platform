package com.itmuch.platform.util.common;

public class StringUtil {

    /**
     * 随机生成count位个整数, 例如6位,主要用于做验证码
     * @param count 字符串长度
     * @return 证书组成的字符串
     */
    public static String radomString(Integer count) {
        String string = "";
        for (int i = 0; i < count; i++) {
            int x = (int) (Math.random() * 10);
            string += x;
        }
        return string;
    }

    /**
     * 将部分字符变为****
     * @param string 入参 字符串
     * @param type 类型 例如手机号/邮箱
     * @return 转换后的字符串
     */
    public static String hideSomeString(String string, Type type) {
        // 邮箱
        if (Type.EMAIL.equals(type)) {
            String s = string.split("@")[1];
            return string.substring(0, 3) + "****@" + s;
        }
        // 手机号
        else if (Type.MOBILE.equals(type)) {
            return string.substring(0, 3) + "****" + string.substring(string.length() - 4);
        }
        // 其他情况
        else {
            return null;
        }
    }

    public enum Type {
        MOBILE, EMAIL;
    }
}
