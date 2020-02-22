package com.gpnu.boshen.mapper;

import com.gpnu.boshen.entity.Train;
import com.gpnu.boshen.entity.Ttag;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface TrainMapper {

    /**
     * 根据id查询培训
     * @param id
     * @return
     */
    @Select("select * from train where train_id = #{id}")
    public List<Train> get(int id);

    /**
     * 根据标签id查询培训
     * @param tag_id
     * @return
     */
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

    /**
     * 查询所有培训
     * @return
     */
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

    /**
     * 添加培训
     * @param train
     * @return
     */
    @Insert("insert into train (title, videourl, author_id, category_id)" +
            "values " +
            "(#{title}, #{videourl}), #{author_id}, #{category_id}")
    public int insert(Train train);

    /**
     * 更新培训
     * @param train
     * @return
     */
    @Update("update train set title = #{title}, videourl = #{videourl}, author_id = #{author_id}, category_id = #{category_id} where train_id = #{train_id}")
    public int update(Train train);

    /**
     * 删除培训
     * @param id
     * @return
     */
    @Delete("delete from train where train_id = #{train_id}")
    public int delete(int id);
}
