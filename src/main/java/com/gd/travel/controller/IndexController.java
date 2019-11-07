package com.gd.travel.controller;

import com.gd.travel.entity.Dictionary;
import com.gd.travel.service.IDictionaryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * 首页信息
 * @Author : GD
 * @Create :2019/10/21 : 14:23
 */
@Controller
@RequestMapping("page")
public class IndexController {
    
    @Autowired
    private IDictionaryService dictionaryService;
    
    @GetMapping("index")
    public String index(Model model){
        List<Dictionary> dictionaries = dictionaryService.listbyPid(0L);
       // model.addAttribute("dictionaries",dictionaries);
        return "pages/home";
    }

    @GetMapping("list_2")
    public String list2(Model model,@RequestParam("id") Long id){
        List<Dictionary> dictionaries = dictionaryService.listbyPid(id);
        model.addAttribute("dictionaries",dictionaries);
        return "pages/list_2";
    }
}
