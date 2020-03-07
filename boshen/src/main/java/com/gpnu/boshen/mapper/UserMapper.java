package com.gpnu.boshen.mapper;

import com.gpnu.boshen.entity.User;
import com.gpnu.boshen.mapper.dynamicSql.UserDS;
import org.apache.ibatis.annotations.*;

@Mapper
public interface UserMapper {
    /**
     * 查看是否已有mail注册用户
     * @param mail
     * @return
     */
    @Select("select * from user where mail = #{mail}")
    User findByMail(String mail);

    /**
     * 查看是否已有phone注册用户
     * @param phone
     * @return
     */
    @Select("select * from user where phone = #{phone}")
    User findByPhone(String phone);

    /**
     * 添加用户
     * @param user
     * @return
     */
    @Insert("insert into user(user_name,password,mail,phone,create_time,avatar,user_type,introduce) values(#{userName}," +
            "#{password},#{mail},#{phone},#{createTime},#{avatar},#{userType},#{introduce})")
    int addUser(User user);

    /**
     * 登录用的，根据Mail和password查询
     * @param mail
     * @param password
     * @return
     */
    @Select("select * from user where mail = #{mail} and password = #{password}")
    User findByMailPassword(@Param("mail") String mail, @Param("password") String password);

    /**
     * 根据userid查询用户信息
     * @param userId
     * @return
     */
    @Select("select * from user where user_id = #{userId}")
    User findByUserId(int userId);

    /**
     * 更新user根据userId
     * @param user
     * @return
     */
//    @Update("update user set user_name=#{userName},mail=#{mail},phone=#{phone},password=#{password}," +
//            " avatar=#{avatar} where user_id = #{userId}")
    @UpdateProvider(type = UserDS.class,method = "update")
    int updateUser(User user);

}
