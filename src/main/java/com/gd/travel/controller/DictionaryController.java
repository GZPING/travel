package com.gd.travel.controller;


import com.gd.travel.common.baseEntity.ResultVO;
import com.gd.travel.entity.Dictionary;
import com.gd.travel.service.ICompaniesService;
import com.gd.travel.service.IDictionaryService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Objects;

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

    @Autowired
    private IDictionaryService dictionaryService;

    @GetMapping("index")
    public String index(){
        return "ok";
    }

    public ResultVO<List<Dictionary>> getDictionarysByPid(@NotNull @RequestParam("pid") Long pid){
        return ResultVO.getSuccess(dictionaryService.listbyPid(pid));
    }

}

