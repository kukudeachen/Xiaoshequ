package top.xiaobolin.shequ.service;

import com.vdurmont.emoji.EmojiParser;
import jdk.nashorn.internal.parser.Token;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.BeanUtils;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;
import top.xiaobolin.shequ.dto.QuestionDTO;
import top.xiaobolin.shequ.mapper.QuesstionMapper;
import top.xiaobolin.shequ.mapper.UserMapper;
import top.xiaobolin.shequ.model.Huifu;
import top.xiaobolin.shequ.model.Question;
import top.xiaobolin.shequ.model.User;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

/**
 * @author：xiaobolin
 * @date 2020/7/21/0021 - 15:32
 */
@Service
public class QuetionService {
    public Object getBenRenUser;
    @Autowired
    private QuesstionMapper quesstionMapper;

    @Autowired
    private UserMapper userMapper;

    //主页文章展示查询信息
    public List<QuestionDTO> list(Integer page, Integer size) {
        List<Question> questions = quesstionMapper.list(page,size);
        List<QuestionDTO> questionDTOS = new ArrayList<>();
        for (Question question : questions) {
            String neirong = EmojiParser.parseToUnicode(question.getDescription());
            question.setDescription(neirong);
            User user = userMapper.findById(question.getCreator());
            QuestionDTO questionDTO = new QuestionDTO();
            BeanUtils.copyProperties(question, questionDTO);
            questionDTO.setUser(user);
            questionDTOS.add(questionDTO);
        }
        return questionDTOS;
    }
    public Integer zongShu(){
        Integer zongSu = quesstionMapper.zongSu();
        return zongSu;
    }
    public Integer geZong(String token){
        Integer geZong = quesstionMapper.geZong(token);
        return geZong;
    }
    //个人问题分页展示
    public List<QuestionDTO> geRenList(Integer page, Integer size, String Token) {
        List<Question> questions = quesstionMapper.geRenList(page,size,Token);
        List<QuestionDTO> questionDTOS = new ArrayList<>();
        for (Question question : questions) {
            User user = userMapper.findById(question.getCreator());
            QuestionDTO questionDTO = new QuestionDTO();
            BeanUtils.copyProperties(question, questionDTO);
            questionDTO.setUser(user);
            questionDTOS.add(questionDTO);
        }
        return questionDTOS;
    }

    public Question getById(Integer id) {
        Question question = new Question();
        Question wenZhang = quesstionMapper.getByID(id);
        String s = EmojiParser.parseToUnicode(wenZhang.getDescription());
        wenZhang.setDescription(s);
        return wenZhang;
    }

    public User getUser(Integer id) {
        Question question = new Question();
        Question wenZhang = quesstionMapper.getByID(id);
        User user = userMapper.findById(wenZhang.getCreator());
        String s = EmojiParser.parseToUnicode(user.getName());
        user.setName(s);
        return user;
    }

    public List<Huifu> getHuifuone(Integer id) {
        Huifu huifu = new Huifu();
        List<Huifu> selecthuifuone = quesstionMapper.selecthuifuone(id);
        List<Huifu> select = new ArrayList<>();
        for (Huifu huifu1:selecthuifuone){
            String username = EmojiParser.parseToUnicode(huifu1.getUserName());
            huifu.setUserName(username);
            String body = EmojiParser.parseToUnicode(huifu1.getHuiFuBody());
            huifu.setHuiFuBody(body);
            huifu.setGmtModified(huifu1.getGmtModified());
            huifu.setUserTou(huifu1.getUserTou());
            Huifu huifu2 = new Huifu();
            BeanUtils.copyProperties(huifu,huifu2);
            select.add(huifu2);
        }
        return select;
    }

    public int seletView(Integer id) {
        int i = quesstionMapper.seletView(id);
        return i;
    }
}
