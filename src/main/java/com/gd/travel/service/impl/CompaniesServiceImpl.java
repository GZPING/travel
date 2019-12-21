package com.gd.travel.service.impl;

import com.gd.travel.entity.Companies;
import com.gd.travel.entity.CompaniesDTO;
import com.gd.travel.entity.SalePersons;
import com.gd.travel.mapper.CompaniesMapper;
import com.gd.travel.mapper.SalePersonsMapper;
import com.gd.travel.service.ICompaniesService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

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
    @Autowired
    private SalePersonsMapper salePersonsMapper;

    @Override
    public CompaniesDTO getDetailById(Long id){
       return companiesMapper.getDetailById(id);
    }

    @Override
    public Companies addCompanies(CompaniesDTO companiesDTO) {
        Companies companies = new Companies();
        BeanUtils.copyProperties(companiesDTO,companies);
        companies.setCreatetime(LocalDateTime.now());
        companiesMapper.insert(companies);
        Long companiesId = companies.getId();
        if(!CollectionUtils.isEmpty(companiesDTO.getSalePersons())){
            for (SalePersons salePerson : companiesDTO.getSalePersons()) {
                salePerson.setCompaniesId(companiesId);
                salePersonsMapper.insert(salePerson);
            }

        }
        return null;
    }

    @Override
    public List<Companies> listbyPid(Long id) {
        return companiesMapper.listByPid(id);
    }

}
