package com.gpnu.boshen.controller;

import com.gpnu.boshen.entity.Consult;
import com.gpnu.boshen.enums.ConsultCategoryStateEnum;
import com.gpnu.boshen.mapper.ConsultMapper;
import com.gpnu.boshen.vo.ResultVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.xml.transform.Result;
import java.util.List;

@Controller
public class ConsultController {

    @Autowired
    private ConsultMapper consultMapper;

    /**
     * 查询所有咨询类
     * @return
     */
//    @GetMapping("/consults")
//    public List<Consult> list() {
////        return consultMapper.list();
//    }

    /**
     * 添加咨询类
     * @param consult
     * @return
     */
//    @PostMapping("/consult")
//    public Consult insert(Consult consult) {
//        return consultMapper.insert(consult);
//    }

    /**
     * 根据id查询咨询
     * @param id
     * @param model
     * @return
     */
//    @GetMapping("/consult/{id}")
//    public String toEditPage(@PathVariable("id") int id, Model model) {
//        Consult consult = consultMapper.get(id);
//        model.addAttribute("consult", consult);
//
//        return "/consult/add";
//    }

    /**
     * 更新咨询类
     * @param consult
     * @return
     */
//    @PutMapping("/consult")
//    public Consult update(Consult consult) {
//        return consultMapper.update(consult);
//    }

    /**
     * 删除咨询类
     * @param id
     * @return
     */
//    @DeleteMapping("/consult/{id}")
//    public Consult delete(int id) {
//        return consultMapper.delete(id);
//    }

    /**
     * 根据title模糊查询
     */

    /**
     * 根据类别查询咨询
     */

    public ResultVo findByCategoryId(Integer categoryId){
        if (categoryId == null){
            return new ResultVo(ConsultCategoryStateEnum.FAIL_NULL_PARAM);
        }
        return new ResultVo(ConsultCategoryStateEnum.SUCCESS,consultMapper.findByCategory(categoryId));
    }
}
