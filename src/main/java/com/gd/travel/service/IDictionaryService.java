package com.gd.travel.service;

import com.gd.travel.entity.Dictionary;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author GD
 * @since 2019-10-21
 */
public interface IDictionaryService extends IService<Dictionary> {

    /**
     * 通过pid 获取字典表内容
     * @param pid
     * @return
     */
    List<Dictionary> listbyPid(Long pid);
}
