package top.xiaobolin.shequ.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import top.xiaobolin.shequ.mapper.QuesstionMapper;

import javax.servlet.http.HttpServletRequest;

/**
 * @author：xiaobolin
 * @date 2020/8/30/0030 - 00:40
 */
@Controller
public class XinXiController {
    @Autowired
    QuesstionMapper quesstionMapper;

    @PostMapping("/xinxi")
    public String XinXi(
        @RequestParam("QQ")int QQ,
        @RequestParam("Email")String Email,
        @RequestParam("Phone")int Phone,
        HttpServletRequest request
    ){
        System.out.println(QQ);
        System.out.println(Email);
        System.out.println(Phone);
        String eID = (String)request.getSession().getAttribute("eID");
        quesstionMapper.updatageren(QQ,Email,Phone,eID);
        return "redirect:/";
    }
}
