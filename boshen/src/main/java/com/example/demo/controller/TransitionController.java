package com.example.demo.controller;

import com.example.demo.dto.*;
import com.example.demo.entity.*;
import com.example.demo.enums.*;
import com.example.demo.mapper.*;
import com.example.demo.service.CompanyMemberService;
import com.example.demo.service.CompanyService;
import com.example.demo.service.ScienceService;
import com.example.demo.service.WikiServie;
import com.example.demo.vo.*;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@SessionAttributes(value = {"userId"})
@RestController
public class TransitionController {
    @Autowired
    CompanyMapper companyMapper;

    @Autowired
    CompanyMemberMapper companyMemberMapper;

    @Autowired
    ScienceMapper scienceMapper;

    @Autowired
    WikiMapper wikiMapper;

    @Autowired
    CompanyService companyService;

    @Autowired
    ArticleMapper articleMapper;

    @Autowired
    ScienceService scienceService;

    @Autowired
    WikiServie wikiServie;

    @Autowired
    CompanyMemberService companyMemberService;

    //测试用的，结合file.html
    @PostMapping("/addScience1")
    public ResultVo addS1(MultipartFile file){
        ScienceRDTO scienceRDTO = new ScienceRDTO();
        scienceRDTO.setAuthorId(2);
        scienceRDTO.setContent("测试内容");
        scienceRDTO.setImage(file);
        scienceRDTO.setIntroduce("测试简介");
        scienceRDTO.setTitle("测试科技文章");
        scienceService.addScience(scienceRDTO);
        return new ResultVo(ScienceStatusEnum.SUCCESS);
    }
//    //测试用的，结合file.html
//    @PostMapping("/addScience2")
//    public ResultVo addS2(MultipartFile file){
//        ScienceRDTO scienceRDTO = new ScienceRDTO();
//        scienceRDTO.setAuthorId(1);
//        scienceRDTO.setCentent("北京时间2月27日，NBA常规赛结束9场争夺。东部两大伪强队全都输球，76人还折损了恩比德，热火输给西部弱旅森林狼，签下伊戈达拉后依然成绩难有改色。。");
//        scienceRDTO.setImage(file);
//        scienceRDTO.setIntroduce("NBA两大伪强队输球，西部两支黑马4连败，第8争夺战升级，附排名");
//        scienceRDTO.setTitle("NBA两大伪强队输球");
//        scienceService.addScience(scienceRDTO);
//        return new ResultVo(ScienceStatusEnum.SUCCESS);
//    }

    /**
     * 添加科学文章
     */
    @PostMapping("/science")
    public ResultVo addScience(ScienceRDTO scienceRDTO, Model model){
        int userId = (Integer)model.getAttribute("userId");
        scienceRDTO.setAuthorId(userId);
//        scienceRDTO.setAuthorId(1);
        System.out.println(scienceRDTO);
        if (scienceService.addScience(scienceRDTO) == 0){
            return new ResultVo(ScienceStatusEnum.FAIL_NOT_IMAGE);
        }
        return new ResultVo(ScienceStatusEnum.SUCCESS);
    }

    /**
     * 删除科学文章
     */
    @DeleteMapping("/science/{id}")
    public ResultVo deleteScience(@PathVariable("id") Integer id){
        if (id == null){
            return new ResultVo(ScienceStatusEnum.FAIL_NULL_PARAM);
        }
        scienceService.deleteScience(id);
        return new ResultVo(ScienceStatusEnum.SUCCESS);
    }

    /**
     * 查询所有的science类
     * @return
     */
    @GetMapping("/sciences")
    public ResultVo Science(){
        return new ResultVo(ScienceStatusEnum.SUCCESS,scienceMapper.getAllScience());
    }

    /**
     * 用id查
     */
    @GetMapping("/wiki/{id}")
    public ResultVo findWikiByWikiId(@PathVariable("id") Integer id){
        return new ResultVo(ScienceStatusEnum.SUCCESS,wikiServie.findByid(id));
    }

    /**
     * 查所有的wiki类
     * @return
     */
    @GetMapping("/wikis")
    public ResultVo Wiki(){
        return new ResultVo(WikiStatusEnum.SUCCESS,wikiMapper.getAllWiki());
    }

