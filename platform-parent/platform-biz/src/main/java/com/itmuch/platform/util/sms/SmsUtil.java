package com.itmuch.platform.util.sms;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.itmuch.platform.util.email.EmailUtil;

@Component
public class SmsUtil {
    private static final Logger LOGGER = LoggerFactory.getLogger(EmailUtil.class);

    @Value("${entinfo.cn.sn}")
    private String sn;
    @Value("${entinfo.cn.pwd}")
    private String pwd;

    public boolean sendMsg(String mobile, String content) {
        try {
            Client client = new Client(this.sn, this.pwd);
            //短信发送  
            String encodedContent = URLEncoder.encode(content, "utf8");
            String res = client.mdsmssend(mobile, encodedContent, "", "", "", "");

            // TODO 待细化返回结果 
            // 根据分析响应报文, 发送成功时是res是类似:301507190388724672的字符串, 而错误码长度都是小于7位的.故而暂时简单写成这样
            if (res.length() > 7) {
                return true;
            } else {
                return false;
            }
        } catch (UnsupportedEncodingException e) {
            LOGGER.error("发送信息发生异常", e);
            return false;
        }
    }
}
