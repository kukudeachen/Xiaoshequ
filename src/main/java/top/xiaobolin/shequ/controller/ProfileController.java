package top.xiaobolin.shequ.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.context.request.RequestScope;
import top.xiaobolin.shequ.dto.Fenye;
import top.xiaobolin.shequ.dto.QuestionDTO;
import top.xiaobolin.shequ.mapper.QuesstionMapper;
import top.xiaobolin.shequ.mapper.UserMapper;
import top.xiaobolin.shequ.model.User;
import top.xiaobolin.shequ.service.QuetionServce;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @author：xiaobolin
 * @date 2020/7/25/0025 - 21:40
 */
@Controller
public class ProfileController {

    @Autowired
    private UserMapper userMapper;
    @Autowired
    private QuetionServce quetionServce;

    @GetMapping("/profile/{action}")
    public  String Profile(
            @PathVariable(name = "action")String action,
            @RequestParam(name = "page",defaultValue = "1") Integer page,
            @RequestParam(name = "size",defaultValue = "4") Integer size,
            Model model,
            HttpServletRequest request
    ) {
        User user = (User) request.getSession().getAttribute("user");
        if (user == null) {
            return "redirect:/";
        }
            if ("questions".equals(action)) {
                model.addAttribute("section", "questions");
                model.addAttribute("sectionName", "我的提问");
                model.addAttribute("fenYeKaiGuan", 1);
                String token = null;
                Cookie[] cookies = request.getCookies();
                if (cookies != null) {
                    for (Cookie cookie : cookies) {
                        if (cookie.getName().equals("token")) {
                            token = cookie.getValue();
                        }
                        break;
                    }
                }
                int kaishi = (page - 1) * size;
                Fenye fenye = new Fenye();
                Integer zongShu = quetionServce.geZong(token);
                if (zongShu != null || zongShu != 0) {
                    int zongYeShu = zongShu % size == 0 ? zongShu / size : zongShu / size + 1;
                    fenye.setZongYeShu(zongYeShu);
                }
                fenye.setZongShu(zongShu);
                fenye.setDangQIanYe(page);
                model.addAttribute("dangQianYe", page);
                model.addAttribute("zongYeShu", fenye.getZongYeShu());
                if (page != null || page != 0) {
                    List<QuestionDTO> questionDTOS = quetionServce.geRenList(kaishi,size,token);
                    model.addAttribute("geRenList", questionDTOS);
                }
                model.addAttribute("wenTiShuLiang",zongShu);
                return "profile";
            }



        if ("repies".equals(action)) {
            model.addAttribute("section", "repies");
            model.addAttribute("sectionName", "最新回复");
            model.addAttribute("zuiXinHuiFuShuLiang",0);
            return "profile";
        }
        return "profile";
    }
}
