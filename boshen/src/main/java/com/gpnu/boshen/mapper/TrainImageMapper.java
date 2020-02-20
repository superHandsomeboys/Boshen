package com.gpnu.boshen.mapper;

import com.gpnu.boshen.entity.Train;
import com.gpnu.boshen.entity.TrainImage;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface TrainImageMapper {

    @Select("select * from train_image where train_id = #{train_id}")
    public List<TrainImage> get(int train_id);

    @Select("select * from train_image")
    @Results({
            @Result(id = true, column = "train_image_id", property = "train_image_id"),
            @Result(column = "train_id", property = "train_id"),
            @Result(column = "image_url", property = "image_url"),
            @Result(column = "train_id", property = "train", one = @One(select = "com.gpnu.boshen.mapper.TrainMapper.get"))
    })
    public List<TrainImage> list();

    @Options(useGeneratedKeys = true, keyProperty = "train_id")
    @Insert("insert into train_image (train_id, image_url) values (#{train_id}, #{image_url})")
    public TrainImage insert(TrainImage trainImage);

    @Update("update train_image set train_id = #{train_id}，image_url = #{image_url} where train_image_id = #{train_image_id}")
    public TrainImage update(TrainImage trainImage);

    @Delete("delete from train_image where train_image_id = #{train_image_id}")
    public TrainImage delete(int id);
}
