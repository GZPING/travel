package com.gd.travel.controller;

import com.gd.travel.entity.Dictionary;
import com.gd.travel.service.IDictionaryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

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
        model.addAttribute("dictionaries",dictionaries);
        return "pages/index";
    }
}
