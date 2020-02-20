package com.gpnu.boshen1;

import com.gpnu.boshen1.Bean.Science;
import com.gpnu.boshen1.Mapper.ScienceMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Date;
import java.util.List;

@SpringBootTest
public class ScienceTest {
    @Autowired
    ScienceMapper scienceMapper;

    //查询所有
    @Test
    void FindAll(){
        List<Science> sciences= scienceMapper.getAllScience();
        for(Science science:sciences){
            System.out.println(science);
        }
    }
    //根据id查询
    @Test
    void FindById(){
        Science science = scienceMapper.getScienceById(1);
        System.out.println(science);
    }
    //插入数据
    @Test
    void InsertScience(){
        Science science = new Science();
        science.setTitle("bebos");
        science.setCreate_time(new Date());
        System.out.println(scienceMapper.insertScience(science));
    }
    //删除数据
    @Test
    void DeleteScience(){
        System.out.println(scienceMapper.deleteScience(2));
    }
    //更新数据
    @Test
    void UpdateScience(){
        Science science = new Science();
        science.setScience_id(2);
        science.setTitle("boshen");
        science.setCreate_time(new Date());
        System.out.println(scienceMapper.updateScience(science));
    }
}
