//package com.gpnu.boshen.controller;
//
//import com.gpnu.boshen.entity.Train;
//import com.gpnu.boshen.mapper.TrainMapper;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
//import org.springframework.web.bind.annotation.*;
//
//import java.util.List;
//
//@RestController
//public class TrainController {
//
//    @Autowired
//    private TrainMapper trainMapper;
//
//    /**
//     * 所有培训
//     * @return
//     */
//    @GetMapping("/trains")
//    public List<Train> list() {
//        return trainMapper.list();
//    }
//
//    /**
//     * 添加培训
//     * @param train
//     * @return
//     */
//    @PostMapping("/train")
//    public Train insert(Train train) {
////        return trainMapper.insert(train);
//    }
//
//    /**
//     *根据id查询培训
//     * @param id
//     * @param model
//     * @return
//     */
//    @GetMapping("/train/{id}")
//    public String toEditPage(@PathVariable("id") int id, Model model) {
//        List<Train> trains = trainMapper.get(id);
//        model.addAttribute("trains", trains);
//
//        return "/train/add";
//    }
//
//    /**
//     * 更新培训
//     * @param train
//     * @return
//     */
//    @PutMapping("/train")
////    public Train update(Train train) {
////        return trainMapper.update(train);
////    }
//
//    /**
//     * 删除培训
//     * @param id
//     * @return
//     */
//    @DeleteMapping("/train/{id}")
////    public Train delete(@PathVariable("id") int id) {
////        return trainMapper.delete(id);
////    }
//}
