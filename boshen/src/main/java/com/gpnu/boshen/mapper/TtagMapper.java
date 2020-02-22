package com.gpnu.boshen.mapper;

import com.gpnu.boshen.entity.Ttag;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface TtagMapper {
    /**
     * 用id查标签
     * @param id
     * @return
     */
    @Select("select * from t_tag where tag_id = #{id}")
    public int get(int id);

    /**
     * 根据培训id查标签，培训，标签多对多？
     * @param train_id
     * @return
     */
    @Select("select * from t_tag where tag_id in (select t_tag_id from train_tag where t_id = #{train_id})")
    @Results({
            @Result(id = true, column = "tag_id", property = "tag_id"),
            @Result(column = "tag_name", property = "tag_name")
    })
    public List<Ttag> getByTrainId(int train_id);

    /**
     * 查所有标签
     * @return
     */
    @Select("select * from t_tag")
    @Results({
            @Result(id = true, column = "tag_id", property = "tag_id"),
            @Result(column = "tag_name", property = "tag_name"),
            @Result(column = "tag_id", property = "trains", many = @Many(select = "com.gpnu.boshen.mapper.TrainMapper.getByTagId"))
    })
    public List<Ttag> list();

    /**
     * 添加标签
     * @param ttag
     * @return
     */
    @Options(useGeneratedKeys = true, keyProperty = "tag_id")
    @Insert("insert into t_tag (tag_name) values (tag_name = #{tag_name})")
    public Ttag insert(Ttag ttag);

    /**
     * 更新标签
     * @param ttag
     * @return
     */
    @Update("update t_tag set tag_name = #{tag_name} where tag_id = #{id}")
    public Ttag update(Ttag ttag);

    /**
     * 删除标签
     * @param id
     * @return
     */
    @Delete("delete from t_tag where tag_id = #{id}")
    public Ttag delete(int id);
}
