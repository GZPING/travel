package com.gd.travel.controller;


import com.gd.travel.common.baseEntity.ResultVO;
import com.gd.travel.entity.Companies;
import com.gd.travel.service.ICompaniesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author GD
 * @since 2019-10-21
 */
@RestController
@RequestMapping("/companies")
public class CompaniesController extends BaseController {

    @Autowired
    private ICompaniesService companiesService;

    @GetMapping("index")
    public ResultVO<List<Companies>> index(){
        return ResultVO.getSuccess(companiesService.list());
    }

}

