package com.gd.travel.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;

import java.io.Serializable;

/**
 * @Author : GD
 * @Create :2019/10/21 : 14:23
 */
public class BaseEntity implements Serializable {

    @TableId(type = IdType.AUTO)
    private Long id;
}
