package com.gd.travel.entity;

import java.time.LocalDateTime;

import com.baomidou.mybatisplus.annotation.IdType;
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
@ApiModel(value="Companies对象", description="")
public class Companies extends BaseEntity {

    private static final long serialVersionUID=1L;

    @ApiModelProperty(value = "公司名称")
    private String name;

    @ApiModelProperty(value = "关键字")
    private String keyword;

    @ApiModelProperty(value = "公司链接")
    private String link;

    @ApiModelProperty(value = "地址")
    private String address;

    @ApiModelProperty(value = "公司父级菜单ID,关联字典表内容")
    private Integer type;

    @ApiModelProperty(value = "创建时间")
    private LocalDateTime createtime;


}
