package com.gd.travel.controller;

import com.gd.travel.entity.Companies;
import com.gd.travel.entity.CompaniesDTO;
import com.gd.travel.entity.Dictionary;
import com.gd.travel.service.ICompaniesService;
import com.gd.travel.service.IDictionaryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import springfox.documentation.annotations.ApiIgnore;

import java.util.List;

/**
 * 首页信息
 * @Author : GD
 * @Create :2019/10/21 : 14:23
 */
@Controller
@ApiIgnore
@RequestMapping("page")
public class IndexController {
    
    @Autowired
    private IDictionaryService dictionaryService;

    @Autowired
    private ICompaniesService companiesService;

    @GetMapping("index")
    public String index(Model model){
        List<Dictionary> dictionaries = dictionaryService.listbyPid(0L);
       // model.addAttribute("dictionaries",dictionaries);
        return "pages/home";
    }

    @GetMapping("list")
    public String list(Model model,@RequestParam("id") Long id){
        List<Dictionary> dictionaries = dictionaryService.listbyPid(id);
        model.addAttribute("dictionaries",dictionaries);
        return "pages/list";
    }

    @GetMapping("companies")
    public String companies(Model model,@RequestParam("id") Long id){
        List<Companies> companies = companiesService.listbyPid(id);
        model.addAttribute("companies",companies);
        return "pages/companiesList";
    }

    @GetMapping("companiesDetail")
    public String companiesDetail(Model model,@RequestParam("id") Long id){
        CompaniesDTO companies = companiesService.getDetailById(id);
        model.addAttribute("companies",companies);
        return "pages/detail";
    }
}
