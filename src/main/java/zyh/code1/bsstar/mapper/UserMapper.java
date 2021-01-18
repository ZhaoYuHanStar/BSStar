package zyh.code1.bsstar.mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Component;
import zyh.code1.bsstar.model.User;

/**
 * @ClassName UserMapper
 * @Description
 * @Author 赵语涵
 * @Date 2020-12-28 22:25
 */
@Component("userMapper")
@Mapper
public interface UserMapper {
    @Insert("insert into user(name, account_id, token, gmt_create, gmt_modified) values(#{name}, #{accountId}, #{token}, #{gmtCreate}, #{gmtModified})")
    void insertUser(User user);

    @Select("select * from user where token = #{token}")
    User findUserByToken(@Param("token") String token);
}

