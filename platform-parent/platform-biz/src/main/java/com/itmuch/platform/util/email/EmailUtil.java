package com.itmuch.platform.util.email;

import java.util.Map;

import javax.annotation.Resource;
import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;

import com.itmuch.core.ui.freemarker.ShiroTagFreeMarkerConfigurer;

import freemarker.template.Template;

/**
 * 发送邮件 工具类
 * 参考文档: 
 * http://www.blogjava.net/tangzurui/archive/2008/12/08/244953.html
 * http://blog.csdn.net/ajun_studio/article/details/7347644
 * @author Administrator
 *
 */
// TODO 异步发送邮件: http://blog.csdn.net/dr_lf/article/details/6014948
@Component
public class EmailUtil {
    @Resource
    private JavaMailSenderImpl mailSender;
    @Resource
    private ShiroTagFreeMarkerConfigurer freeMarkerConfigurer;
    @Value("${email.from}")
    private String from;

    private static final Logger LOGGER = LoggerFactory.getLogger(EmailUtil.class);

    /** 
     * 生成html模板字符串 
     * @param root 存储动态数据的map 
     * @return 
     */
    private String getMailText(Map<String, Object> root, String templateName) throws Exception {
        String htmlText = "";
        try {
            //通过指定模板名获取FreeMarker模板实例    
            Template tpl = this.freeMarkerConfigurer.getConfiguration().getTemplate(templateName);
            htmlText = FreeMarkerTemplateUtils.processTemplateIntoString(tpl, root);
        } catch (Exception e) {
            throw new Exception("获取邮箱模板失败");
        }
        return htmlText;
    }

    /** 
     * 发送邮件 
     * @param root 存储动态数据的map 
     * @param to 邮件地址 
     * @param subject 邮件主题 
     * @return 发送结果
     * @throws MessagingException 
     */
    public boolean sendMailByTemplate(String to, String subject, Map<String, Object> root, String templateName) {
        String htmlText = null;
        try {
            MimeMessage msg = this.mailSender.createMimeMessage();
            //由于是html邮件，不是mulitpart类型    
            MimeMessageHelper helper = new MimeMessageHelper(msg, false, "utf-8");
            helper.setFrom(this.from);
            helper.setTo(to);
            helper.setSubject(subject);

            //使用模板生成html邮件内容   
            htmlText = this.getMailText(root, templateName);
            helper.setText(htmlText, true);
            this.mailSender.send(msg);
            LOGGER.debug("发送邮件成功, 收信人:{}, 主题:{}, 模板:{}, 内容:{}.", new Object[] { to, subject, templateName, htmlText });
            return true;
        } catch (Exception e) {
            LOGGER.warn("发送邮件失败, 收信人:{}, 主题:{}, 模板:{}, 内容:{}, 异常:{}.", new Object[] { to, subject, templateName, htmlText, e.getMessage() });
            return false;
        }

    }

    /**
     * 发送html格式的邮件
     * @param from 发件人
     * @param to 收件人
     * @param subject 标题
     * @param text 邮箱内容
     */
    public void sendHtmlMessage(String from, String to, String subject, String text) {

        try {
            JavaMailSenderImpl senderImpl = new JavaMailSenderImpl();
            MimeMessage mailMessage = senderImpl.createMimeMessage();
            MimeMessageHelper messageHelper = new MimeMessageHelper(mailMessage, false, "utf-8");

            messageHelper.setTo(to);
            messageHelper.setFrom(from);
            messageHelper.setSubject(subject);
            messageHelper.setText(text, true);

            this.mailSender.send(mailMessage);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
