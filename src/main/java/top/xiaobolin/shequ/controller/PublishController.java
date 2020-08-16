package top.xiaobolin.shequ.controller;

import com.vdurmont.emoji.EmojiParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.server.Session;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import top.xiaobolin.shequ.dto.AccessTokenDTO;
import top.xiaobolin.shequ.dto.GithubUser;
import top.xiaobolin.shequ.mapper.QuesstionMapper;
import top.xiaobolin.shequ.mapper.UserMapper;
import top.xiaobolin.shequ.model.Question;
import top.xiaobolin.shequ.model.User;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

/**
 * @author：xiaobolin
 * @date 2020/7/18/0018 - 22:49
 */
@Controller
public class PublishController {
    @Autowired
    private QuesstionMapper quesstionMapper;
    @Autowired
    private UserMapper userMapper;

    @GetMapping("/publish")
    public String publish(){
        return "publish";
    }

    @PostMapping("/publish")
    public String doPublish(
            @RequestParam("title")String title,
            @RequestParam("description")String description,
            @RequestParam("tag")String tag,
            HttpServletRequest request,
            Model model){
        model.addAttribute("title",title );
        model.addAttribute("description",description);
        model.addAttribute("tag",tag);
        if (title == null || title ==""){
            model.addAttribute("error","标题不能为空！");
            return "publish";
        }
        if (description == null || description ==""){
            model.addAttribute("error","内容不能为空！");
            return "publish";
        }
        if (tag == null || tag ==""){
            model.addAttribute("error","标签不能为空！");
            return "publish";
        }
        User user = (User) request.getSession().getAttribute("user");
        if (user ==null) {
            return "redirect:/";
        }else {
            Question question = new Question();
            question.setTitle(title);
            question.setDescription(description);
            question.setTag(tag);
            question.setCreator(user.getId());
            question.setGmtCreate(System.currentTimeMillis());
            question.setGmtModified(question.getGmtCreate());
            question.setAccountId(user.getAccountId());
            String s = EmojiParser.parseToAliases(user.getName());//表情转换
            question.setUserName(s);
            quesstionMapper.create(question);
            return "redirect:/";
        }
    }
}
