package com.gpnu.boshen.mapper;

import com.gpnu.boshen.entity.ConsultCategory;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface ConsultCategoryMapper {
    /**
     * 用类别id查询培训类别
     * @param id
     * @return
     */
    @Select("select * from consult_category where consult_category_id = #{id}")
    public ConsultCategory get(@Param("id") int id);

    /**
     * 查询所有咨询类别
     * @return
     */
    @Select("select * from consult_category")
    public List<ConsultCategory> list();

    /**
     * 添加咨询类别
     * @param category
     * @return
     */
    @Options(useGeneratedKeys = true, keyProperty = "consultCategoryId")
    @Insert("insert into consult_category(consult_category_name) values (#{consultCategoryName})")
    public int insert(ConsultCategory category);

    /**
     * 更新咨询类别
     * @param category
     * @return
     */
    @Update("update consult_category set consult_category_name = #{consultCategoryName} " +
            "where consult_category_id = #{consultCategoryId}")
    public int update(ConsultCategory category);

    /**
     * 用id删除咨询类别
     * @param id
     * @return
     */
    @Delete("delete from consult_category where consult_category_id = #{id}")
    public int delete(int id);
}
