package com.gd.travel.mapper;

import com.gd.travel.entity.Dictionary;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author GD
 * @since 2019-10-21
 */
public interface DictionaryMapper extends BaseMapper<Dictionary> {

    List<Dictionary> listByPid(Long pid);
}
