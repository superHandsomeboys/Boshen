package com.example.demo.mapper;

import com.example.demo.entity.Slider;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface SliderMapper  {

    /**
     * 查询一类型的所有轮播图
     * @return
     */
    @Select("select * from slider where type=#{type}")
    public List<Slider> list(int type);

    /**
     * 添加轮播图
     * @param slider
     * @return
     */
    @Options(useGeneratedKeys = true, keyProperty = "id")
    @Insert("insert into slider(name, type, uri) values (#{name}, #{type}, #{uri})")
    public int insertSlider(Slider slider);

    /**
     * 删除轮播图
     * @param sliderId
     * @return
     */
    @Delete("delete from slider where id = #{sliderId}")
    public int deleteSlider(int sliderId);


    @Select("select * from slider where id = #{id}")
    public Slider findbyid(int id);
}
