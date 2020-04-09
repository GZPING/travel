package com.gd.travel.controller;


import com.gd.travel.common.baseEntity.ResultVO;
import com.gd.travel.entity.Companies;
import com.gd.travel.entity.CompaniesDTO;
import com.gd.travel.service.ICompaniesService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.gd.travel.controller.BaseController;

import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author GD
 * @since 2019-12-21
 */
@RestController
@Api(value = "公司处理",tags="公司处理")
@RequestMapping("/companies")
public class CompaniesController extends BaseController {

    @Autowired
    private ICompaniesService companiesService;

    @GetMapping("getDetailById")
    @ResponseBody
    public CompaniesDTO getDetailById(Long id){
        return companiesService.getDetailById(id);
    }

    @PostMapping("addCompanies")
    @ResponseBody
    @ApiOperation(value = "添加公司",notes = "添加公司")
    public ResultVO addCompanies(@RequestBody CompaniesDTO companies, @RequestParam("token") String token){
        if(StringUtils.isBlank(token) || !"petter123".equals(token)){
            return ResultVO.getParamsError("token 错误");
        }
        return ResultVO.getSuccess(companiesService.addCompanies(companies));
    }

    @PostMapping("removeCompanies")
    @ResponseBody
    @ApiOperation(value = "删除公司",notes = "删除列表")
    public ResultVO removeCompanies(Long id, @RequestParam("token") String token){
        if(StringUtils.isBlank(token) || !"petter123".equals(token)){
            return ResultVO.getParamsError("token 错误");
        }
        return ResultVO.getSuccess(companiesService.removeById(id));
    }

    @PostMapping("getList")
    @ResponseBody
    @ApiOperation(value = "删除公司",notes = "删除列表")
    public ResultVO<List<CompaniesDTO>> getList(Long id, @RequestParam("token") String token){
        return ResultVO.getSuccess();
    }
}

