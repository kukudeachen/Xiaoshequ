package top.xiaobolin.shequ.controller;

import com.vdurmont.emoji.EmojiParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import top.xiaobolin.shequ.mapper.QuesstionMapper;
import top.xiaobolin.shequ.model.Huifu;
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

    @Autowired
    private QuesstionMapper quesstionMapper;

    @GetMapping("/wenti")
    public String Xiangxiwenti(
            @RequestParam(name = "id")Integer id,
            Model model,
            HttpServletRequest request
    ){
        int i = quetionService.seletView(id);
        int i1 = i+1;
        quesstionMapper.updataView(id,i1);
        request.getSession().setAttribute("id",id);
        Question wenZhangYe = quetionService.getById(id);
        User user = quetionService.getUser(id);
        List<Huifu> huifuone = quetionService.getHuifuone(id);
        if (wenZhangYe.getAccountId().equals(request.getSession().getAttribute("eID"))){
            model.addAttribute("upBianJi","yes");
        }
        model.addAttribute("huifuone",huifuone);
        model.addAttribute("user",user);
        model.addAttribute("usertwo",request.getSession().getAttribute("user"));
        model.addAttribute("wenZhangYe",wenZhangYe);
        model.addAttribute("wenzhang",wenZhangYe.getDescription());
    return "Wentiyemian";
    }
    @GetMapping("/huifu")
    public ModelAndView huifu(
            @RequestParam("huifuone")String huifuone,
            HttpServletRequest request
    ){
        Huifu huifu = new Huifu();
        User user = (User)request.getSession().getAttribute("user");
        int id = (int)request.getSession().getAttribute("id");
        String name = EmojiParser.parseToAliases(user.getName());
        huifu.setUserName(name);
        huifu.setUserTou(user.getFigureurlQq());
        String toAliases = EmojiParser.parseToAliases(huifuone);
        huifu.setHuiFuBody(toAliases);
        huifu.setChengJieShang(id);
        huifu.setChengJieXia((int) System.currentTimeMillis());
        huifu.setGmtCreate(System.currentTimeMillis());
        huifu.setGmtModified(huifu.getGmtCreate());
        quesstionMapper.huifuone(huifu);
        int i = quesstionMapper.cpls(id);
        int i1 = i + 1;
        quesstionMapper.xpls(i1,id);
        ModelAndView model = new ModelAndView("redirect:/wenti?id="+id);
        return model;
    }
}
