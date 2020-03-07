package com.gpnu.boshen.mapper;

import com.gpnu.boshen.dto.ConsultInfo;
import com.gpnu.boshen.entity.Consult;
import com.gpnu.boshen.entity.ConsultCategory;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface ConsultMapper {

    /**
     * 用id查询咨询
     * @param id
     * @return
     */
    @Select("select * from consult where consult_id = #{id};")
    public Consult get(int id);

    /**
     * 查询所有咨询
     * @return
     */
    @Select("select * from consult")
    public List<Consult> list();

    /**
     * 添加咨询
     * @param consult
     * @return
     */
    @Options(useGeneratedKeys = true, keyProperty = "consultId")
    @Insert("insert into consult (consult_title, article_id, category_id) values (#{consultTitle}, #{articleId}, #{categoryId})")
    public int insert(Consult consult);

    /**
     * 更新咨询
     * @param consult
     * @return
     */
    @Update("update consult set consult_title = #{consultTitle}, article_id = #{articleId}, category_id = #{categoryId} where consult_id = #{consultId}")
    public int update(Consult consult);

    /**
     * 删除咨询
     * @param id
     * @return
     */
    @Delete("delete from consult where consult_id = #{id}")
    public int delete(int id);

    /**
     * 根据类别id查询咨询
     * @param categoryId
     * @return
     */
    @Select("select * from consult " +
            "where category_id = #{categoryId}")
    public List<Consult> findByCategory(int categoryId);

    /**
     * 条件，分页查询
     */
    @Select("select * from consult where consult_title like #{title} limit #{start},#{quantity}")
    public List<Consult> findByPageTitle(@Param("title") String title,@Param("start") int start,@Param("quantity")int quantity);
    /**
     * 根据题目的模糊查询
     * @param consultTitle
     * @return
     */
    @Select("select * from consult where consult_title like #{consultTitle}")
    public List<ConsultInfo> findByTitle(String consultTitle);
}