    /**
     * 更新wiki
     */
    @PutMapping("/wiki")
    public ResultVo updateWiki(@RequestBody WikiVO wikiVO){
        Article article = new Article();
        int articleId =  wikiMapper.getByIdWiki(wikiVO.getWikiId()).getArticleId();
        article.setContent(wikiVO.getContent());
        article.setArticleId(articleId);
        articleMapper.updateArticle(article);
        Wiki wiki = new Wiki();
        BeanUtils.copyProperties(wikiVO,wiki);
        wiki.setArticleId(articleId);
        wikiMapper.updateWiki(wiki);
        return new ResultVo(WikiStatusEnum.SUCCESS);
    }

    /**
     * 更新公司信息
     */
    @PutMapping("/company")
    public ResultVo updateCompany(@RequestBody Company company){
        company.setCompanyId(1);
        companyMapper.updateCompany(company);
        return new ResultVo(CompanyStatusEnum.SUCCESS);
    }

    /**
     * 查公司简介
     * @return
     */
    @GetMapping("/company/introduce")
    public ResultVo findIntroduce(){
        String introduce = companyMapper.getCompanyById(1).getIntroduce();
        Map<String,String> map = new HashMap<>();
        map.put("introduce",introduce);
        return new ResultVo(CompanyStatusEnum.SUCCESS,map);
    }

    /**
     * 用公司的consultindexID查consultCategory
     *
     * ConsultCategory
     *
     */
    @GetMapping("/company/consult")
    public ResultVo findIndexConsult(){
        CompanyDTO companyDTO = companyService.findById(1);
        Map<String,Object> map = new HashMap<>();
        map.put("indexConsultCategory",companyDTO.getIndexConsult());
//        map.put("indexTrainCategory",companyDTO.getIndexTrain());
        return new ResultVo(CompanyStatusEnum.SUCCESS,map);
    }

    /**
     * 查所有能源百科类
     * @return
     */
    @GetMapping("/wiki")
    public ResultVo findAllWiki(){
        List<Wiki> wikis = wikiMapper.getAllWiki();
        List<WikiDTO> wikiDTOS = new ArrayList<WikiDTO>();
        for (Wiki wiki : wikis){
            WikiDTO wikiDTO = new WikiDTO();
            wikiDTO.setTitle(wiki.getTitle());
            wikiDTO.setWikiId(wiki.getWikiId());
            wikiDTO.setArticle(articleMapper.findArticleById(wiki.getArticleId()));
            wikiDTOS.add(wikiDTO);
        }
        return new ResultVo(WikiStatusEnum.SUCCESS,wikiDTOS);
    }

    /**
     * 查某页的科技类，分页查询，8个科技类一页
     */
    @GetMapping("/sciences/page/{page}")
    public ResultVo findAllScience(@PathVariable("page") int page){
        List<ScienceDTO> scienceDTOS = scienceService.findPage(page);
        List<SimpleScienceVO> simpleScienceVOS = new ArrayList<SimpleScienceVO>();
        for (ScienceDTO scienceDTO : scienceDTOS){
            SimpleScienceVO simpleScienceVO = new SimpleScienceVO();
            BeanUtils.copyProperties(scienceDTO,simpleScienceVO);
            simpleScienceVOS.add(simpleScienceVO);
        }
        Map<String,Object> map = new HashMap<>();
        map.put("pageQuantity",scienceService.findPageQuantity());
        map.put("sciences",simpleScienceVOS);
        return new ResultVo(ScienceStatusEnum.SUCCESS,map);
    }

    /**
     * 分页，条件查询，科技文章
     */
    @GetMapping("/sciences/title/{title}/page/{page}")
    public ResultVo selectScienceByTitlePage(@PathVariable("page") int page, @PathVariable("title") String title){
        Map<String,Object> map = new HashMap<>();
        map.put("sciences",scienceService.findByPageTitle(title,page));
        map.put("pageQuantity",scienceService.findPageQuantityByTitle(title));
        return new ResultVo(ScienceStatusEnum.SUCCESS,map);
    }

