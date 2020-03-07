package com.gpnu.boshen.mapper.dynamicSql;

import com.gpnu.boshen.entity.Comment;
import com.gpnu.boshen.entity.User;
import org.apache.ibatis.jdbc.SQL;

/**
 * comment类的动态sql,依赖于mybatis包
 */
public class UserDS {
    public String update(User user){
        return new SQL(){
            {
                UPDATE("user");
                if (user.getMail() != null){
                    SET("mail=#{mail}");
                }
                if (user.getPhone() != null){
                    SET("phone=#{phone}");
                }
                if (user.getPassword() != null){
                    SET("password=#{password}");
                }
                if (user.getAvatar() != null){
                    SET("avatar=#{avatar}");
                }
                if (user.getUserName() != null){
                    SET("user_name=#{userName}");
                }
                if (user.getUserType() != null){
                    SET("user_Type = #{userType}");
                }
                if (user.getIntroduce() != null){
                    SET("introduce=#{introduce}");
                }
                WHERE("user_id = #{userId}");
            }
        }.toString();
    }



}
