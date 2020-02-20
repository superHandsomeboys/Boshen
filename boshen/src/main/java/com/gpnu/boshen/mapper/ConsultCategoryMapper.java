package com.gpnu.boshen.mapper;

import com.gpnu.boshen.entity.ConsultCategory;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface ConsultCategoryMapper {

    @Select("select * from consult_category where consult_category_id = #{id}")
    public ConsultCategory get(int id);

    @Select("select * from consult_category")
    public List<ConsultCategory> list();

    @Options(useGeneratedKeys = true, keyProperty = "consult_category_id")
    @Insert("insert into consult_category(consult_category_name) values (#{consult_category_name})")
    public ConsultCategory insert(ConsultCategory category);

    @Update("update consult_category set consult_category_name = #{consult_category_name} where consult_category_id = #{consult_category_id}")
    public ConsultCategory update(ConsultCategory category);

    @Delete("delete from consult_category where consult_category_id = #{id}")
    public ConsultCategory delete(int id);
}
