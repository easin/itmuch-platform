package com.itmuch.core.util;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import com.itmuch.core.exception.ParamInvalidException;

public class StringUtil {
    public static String YESTERDAY = "昨日";
    public static String BEFORE_YESTERDAY = "前日";
    public static String LAST_WEEK = "上周同期";
    public static String UNKNOWN = "未知时间";

    /**
     * 为日期起别名
     * @param date
     * @return
     */
    public static String aliasDate(Date date) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        if (date == null) {
            return UNKNOWN;
        } else {
            String dateString = format.format(date);

            if (parseDateString(format, -1).equals(dateString)) {
                return YESTERDAY;
            } else if (parseDateString(format, -2).equals(dateString)) {
                return BEFORE_YESTERDAY;
            } else if (parseDateString(format, -7).equals(dateString)) {
                return LAST_WEEK;
            } else {
                return dateString;
            }
        }

    }

    /**
     * 通过偏移量以及指定的format，将该天日期格式化，例如offset=-1，则可将昨天格式化
     * @param format 日期格式化
     * @param offset 偏移量
     * @return
     */
    public static String parseDateString(SimpleDateFormat format, int offset) {
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.DATE, offset);
        return format.format(cal.getTime());
    }

    /**
     * 格式化指定时间为指定字符串
     * @param format 格式化
     * @param date 时间
     * @return
     */
    public static String formatDate(SimpleDateFormat format, Date date) {
        if (date == null) {
            return "";
        } else {
            return format.format(date);
        }
    }

    /**
     * 将驼峰式命名的字符串转换为下划线小写方式。如果转换前的驼峰式命名的字符串为空，则返回空字符串。</br>
     * 例如：HelloWorld->helloWorld
     * @param name 转换前的驼峰式命名的字符串
     * @return 转换后下划线小写方式命名的字符串
     */
    public static String underscoreName(String name) {
        // 如果包含_, 那认为已经是下划线命名方式, 不做处理
        if (name.contains("_")) {
            return name;
        }
        StringBuilder result = new StringBuilder();
        if ((name != null) && (name.length() > 0)) {
            // 将第一个字符处理成小写
            result.append(name.substring(0, 1).toLowerCase());
            // 循环处理其余字符
            for (int i = 1; i < name.length(); i++) {
                String s = name.substring(i, i + 1);
                // 在大写字母前添加下划线
                if (s.equals(s.toUpperCase()) && !Character.isDigit(s.charAt(0))) {
                    result.append("_");
                }
                // 其他字符直接转成小写
                result.append(s.toLowerCase());
            }
        }
        return result.toString();
    }

    /**
     * 将下划线大写方式命名的字符串转换为驼峰式。如果转换前的下划线大写方式命名的字符串为空，则返回空字符串。</br>
     * 例如：HELLO_WORLD->HelloWorld
     * @param name 转换前的下划线大写方式命名的字符串
     * @return 转换后的驼峰式命名的字符串
     */
    public static String camelName(String name) {
        StringBuilder result = new StringBuilder();
        // 快速检查
        if ((name == null) || name.isEmpty()) {
            // 没必要转换
            return "";
        } else if (!name.contains("_")) {
            // 不含下划线，仅将首字母小写
            return name.substring(0, 1).toLowerCase() + name.substring(1);
        }
        // 用下划线将原始字符串分割
        String camels[] = name.split("_");
        for (String camel : camels) {
            // 跳过原始字符串中开头、结尾的下换线或双重下划线
            if (camel.isEmpty()) {
                continue;
            }
            // 处理真正的驼峰片段
            if (result.length() == 0) {
                // 第一个驼峰片段，全部字母都小写
                result.append(camel.toLowerCase());
            } else {
                // 其他的驼峰片段，首字母大写
                result.append(camel.substring(0, 1).toUpperCase());
                result.append(camel.substring(1).toLowerCase());
            }
        }
        return result.toString();
    }

    public static Integer[] parseStringToIntegerArray(String param) {
        if (param != null) {
            String[] split = param.split(",");

            if ((split != null) && (split.length > 0)) {
                Integer[] ret = new Integer[split.length];
                for (int i = 0; i < split.length; i++) {
                    try {
                        ret[i] = Integer.parseInt(split[i]);
                    } catch (NumberFormatException e) {
                        throw new ParamInvalidException(new StringBuffer("参数").append(split[i]).append("无法转换成整型数字").toString());
                    }
                }
                return ret;
            }
        }
        return null;
    }
}
