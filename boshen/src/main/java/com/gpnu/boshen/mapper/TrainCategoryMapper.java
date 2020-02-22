package com.gpnu.boshen.mapper;

import com.gpnu.boshen.entity.TrainCategory;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface TrainCategoryMapper {

    /**
     * 用id查询培训类别
     * @param id
     * @return
     */
    @Select("select * from train_category where train_category_id = #{id}")
    public TrainCategory get(int id);

    /**
     * 查询所有培训类别
     * @return
     */
    @Select("select * from train_category")
    public List<TrainCategory> list();

    /**
     * 添加培训类别
     * @param trainCategory
     * @return
     */
    @Options(useGeneratedKeys = true, keyProperty = "train_category_id")
    @Insert("insert into train_category " +
            "(train_category_name, introduce_imageurl, introduce_content, function_imageurl, function_content, history_imageurl, history_content) " +
            "values " +
            "(#{train_category_name}, #{introduce_imageurl}, #{introduce_content}, #{function_imageurl}, #{function_content}, #{history_imageurl}, #{history_content})")
    public int insert(TrainCategory trainCategory);

    /**
     * 更新培训类别
     * @param trainCategory
     * @return
     */
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
    public int update(TrainCategory trainCategory);

    /**
     * 删除培训类别
     * @param id
     * @return
     */
    @Delete("delete from train_category where train_category_id = #{id}")
    public int delete(int id);
}
