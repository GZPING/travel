package com.gd.travel.service;

import com.gd.travel.entity.Companies;
import com.baomidou.mybatisplus.extension.service.IService;
import com.gd.travel.entity.CompaniesDTO;
import com.gd.travel.entity.Dictionary;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author GD
 * @since 2019-12-21
 */
public interface ICompaniesService extends IService<Companies> {
    CompaniesDTO getDetailById(Long id);

    Companies addCompanies(CompaniesDTO companies);

    List<Companies> listbyPid(Long id);
}
