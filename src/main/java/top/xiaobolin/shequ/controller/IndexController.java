package top.xiaobolin.shequ.controller;

import com.vdurmont.emoji.EmojiParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import top.xiaobolin.shequ.dto.Fenye;
import top.xiaobolin.shequ.dto.QuestionDTO;
import top.xiaobolin.shequ.service.QuetionService;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * @author：xiaobolin
 * @date 2020/7/7/0007 - 16:52
 */

@Controller
public class IndexController {

    @Autowired
    private QuetionService quetionService;
    /*
     * 访问页面
     * */
    @GetMapping("/")
    public String index(HttpServletRequest request,
                        Model model,
                        @RequestParam(name = "page",defaultValue = "1") Integer page,
                        @RequestParam(name = "size",defaultValue = "4") Integer size
                       ) {
        int kaishi = (page - 1) * size;
        Fenye fenye = new Fenye();
        Integer zongShu = quetionService.zongShu();
        if (zongShu != null || zongShu != 0) {
            int zongYeShu = zongShu % size == 0 ? zongShu / size : zongShu / size + 1;
            fenye.setZongYeShu(zongYeShu);
        }
        fenye.setZongShu(zongShu);
        fenye.setDangQIanYe(page);
        model.addAttribute("dangQianYe", page);
        model.addAttribute("zongYeShu", fenye.getZongYeShu());
        if (page != null || page != 0) {
            List<QuestionDTO> questionList = quetionService.list(kaishi, size);
            model.addAttribute("question", questionList);
        }
        return "index";
    }
    @GetMapping("/fenye")
    public String fenye(
            HttpServletRequest request,
            Model model,
            @RequestParam(name = "page",defaultValue = "1") Integer page,
            @RequestParam(name = "size",defaultValue = "4") Integer size
    ){
        Fenye fenye= new Fenye();
        int kaishi = (page - 1) * size;
        fenye.setDangQIanYe(page);
        if (page!=null || page!=0){
            List<QuestionDTO> questionList = quetionService.list(kaishi,size);
            model.addAttribute("question",questionList);
        }
        Integer zongShu = quetionService.zongShu();
        if (zongShu!=null || zongShu!=0){
            int zongYeShu = zongShu % size == 0 ? zongShu / size : zongShu / size + 1;
            fenye.setZongYeShu(zongYeShu);
        }
        model.addAttribute("zongYeShu",fenye.getZongYeShu());
        model.addAttribute("dangQianYe",fenye.getDangQIanYe());
        model.addAttribute("index","yes");
        return "index";
    }
    @GetMapping("/tuichu")
    public String tuichu(
            HttpServletRequest request,
            HttpServletResponse response
    ){
        Cookie cookie = new Cookie("token", null);
        cookie.setMaxAge(0);
        cookie.setPath("/");
        response.addCookie(cookie);
        request.getSession().removeAttribute("user");
        System.out.println("退出成功！！！！");
        return "redirect:/";
    }
}
