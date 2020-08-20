package top.xiaobolin.shequ.controller;

import com.alibaba.fastjson.JSON;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import top.xiaobolin.shequ.dto.QuestionDTO;
import top.xiaobolin.shequ.model.Question;
import top.xiaobolin.shequ.model.User;
import top.xiaobolin.shequ.service.QuetionService;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @author：xiaobolin
 * @date 2020/8/17/0017 - 00:42
 */
@Controller
public class XiangxiController {
    @Autowired
    private QuetionService quetionService;

    @GetMapping("/wenti")
    public String Xiangxiwenti(
            @RequestParam(name = "id")Integer id,
            Model model,
            HttpServletRequest request
    ){
        Question wenZhangYe = quetionService.getById(id);
        User user = quetionService.getUser(id);
        model.addAttribute("user",user);
        model.addAttribute("usertwo",request.getSession().getAttribute("user"));
        model.addAttribute("wenZhangYe",wenZhangYe);
        model.addAttribute("wenzhang",wenZhangYe.getDescription());
    return "Wentiyemian";
    }

}
