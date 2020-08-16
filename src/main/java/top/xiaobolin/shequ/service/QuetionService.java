package top.xiaobolin.shequ.service;

import jdk.nashorn.internal.parser.Token;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import top.xiaobolin.shequ.dto.QuestionDTO;
import top.xiaobolin.shequ.mapper.QuesstionMapper;
import top.xiaobolin.shequ.mapper.UserMapper;
import top.xiaobolin.shequ.model.Question;
import top.xiaobolin.shequ.model.User;

import java.util.ArrayList;
import java.util.List;

/**
 * @author：xiaobolin
 * @date 2020/7/21/0021 - 15:32
 */
@Service
public class QuetionService {
    @Autowired
    private QuesstionMapper quesstionMapper;

    @Autowired
    private UserMapper userMapper;

    //主页文章展示查询信息
    public List<QuestionDTO> list(Integer page, Integer size) {
        List<Question> questions = quesstionMapper.list(page,size);
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

    public QuestionDTO getById(Integer id) {
        Question question = quesstionMapper.getByID(id);
        QuestionDTO questionDTO = new QuestionDTO();
        BeanUtils.copyProperties(question,questionDTO);//questionDTO完全替换Question
        return questionDTO;
    }
}
