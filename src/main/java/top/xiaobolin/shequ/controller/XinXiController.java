package top.xiaobolin.shequ.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import top.xiaobolin.shequ.mapper.QuesstionMapper;
import top.xiaobolin.shequ.mapper.UserMapper;

import javax.servlet.http.HttpServletRequest;
import java.math.BigInteger;

/**
 * @author：xiaobolin
 * @date 2020/8/30/0030 - 00:40
 */
@Controller
public class XinXiController {
    @Autowired
    QuesstionMapper quesstionMapper;

    @Autowired
    UserMapper userMapper;

    @PostMapping("/xinxi")
    public String XinXi(
        @RequestParam("QQ")String QQ,
        @RequestParam("Email")String Email,
        @RequestParam("Phone")String Phone,
        HttpServletRequest request
    ){
        String eID = (String)request.getSession().getAttribute("eID");
        String ifok = "1";
        quesstionMapper.updatageren(eID, QQ, Phone, Email);
        int selectcishu = userMapper.selectcishu(eID);
        request.getSession().setAttribute("cishu",selectcishu);
        return "redirect:/";
    }
}
