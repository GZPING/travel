package com.gd.travel.service.impl;

import com.gd.travel.SpringBootTestConfig;
import com.gd.travel.entity.Dictionary;
import com.gd.travel.service.IDictionaryService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * @Description
 * @Author GD
 * @Date 2019/11/5 21:39
 */

class DictionaryServiceImplTest extends SpringBootTestConfig {

    @Autowired
    private IDictionaryService dictionaryService;

    @Test
    void save() {
        List<Dictionary> dicts = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            dicts.add(Dictionary.builder().pid(1).name("省内" + i).remark("省内" + i)
                    .tail(false).createtime(LocalDateTime.now()).build());
        }
        dictionaryService.saveBatch(dicts);
    }

    @Test
    void saveBatch() {
    }

    @Test
    void listbyPid() {
    }
}
