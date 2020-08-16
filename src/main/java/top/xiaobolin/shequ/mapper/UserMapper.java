package top.xiaobolin.shequ.mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import top.xiaobolin.shequ.model.User;

/**
 * @author：xiaobolin
 * @date 2020/7/14/0014 - 20:42
 */
@Mapper
public interface UserMapper {
    @Insert("Insert into USER (name,account_id,token,gmt_create,gmt_modified,figureurl_qq) values (#{name},#{accountId},#{token},#{gemCreate},#{gemModified},#{figureurlQq})")
    void insert(User User);

    @Select("select * from user where token = #{token}")
    User findByToken(@Param("token") String token);

    @Select("select * from user where id = #{id}")
    User findById(@Param("id") Integer id);
}
