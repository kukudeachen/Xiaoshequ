package top.xiaobolin.shequ.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpServletRequest;
import javax.swing.text.html.HTML;

/**
 * @author：xiaobolin
 * @date 2020/10/7/0007 - 15:05
 */
@Controller
@RequestMapping("/mail")
public class MailController {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private JavaMailSender mailSender;

    @Value("${spring.mail.username}")
    private  String username;

    @RequestMapping("/send")
    private ModelAndView sendMail(
            @RequestParam("mail")String mail,
            @RequestParam("href")String href,
            HttpServletRequest request
    ) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom(username);
        message.setTo(mail);
        message.setSubject("小柏林社区问题求助");
        message.setText("你好，小柏林社区发来的邮件，我有一个问题需要你来解答。\n"+href);
        try {
            mailSender.send(message);
            logger.info("邮件已发送。");
        } catch (Exception e) {
            logger.error("发送邮件时发生异常了！", e);
        }
        int id = (int)request.getSession().getAttribute("id");
        ModelAndView model = new ModelAndView("redirect:/wenti?id="+id);
        return model;
    }
}