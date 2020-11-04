package top.xiaobolin.shequ.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

/**
 * @author：xiaobolin
 * @date 2020/10/31/0031 - 23:11
 */
@Service
public class Mailservce {

    @Autowired
    private JavaMailSender mailSender;

    @Value("${spring.mail.username}")
    private  String username;

    public String mail(String mail,String href){
        String ok =null;
        try {
            SimpleMailMessage message = new SimpleMailMessage();
            message.setFrom(username);
            message.setTo(mail);
            message.setSubject("小柏林社区问题求助");
            message.setText("你好，小柏林社区发来的邮件，我有一个问题需要你来解答。\n"+href);
            mailSender.send(message);
            ok="ok";
        } catch (MailException e) {
            e.printStackTrace();
        }
        return ok;
    }
}
