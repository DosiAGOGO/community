package cc.smcoco.community.mapper;

import cc.smcoco.community.model.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.intellij.lang.annotations.PrintFormat;

@Mapper
public interface UserMapper {
    @Insert("insert into user (name,account_id,token,gmt_create,gmt_modified,avatar_url) values (#{name},#{accountId},#{token},#{gmtCreate},#{gmtModified},#{avatarUrl})")
    void insert(User user);

    //注解的感觉就是双引号里的内容是这个注解自动写的sql语句。
    @Select("select * from user where token = #{token}")
    User findByToken(@Param("token") String token);
}
