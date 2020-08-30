package top.xiaobolin.shequ.mapper;

import org.apache.ibatis.annotations.*;
import top.xiaobolin.shequ.model.User;

/**
 * @author：xiaobolin
 * @date 2020/7/14/0014 - 20:42
 */
@Mapper
public interface UserMapper {
    @Insert("Insert into USER (name,account_id,token,gmt_create,gmt_modified,figureurl_qq,cishu) values (#{name},#{accountId},#{token},#{gemCreate},#{gemModified},#{figureurlQq},#{cishu})")
    void insert(User User);

    @Select("select * from user where token = #{token}")
    User findByToken(@Param("token") String token);

    @Select("select * from user where id = #{id}")
    User findById(@Param("id") Integer id);

    @Update("update `shequ`.`user` set `name` = #{name} where `account_id` = #{accountId}")
    void fuGaiName(@Param("name") String name,@Param("accountId") String accountId);

    @Update("update `shequ`.`user` set `figureurl_qq` = #{figureurlQq} where `account_id` = #{accountId}")
    void fuGaiTou(@Param("figureurlQq") String figureurlQq,@Param("accountId") String accountId);

    @Select("SELECT cishu FROM `user` WHERE Account_id=#{accountId} ORDER BY gmt_create LIMIT 1")
    int selectcishu(@Param("accountId") String accountId);

    @Update("update `shequ`.`user` set `cishu` = #{selectcishu1} where `Account_id` = #{accountId}")
    void upcishu(@Param("selectcishu1") int selectcishu1,@Param("accountId") String accountId);

}
