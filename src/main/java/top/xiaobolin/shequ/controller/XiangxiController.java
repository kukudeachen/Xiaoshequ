package top.xiaobolin.shequ.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import top.xiaobolin.shequ.dto.QuestionDTO;
import top.xiaobolin.shequ.service.QuetionService;

/**
 * @author：xiaobolin
 * @date 2020/8/17/0017 - 00:42
 */
@Controller
public class XiangxiController {
    @Autowired
    private QuetionService quetionService;

    @GetMapping("/wenti/{id}")
    public String Xiangxiwenti(
            @PathVariable(name = "id") Integer id,
            Model model
    ){

    QuestionDTO questionDTO = quetionService.getById(id);
    model.addAttribute("question",questionDTO);
    return "Wentiyemian";
    }
    @GetMapping("/Wentiyemian")
    public String wenti(){
        return "Wentiyemian";
    }
}
