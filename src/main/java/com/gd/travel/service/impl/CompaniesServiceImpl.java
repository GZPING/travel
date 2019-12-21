package com.gd.travel.service.impl;

import com.gd.travel.entity.Companies;
import com.gd.travel.mapper.CompaniesMapper;
import com.gd.travel.service.ICompaniesService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author GD
 * @since 2019-12-21
 */
@Service
public class CompaniesServiceImpl extends ServiceImpl<CompaniesMapper, Companies> implements ICompaniesService {

    @Autowired
    private CompaniesMapper companiesMapper;

    @Override
    public Companies getDetailById(Long id){
       return companiesMapper.getDetailById(id);
    }

}
