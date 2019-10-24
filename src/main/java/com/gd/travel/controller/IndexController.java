package com.gd.travel.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @Author : GD
 * @Create :2019/10/21 : 14:23
 */
@Controller
@RequestMapping("page")
public class IndexController {

    @GetMapping("index")
    public String index(){
        return "pages/index";
    }
}
