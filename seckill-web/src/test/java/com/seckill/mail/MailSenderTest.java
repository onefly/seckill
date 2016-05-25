package com.seckill.mail;

import org.apache.velocity.app.VelocityEngine;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.ui.velocity.VelocityEngineUtils;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by caozhifei on 2016/5/24.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:spring/spring-mail.xml"})
public class MailSenderTest {
    @Autowired
    private JavaMailSender mailSender;
    @Autowired
    private VelocityEngine velocityEngine;
    @Test
    public void testSimpleMail(){
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("cao_zhifei@sina.com");
        message.setTo("529352479@qq.com");
        message.setSubject("spring send mail title2 标题");
        message.setText("spring send mail content success");
        mailSender.send(message);
    }
    @Test
    public void testTemplateMail() throws MessagingException {
        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message);
        helper.setFrom("cao_zhifei@sina.com");
        helper.setTo("529352479@qq.com");
        helper.setSubject("spring send template mail title 标题");
        Map<String, Object> model = new HashMap<String, Object>();
        model.put("user","testuser");
        model.put("text","template text !");
        String text = VelocityEngineUtils.mergeTemplateIntoString(velocityEngine,"template/hello.vm",model);
        helper.setText(text,true);
        mailSender.send(message);
    }
    @Test
    public void testTemplateMail2() throws MessagingException {
        MimeMessagePreparator preparator = new MimeMessagePreparator() {
            public void prepare(MimeMessage mimeMessage) throws Exception {
                MimeMessageHelper message = new MimeMessageHelper(mimeMessage);
                message.setTo("529352479@qq.com");
                message.setFrom("cao_zhifei@sina.com"); // could be parameterized...
                message.setSubject("邮件发送测试");
                Map model = new HashMap();
                model.put("user", "tomcat");
                model.put("text","template text !");
                String text = VelocityEngineUtils.mergeTemplateIntoString(
                        velocityEngine, "template/hello.vm", model);
                message.setText(text, true);
            }
        };
        this.mailSender.send(preparator);
    }
}