    /**
     * 分页，条件查询，百科文章
     */
    @GetMapping("/wikis/title/{title}/page/{page}")
    public ResultVo selectWikiByTitlePage(@PathVariable("page") int page, @PathVariable("title") String title){
        Map<String,Object> map = new HashMap<>();
        map.put("wikis",wikiServie.findByPageTitle(title,page));
        map.put("pageQuantity",wikiServie.findPageQuantityByTitle(title));
        return new ResultVo(WikiStatusEnum.SUCCESS,map);
    }


//    /**
//     * 查询科技文章有多少页
//     */
//    @GetMapping("/science/pageQuantity")
//    public ResultVo findPageQuantity(){
//        Map<String,Integer> map = new HashMap<>();
//        map.put("pageQuantity",scienceService.findPageQuantity());
//        return new ResultVo(ScienceStatusEnum.SUCCESS,map);
//    }

    /**
     * 查看科技文章详情页的科技类数据
     */
    @GetMapping("/science/detailed/{id}")
    public ResultVo detailedScience(@PathVariable("id") Integer id){
        if (id == null){
            return new ResultVo(ScienceStatusEnum.FAIL_NULL_PARAM);
        }
        ScienceDTO scienceDTO = scienceService.findByScienceId(id);
        ScienceVO scienceVO = new ScienceVO();
        BeanUtils.copyProperties(scienceDTO,scienceVO);
        SimpleUserVO simpleUserVO = new SimpleUserVO();
        simpleUserVO.setAvatarUrl(scienceDTO.getAuthor().getAvatar());
        simpleUserVO.setUserName(scienceDTO.getAuthor().getUserName());
        simpleUserVO.setIntroduce(scienceDTO.getAuthor().getIntroduce());
        scienceVO.setAuthor(simpleUserVO);
        return new ResultVo(ScienceStatusEnum.SUCCESS,scienceVO);
    }

    /**
     * 用scienceid查科技文章资料
     */
    @GetMapping("/science/{id}")
    public ResultVo findScienceByScienceId(@PathVariable("id") Integer id){
        if (id == null){
            return new ResultVo(ScienceStatusEnum.FAIL_NULL_PARAM);
        }
        return new ResultVo(ScienceStatusEnum.SUCCESS,scienceService.findByScienceId(id));
    }

    /**
     * 查找科技类上一个和下一个文章,id,title
     */
    @GetMapping("/science/around/{id}")
    public ResultVo lastScience(@PathVariable("id")Integer id){
        if (id == null){
            return new ResultVo(ScienceStatusEnum.FAIL_NULL_PARAM);
        }
        Map<String,Object> map = new HashMap<>();
        //看有无上一个类
        Science lastScience = scienceService.lastScience(id);
        if (lastScience == null){
            map.put("lastScienceId",null);
            map.put("lastTitle",null);
        }else {
            map.put("lastScienceId",lastScience.getScienceId());
            map.put("lastTitle",lastScience.getTitle());
        }
        ///看有无下一个类
        Science nextScience = scienceService.nextScience(id);
        if (nextScience == null){
            map.put("nestScienceId",null);
            map.put("nestTitle",null);
        }else {
            map.put("nestScienceId",nextScience.getScienceId());
            map.put("nestTitle",nextScience.getTitle());
        }

        return new ResultVo(ScienceStatusEnum.SUCCESS,map);
    }

    /**
     * 查找前4个科技类文章放,可能包含自己
     */
    @GetMapping("/sciences/four")
    public ResultVo findFourSciences(){
        List<ScienceDTO> scienceDTOS = scienceService.findLimit(0,4);
        List<SimpleScienceVO> simpleScienceVOS = new ArrayList<SimpleScienceVO>();
        for (ScienceDTO scienceDTO : scienceDTOS){
            SimpleScienceVO simpleScienceVO = new SimpleScienceVO();
            BeanUtils.copyProperties(scienceDTO,simpleScienceVO);
            simpleScienceVOS.add(simpleScienceVO);
        }
        return new ResultVo(ScienceStatusEnum.SUCCESS,simpleScienceVOS);
    }

    //1.查id=1的公司信息 company
    @GetMapping("/company")
    public ResultVo findCompany(){
        return new ResultVo(CommentStateEnum.SUCCESS,companyMapper.getCompanyById(1));

    }

