package com.gpnu.boshen.mapper;

import com.gpnu.boshen.entity.Consult;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface ConsultMapper {

    @Select("select consult_id, consult_title, consult_category_name, content " +
            "from consult " +
            "left join consult_category on category_id = consult_category_id " +
            "left join article on consult.article_id = article.article_id " +
            "where consult_id = #{id};")
    public Consult get(int id);
    @Select("select consult_id, consult_title, consult_category_name, content " +
            "from consult left join consult_category on category_id = consult_category_id " +
            "left join article on consult.article_id = article.article_id;")
    public List<Consult> list();

    @Options(useGeneratedKeys = true, keyProperty = "consult_id")
    @Insert("insert into consult (title, article_id, consult_category_id) values (#{title}, #{article_id}, #{consult_category_id})")
    public Consult insert(Consult consult);

    @Update("update consult set title = #{title}, article_id = #{article_id}, consult_category_id = #{consult_category_id} where consult_id = #{consult_id}")
    public Consult update(Consult consult);

    @Delete("delete from consult where consult_id = #{id}")
    public Consult delete(int id);
}
