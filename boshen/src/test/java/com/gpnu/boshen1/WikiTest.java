package com.gpnu.boshen1;

import com.gpnu.boshen1.Bean.Wiki;
import com.gpnu.boshen1.Mapper.WikiMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
public class WikiTest {

    @Autowired
    WikiMapper wikiMapper;

    //查询所有
    @Test
    void FindAll(){
        List<Wiki> wikis = wikiMapper.getAllWiki();
        for(Wiki wiki:wikis){
            System.out.println(wiki);
        }
    }
    //根据ID查询
    @Test
    void FindById(){
        Wiki wiki = wikiMapper.getByIdWiki(2);
        System.out.println(wiki);
    }
    //插入数据
    @Test
    void insertWiki(){
        Wiki wiki = new Wiki();
        wiki.setTitle("电力市场");
        System.out.println(wikiMapper.insertWiki(wiki));
        FindAll();
    }
    //删除数据
    @Test
    void deleteWiki(){
        System.out.println(wikiMapper.deleteWiki(2));
    }
    //更新数据
    @Test
    void updateWiki(){
        Wiki wiki = new Wiki();
        wiki.setWiki_id(1);
        wiki.setTitle("水力市场");
        System.out.println(wikiMapper.updateWiki(wiki));
        FindAll();
    }
}
