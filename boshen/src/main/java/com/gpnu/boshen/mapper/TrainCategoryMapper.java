package com.gpnu.boshen.mapper;

import com.gpnu.boshen.entity.TrainCategory;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface TrainCategoryMapper {

    @Select("select * from train_category where train_category_id = #{id}")
    public TrainCategory get(int id);

    @Select("select * from train_category")
    public List<TrainCategory> list();

    @Options(useGeneratedKeys = true, keyProperty = "train_category_id")
    @Insert("insert into train_category " +
            "(train_category_name, introduce_imageurl, introduce_content, function_imageurl, function_content, history_imageurl, history_content) " +
            "values " +
            "(#{train_category_name}, #{introduce_imageurl}, #{introduce_content}, #{function_imageurl}, #{function_content}, #{history_imageurl}, #{history_content})")
    public TrainCategory insert(TrainCategory trainCategory);

    @Update("update train_category " +
            "set " +
            "train_category_name = #{train_category_name}, " +
            "introduce_imageurl = #{introduce_imageurl}, " +
            "introduce_content = #{introduce_content}, " +
            "function_imageurl = #{function_imageurl}, " +
            "function_content = #{function_content}, " +
            "history_imageurl = #{history_imageurl}, " +
            "history_content = #{history_content}" +
            "where " +
            "train_category_id = #{train_category_id}")
    public TrainCategory update(TrainCategory trainCategory);

    @Delete("delete from train_category where train_category_id = #{id}")
    public TrainCategory delete(int id);
}
