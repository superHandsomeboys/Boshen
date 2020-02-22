package com.gpnu.boshen.mapper;

import com.gpnu.boshen.entity.Train;
import com.gpnu.boshen.entity.TrainImage;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface TrainImageMapper {

    /**
     * 用trainid查询培训图片
     * @param train_id
     * @return
     */
    @Select("select * from train_image where train_id = #{train_id}")
    public List<TrainImage> get(int train_id);

    /**
     * 查询所有的培训图片
     * @return
     */
    @Select("select * from train_image")
    @Results({
            @Result(id = true, column = "train_image_id", property = "train_image_id"),
            @Result(column = "train_id", property = "train_id"),
            @Result(column = "image_url", property = "image_url"),
            @Result(column = "train_id", property = "train", one = @One(select = "com.gpnu.boshen.mapper.TrainMapper.get"))
    })
    public List<TrainImage> list();

    /**
     * 添加培训图片
     * @param trainImage
     * @return
     */
    @Options(useGeneratedKeys = true, keyProperty = "train_id")
    @Insert("insert into train_image (train_id, image_url) values (#{train_id}, #{image_url})")
    public int insert(TrainImage trainImage);

    /**
     * 更新培训图片
     * @param trainImage
     * @return
     */
    @Update("update train_image set train_id = #{train_id}，image_url = #{image_url} where train_image_id = #{train_image_id}")
    public int update(TrainImage trainImage);

    /**
     * 删除培训
     *
     * @param id
     * @return
     */
    @Delete("delete from train_image where train_image_id = #{train_image_id}")
    public int delete(int id);
}
