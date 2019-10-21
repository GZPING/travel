package com.gd.travel.controller;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author GD
 * @since 2019-10-21
 */
@RestController
    @RequestMapping("/dictionary")
public class DictionaryController extends BaseController {

    @GetMapping("index")
    public String index(){
        return "ok";
    }

}

