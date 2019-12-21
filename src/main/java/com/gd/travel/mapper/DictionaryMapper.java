package com.gd.travel.mapper;

import com.gd.travel.entity.Dictionary;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

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

    List<Dictionary> listByPid(@Param("pid") Long pid);
}
