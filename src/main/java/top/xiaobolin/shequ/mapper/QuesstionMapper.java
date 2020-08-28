package top.xiaobolin.shequ.mapper;

import org.apache.ibatis.annotations.*;
import org.apache.taglibs.standard.extra.spath.Step;
import org.springframework.stereotype.Service;
import top.xiaobolin.shequ.dto.QuestionDTO;
import top.xiaobolin.shequ.model.Huifu;
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

    @Insert("insert into `shequ`.`huifu` (`username`, `usertou`, `huifubody`, `chengjie_shang`,`chengji_xia`, `gmt_create`, `gmt_modified`) values (#{userName},#{userTou},#{huiFuBody},#{chengJieShang},#{chengJieXia},#{gmtCreate},#{gmtModified})")
    void huifuone(Huifu huifu);

    @Select("SELECT question.`comment_count` FROM question WHERE id = #{id}")
    int cpls(@Param("id") int id);

    @Update("update `shequ`.`question` set `comment_count` =#{i1}   where `id` = #{id}")
    void xpls(@Param("i1") int i1,@Param("id") int id);

    @Select("SELECT * FROM huifu WHERE chengjie_shang = #{id}")
    List<Huifu> selecthuifuone(@Param("id") Integer id);

    @Select("SELECT `view_count` FROM question WHERE `id`=#{id}")
    int seletView(@Param("id") Integer id);

    @Update("update `shequ`.`question` set `view_count` = #{i1} where `id` = #{id}")
    void updataView(@Param("id") Integer id, @Param("i1") int i1);

    @Select("SELECT * FROM question WHERE id=#{id}")
    Question selectXuiGai(int id);

    @Update("update `shequ`.`question` set `title` = #{title} , `description` = #{description} , `tag` = #{tag} where `id` = #{id}")
    void upxiugai(@Param("title") String title,@Param("description") String description,@Param("tag") String tag,@Param("id") int id);
}
