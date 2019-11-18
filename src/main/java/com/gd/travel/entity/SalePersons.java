package com.gd.travel.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 
 * </p>
 *
 * @author GD
 * @since 2019-10-21
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@ApiModel(value="SalePersons对象", description="")
public class SalePersons extends BaseEntity {

    private static final long serialVersionUID=1L;

    @TableId(type = IdType.AUTO)
    private Long id;

    @ApiModelProperty(value = " 销售名称")
    private String name;

    @ApiModelProperty(value = "电话，如果多个，使用逗号隔开")
    private String phone;

    @ApiModelProperty(value = "微信号，如果多个，使用逗号隔开")
    @TableField("wechatNo")
    private String wechatNo;


}
