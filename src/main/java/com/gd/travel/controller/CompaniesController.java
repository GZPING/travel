package com.gd.travel.controller;


import com.gd.travel.entity.Companies;
import com.gd.travel.service.ICompaniesService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;
import com.gd.travel.controller.BaseController;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author GD
 * @since 2019-12-21
 */
@RestController
@Api
@RequestMapping("/companies")
public class CompaniesController extends BaseController {

    @Autowired
    private ICompaniesService companiesService;

    @GetMapping("getDetailById")
    public Companies getDetailById(Long id){
        return companiesService.getDetailById(id);
    }
}

