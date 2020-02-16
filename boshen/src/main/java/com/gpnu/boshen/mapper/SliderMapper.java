package com.gpnu.boshen.mapper;

import com.gpnu.boshen.entity.Slider;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface SliderMapper  {

    @Select("select * from slider where id = #{id}")
    public Slider getSliderById(Integer id);

    @Select("select * from slider")
    public List<Slider> list();

    @Options(useGeneratedKeys = true, keyProperty = "id")
    @Insert("insert into slider(name, type, url) values (#{name}, #{type}, #{uri})")
    public boolean insertSlider(Slider slider);

}
