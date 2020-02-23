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
    @Select("select consult_id, consult_title, consult_category_name, content " +
            "from consult " +
            "left join consult_category on category_id = consult_category_id " +
            "left join article on consult.article_id = article.article_id " +
            "where consult_id = #{id};")
    public ConsultInfo get(int id);

    /**
     * 查询所有咨询
     * @return
     */
    @Select("select consult_id, consult_title, consult_category_name, content " +
            "from consult left join consult_category on category_id = consult_category_id " +
            "left join article on consult.article_id = article.article_id;")
    public List<ConsultInfo> list();

    /**
     * 添加咨询
     * @param consult
     * @return
     */
    @Options(useGeneratedKeys = true, keyProperty = "consult_id")
    @Insert("insert into consult (title, article_id, consult_category_id) values (#{title}, #{article_id}, #{consult_category_id})")
    public int insert(Consult consult);

    /**
     * 更新咨询
     * @param consult
     * @return
     */
    @Update("update consult set title = #{title}, article_id = #{article_id}, consult_category_id = #{consult_category_id} where consult_id = #{consult_id}")
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
     * @param consultCategoryId
     * @return
     */
    @Select("select * from consult as c" +
            "where consult_category_id = #{consultCategoryId}")
    public List<Consult> findByCategory(int consultCategoryId);

    /**
     * 根据题目的模糊查询
     * @param title
     * @return
     */
    @Select("select * from consult where title = #{title}")
    public List<ConsultInfo> findByTitle(String title);
}
