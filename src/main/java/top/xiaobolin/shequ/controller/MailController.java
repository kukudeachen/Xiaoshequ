package top.xiaobolin.shequ.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import top.xiaobolin.shequ.service.Mailservce;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpServletRequest;
import javax.swing.text.html.HTML;
import javax.websocket.Session;

/**
 * @author：xiaobolin
 * @date 2020/10/7/0007 - 15:05
 */
@Controller
@RequestMapping("/mail")
public class MailController {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    Mailservce mailservce;

    @RequestMapping("/send")
    public ModelAndView sendMail(
            @RequestParam("mail")String mail,
            @RequestParam("href")String href,
            HttpServletRequest request,
            Model model1
    ) {
        int id = (int)request.getSession().getAttribute("id");
        String s = null;
        try {
            String mail1 = mailservce.mail(mail, href);
            System.out.println("mail"+mail1);
            s = mail1;
            logger.info("邮件已发送。");
        } catch (Exception e) {
            logger.error("发送邮件时发生异常了！", e);
        }
        System.out.println("邮控制"+s);
        model1.addAttribute("ok",s);
        ModelAndView model = new ModelAndView("redirect:/wenti?id="+id);
        return model;
    }
}