package com.gpnu.boshen1.Controller;

import com.gpnu.boshen1.Bean.Company;
import com.gpnu.boshen1.Bean.CompanyMember;
import com.gpnu.boshen1.Bean.Science;
import com.gpnu.boshen1.Bean.Wiki;
import com.gpnu.boshen1.Mapper.CompanyMapper;
import com.gpnu.boshen1.Mapper.CompanyMemberMapper;
import com.gpnu.boshen1.Mapper.ScienceMapper;
import com.gpnu.boshen1.Mapper.WikiMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;


@Controller
public class TransitionController {
    @Autowired
    CompanyMapper companyMapper;

    @Autowired
    CompanyMemberMapper companyMemberMapper;

    @Autowired
    ScienceMapper scienceMapper;

    @Autowired
    WikiMapper wikiMapper;

    @RequestMapping({"/关于我们.html","关于我们"})
    public String AboutUs(Model model){
        Company company = companyMapper.getCompanyById(1);
        List<CompanyMember> companyMembers = companyMemberMapper.getAllCompanyMember();
        model.addAttribute("company",company);
        model.addAttribute("companyMembers",companyMembers);
        return "关于我们";
    }

    @RequestMapping({"科技前沿首页.html","科技前沿","科技前沿首页"})
    public String Science(Model model){
        List<Science> sciences = scienceMapper.getAllScience();
        model.addAttribute("sciences",sciences);
        return "科技前沿首页";
    }

    @RequestMapping({"能源百科.html","能源百科"})
    public String Wiki(Model model){
        List<Wiki> wikis = wikiMapper.getAllWiki();
        model.addAttribute("wikis",wikis);
        return "能源百科";
    }
}
