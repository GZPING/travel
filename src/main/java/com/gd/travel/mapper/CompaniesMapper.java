package com.gd.travel.mapper;

import com.gd.travel.entity.Companies;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.gd.travel.entity.CompaniesDTO;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author GD
 * @since 2019-12-21
 */
public interface CompaniesMapper extends BaseMapper<Companies> {

    CompaniesDTO getDetailById(Long id);

    List<Companies> listByPid(Long id);
}
