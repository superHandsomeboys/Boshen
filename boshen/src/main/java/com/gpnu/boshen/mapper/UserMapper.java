package com.gpnu.boshen.mapper;

import com.gpnu.boshen.entity.User;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface UserMapper {

    @Select("select * from user where user_id = #{id}")
    public User get(int id);

    @Select("select * from user")
    public List<User> list();

    @Options(useGeneratedKeys = true, keyProperty = "user_id")
    @Insert("insert into user (user_name, password, email, phone) values (#{user_name}, #{password}, #{email}, #{phone})")
    public User insert(User user);

    @Update("update user set user_name = #{user_name}, password = #{password}, email = #{email}, phone = #{phone}, avatar = #{avatar} where user_id = #{user_id}")
    public User update(User user);

    @Delete("delete from user where user_id = #{id}")
    public User delete(int id);
}