    //2.查所有的公司成员介绍信息    companymember
    @GetMapping("/companyMembers")
    public ResultVo findMember(){
        return new ResultVo(CompanyMemberStatusEnum.SUCCESS,companyMemberService.findAll());
    }

    /**
     * 添加公司成员
     */
    @PostMapping("/companyMember")
    public ResultVo addCompanyMember(@RequestBody CompanyMemberDTO companyMemberDTO){
        companyMemberService.addMember(companyMemberDTO);
        return new ResultVo(CompanyMemberStatusEnum.SUCCESS);
    }
//    //搭配file.html测试
//    @PostMapping("/addmember")
//    public ResultVo addcm(MultipartFile file){
//        CompanyMemberDTO companyMemberDTO = new CompanyMemberDTO();
//        companyMemberDTO.setAvatar(file);
//        companyMemberDTO.setIntroduce("hhhh");
//        companyMemberDTO.setMemberName("骆进");
//        companyMemberDTO.setPosition("CFO");
//        companyMemberService.addMember(companyMemberDTO);
//        return new ResultVo(CompanyMemberStatusEnum.SUCCESS);
//    }

    /**
     * 更改公司成员信息
     */
    @PutMapping("/companyMember")
    public ResultVo updateCompanyMember(@RequestBody CompanyMemberDTO companyMemberDTO){
        companyMemberService.updateMember(companyMemberDTO);
        return new ResultVo(CompanyMemberStatusEnum.SUCCESS);
    }
//    //搭配file.html测试，这里用post
//    @PostMapping("/updatemember")
//    public ResultVo updatecm(MultipartFile file){
//        if (file == null){
//            return new ResultVo(CommentStateEnum.FAIL_NULL_PARAM);
//        }
//        CompanyMemberDTO companyMemberDTO = new CompanyMemberDTO();
//        companyMemberDTO.setMemberId(1);
//        companyMemberDTO.setAvatar(file);
//        companyMemberDTO.setIntroduce("hhhh");
//        companyMemberDTO.setMemberName("黄炜亿");
//        companyMemberDTO.setPosition("CEO");
//        companyMemberService.updateMember(companyMemberDTO);
//        return new ResultVo(CompanyMemberStatusEnum.SUCCESS);
//    }

    /**
     * 删公司成员信息
     */
    @DeleteMapping("/companyMember/{id}")
    public ResultVo deleteMember(@PathVariable("id") Integer id){
        if (id == null){
            return new ResultVo(CompanyMemberStatusEnum.FAIL_NULL_PARAM);
        }
        companyMemberService.deleteMember(id);
        return new ResultVo(CompanyMemberStatusEnum.SUCCESS);
    }

    /**
     * 用id查公司成员信息
     */
    @GetMapping("/companyMember/{id}")
    public ResultVo findMemberById(@PathVariable("id") Integer id){
        if (id == null){
            return new ResultVo(CompanyMemberStatusEnum.FAIL_NULL_PARAM);
        }
        return new ResultVo(CompanyMemberStatusEnum.SUCCESS,companyMemberService.findMemberById(id));

    }

    /**
     * 添加百科文章
     */
    @PostMapping("/wiki")
    public ResultVo addWiki(@RequestBody WikiVO wikiVO){
        Article article = new Article();
        article.setContent(wikiVO.getContent());
        articleMapper.addArticle(article);
        Wiki wiki = new Wiki();
        BeanUtils.copyProperties(wikiVO,wiki);
        wiki.setArticleId(article.getArticleId());
        wikiMapper.insertWiki(wiki);
        return new ResultVo(WikiStatusEnum.SUCCESS);
    }

    /**
     * 删除wiki
     */
    @DeleteMapping("/wiki/{id}")
    public ResultVo deleteWiki(@PathVariable("id") Integer id){
        if (id == null){
            return new ResultVo(WikiStatusEnum.FAIL_NULL_PARAM);
        }
        wikiServie.deleteWiki(id);
        return new ResultVo(WikiStatusEnum.SUCCESS);
    }

    /**
     * 根据作者id查询科技文章
     */
    @GetMapping("/science/authorId")
    public ResultVo findByAuthorId(Model model){
        Integer id = (Integer)model.getAttribute("userId");
        return new ResultVo(ScienceStatusEnum.SUCCESS,scienceService.findByAuthorId(id));
    }

}