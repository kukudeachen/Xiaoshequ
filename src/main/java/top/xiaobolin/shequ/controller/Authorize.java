package top.xiaobolin.shequ.controller;

import com.vdurmont.emoji.EmojiParser;
import org.apache.ibatis.annotations.Insert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import top.xiaobolin.shequ.dto.AccessTokenDTO;
import top.xiaobolin.shequ.dto.GithubUser;
import top.xiaobolin.shequ.mapper.UserMapper;
import top.xiaobolin.shequ.model.User;
import top.xiaobolin.shequ.provider.GithubProvider;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.UUID;

/**
 * @author：xiaobolin
 * @date 2020/7/9/0009 - 21:34
 */
@Controller
public class Authorize {

    @Autowired
    private GithubProvider GithubProvider;

    @Value("${github.Client.id}")
    private String clientId;

    @Value("${github.Client.secret}")
    private String clientSecret;

    @Value("${github.Client.url}")
    private String clientUrl;

    @Autowired
    private UserMapper userMapper;


    @GetMapping("/callback")
    public String callback(@RequestParam(name = "code") String code,
                           HttpServletRequest request,
                           HttpServletResponse response) throws IOException {
        System.out.println("获取openid路径");
        AccessTokenDTO accessTokenDTO = new AccessTokenDTO();
        //App id
        accessTokenDTO.setClient_id(clientId);
        //回执地址
        accessTokenDTO.setRedirect_uri(clientUrl);
        //app key
        accessTokenDTO.setClient_secret(clientSecret);
        accessTokenDTO.setCode(code);
        accessTokenDTO.setGrant_type("authorization_code");
        //accessToken
        String accessToken = GithubProvider.getAccessToken(accessTokenDTO);
        //openid
        String openid = GithubProvider.getOpenid(accessToken);
        //回去唯一id
        String unionId = GithubProvider.githubUnionid(accessToken);
        //获取user数据
        GithubUser githubUser = GithubProvider.getUser(accessToken, openid, accessTokenDTO.getClient_id());
        String s = EmojiParser.parseToAliases(githubUser.getNickname());//将表情符号转为字符
        githubUser.setNickname(s);
        String s1 = EmojiParser.parseToUnicode(s);//将字符转为表情符号
        if(githubUser!=null){
            User user = new User();
            String token = UUID.randomUUID().toString();
            user.setToken(token);
            user.setName(githubUser.getNickname());
            user.setAccountId(String.valueOf(unionId));
            user.setGemCreate(System.currentTimeMillis());
            user.setGemModified(user.getGemCreate());
            user.setFigureurlQq(githubUser.getFigureurlQq());
            user.setCishu(1);
            userMapper.insert(user);
            //登录成功，写cookie和session
            response.addCookie(new Cookie("token",token));
            int selectcishu = userMapper.selectcishu(user.getAccountId());
                request.getSession().setAttribute("cishu",selectcishu);
            int selectcishu1 = selectcishu + 1;
            userMapper.upcishu(selectcishu1,user.getAccountId());
            userMapper.fuGaiName(user.getName(),user.getAccountId());
            userMapper.fuGaiTou(user.getFigureurlQq(),user.getAccountId());
            return "redirect:/";
        }else{
            //登录失败，重新登录
            return "redirect:/";
        }
    }
}
