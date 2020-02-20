package com.gpnu.boshen.mapper;

import com.gpnu.boshen.entity.Train;
import com.gpnu.boshen.entity.Ttag;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface TrainMapper {

    @Select("select * from train where train_id = #{id}")
    public List<Train> get(int id);

    @Select("select * from train where train_id in (select t_id from train_tag where t_tag_id = #{tag_id})")
    @Results({
            @Result(id = true, column = "train_id", property = "train_id"),
            @Result(column = "title", property = "title"),
            @Result(column = "videourl", property = "videourl"),
            @Result(column = "author_id", property = "author_id"),
            @Result(column = "category_id", property = "category_id"),
            @Result(column = "train_id", property = "trainImages", many = @Many(select = "com.gpnu.boshen.mapper.TrainImageMapper.get"))
    })
    public List<Train> getByTagId(int tag_id);

    @Select("select * from Train")
    @Results({
            @Result(id = true, column = "train_id", property = "train_id"),
            @Result(column = "title", property = "title"),
            @Result(column = "videourl", property = "videourl"),
            @Result(column = "author_id", property = "author_id"),
            @Result(column = "category_id", property = "category_id"),
            @Result(column = "train_id", property = "trainImages", many = @Many(select = "com.gpnu.boshen.mapper.TrainImageMapper.get")),
            @Result(column = "train_id", property = "ttags", many = @Many(select = "com.gpnu.boshen.mapper.TtagMapper.getByTrainId"))
    })
    public List<Train> list();

    @Insert("insert into train (title, videourl, author_id, category_id)" +
            "values " +
            "(#{title}, #{videourl}), #{author_id}, #{category_id}")
    public Train insert(Train train);

    @Update("update train set title = #{title}, videourl = #{videourl}, author_id = #{author_id}, category_id = #{category_id} where train_id = #{train_id}")
    public Train update(Train train);

    @Delete("delete from train where train_id = #{train_id}")
    public Train delete(int id);
}
