package com.gpnu.boshen.mapper;

import com.gpnu.boshen.entity.Ttag;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface TtagMapper {

    @Select("select * from t_tag where tag_id = #{id}")
    public Ttag get(int id);

    @Select("select * from t_tag where tag_id in (select t_tag_id from train_tag where t_id = #{train_id})")
    @Results({
            @Result(id = true, column = "tag_id", property = "tag_id"),
            @Result(column = "tag_name", property = "tag_name")
    })
    public List<Ttag> getByTrainId(int train_id);

    @Select("select * from t_tag")
    @Results({
            @Result(id = true, column = "tag_id", property = "tag_id"),
            @Result(column = "tag_name", property = "tag_name"),
            @Result(column = "tag_id", property = "trains", many = @Many(select = "com.gpnu.boshen.mapper.TrainMapper.getByTagId"))
    })
    public List<Ttag> list();

    @Options(useGeneratedKeys = true, keyProperty = "tag_id")
    @Insert("insert into t_tag (tag_name) values (tag_name = #{tag_name})")
    public Ttag insert(Ttag ttag);

    @Update("update t_tag set tag_name = #{tag_name} where tag_id = #{id}")
    public Ttag update(Ttag ttag);

    @Delete("delete from t_tag where tag_id = #{id}")
    public Ttag delete(int id);
}
