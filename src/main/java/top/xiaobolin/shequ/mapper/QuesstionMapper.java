package top.xiaobolin.shequ.mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.taglibs.standard.extra.spath.Step;
import top.xiaobolin.shequ.dto.QuestionDTO;
import top.xiaobolin.shequ.model.Question;

import java.util.List;

/**
 * @author：xiaobolin
 * @date 2020/7/19/0019 - 17:27
 */
@Mapper
public interface QuesstionMapper {

    @Insert("insert into `shequ`.`question` (`title`, `description`, `gmt_create`, `gmt_modified`, `creator`, `tag`, `account_id`, `user_name`) values (#{title}, #{description}, #{gmtCreate}, #{gmtModified}, #{creator}, #{tag}, #{accountId}, #{userName})")
     void create(Question question);

    @Select("select * from question limit #{page},#{size}")
    List<Question> list(@Param("page") Integer page,@Param("size") Integer size);

    @Select("select count(1) FROM question")
    Integer zongSu();

    @Select("SELECT * FROM question WHERE account_id = ALL (SELECT account_id FROM `user` WHERE token = #{token}) LIMIT #{page},#{size};")
    List<Question> geRenList(@Param("page") Integer page,@Param("size") Integer size,@Param("token") String token);

    @Select("SELECT COUNT(1) FROM question WHERE account_id = ALL (SELECT account_id FROM `user` WHERE token = #{token});")
    Integer geZong(@Param("token") String token);

    @Select("select * from question where id = #{id}")
    Question getByID(@Param("id") Integer id);

}
