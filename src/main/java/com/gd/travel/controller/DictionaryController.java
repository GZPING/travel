package com.gd.travel.controller;


import com.gd.travel.common.baseEntity.ResultVO;
import com.gd.travel.entity.Companies;
import com.gd.travel.entity.Dictionary;
import com.gd.travel.service.IDictionaryService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotNull;
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
@Api(value = "列表信息",tags = "列表信息")
@RequestMapping("/dictionary")
public class DictionaryController extends BaseController {

    @Autowired
    private IDictionaryService dictionaryService;

    @PostMapping("addDictionary")
    @ResponseBody
    @ApiOperation(value = "添加列表信息",notes = "添加列表信息")
    public ResultVO addDictionary(@RequestBody Dictionary dictionary,@RequestParam("token") String token){
        if(StringUtils.isBlank(token) || !"petter123".equals(token)){
            return ResultVO.getParamsError("token 错误");
        }
        return ResultVO.getSuccess(dictionaryService.save(dictionary));
    }

    @PostMapping("removeDictionary")
    @ResponseBody
    @ApiOperation(value = "删除列表",notes = "删除列表")
    public ResultVO addDictionary(Long id,@RequestParam("token") String token){
        if(StringUtils.isBlank(token) || !"petter123".equals(token)){
            return ResultVO.getParamsError("token 错误");
        }
        return ResultVO.getSuccess(dictionaryService.removeById(id));
    }
    public ResultVO<List<Dictionary>> getDictionarysByPid(@NotNull @RequestParam("pid") Long pid){
        return ResultVO.getSuccess(dictionaryService.listbyPid(pid));
    }

}

