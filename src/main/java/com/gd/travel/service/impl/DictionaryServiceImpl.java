package com.gd.travel.service.impl;

import com.gd.travel.entity.Dictionary;
import com.gd.travel.mapper.DictionaryMapper;
import com.gd.travel.service.IDictionaryService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author GD
 * @since 2019-10-21
 */
@Service
public class DictionaryServiceImpl extends ServiceImpl<DictionaryMapper, Dictionary> implements IDictionaryService {

    @Autowired
    private DictionaryMapper dictionaryMapper;

    @Override
    public List<Dictionary> listbyPid(Long pid) {
        return dictionaryMapper.listByPid(pid);
    }
}
