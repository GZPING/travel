package com.gd.travel.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.time.LocalDateTime;
import java.util.List;

/**
 * <p>
 * 
 * </p>
 *
 * @author GD
 * @since 2019-12-21
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@ApiModel(value="Companies对象", description="")
public class CompaniesDTO extends BaseEntity {

    private static final long serialVersionUID=1L;

    @TableId(type = IdType.AUTO)
    private Long id;

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

    @ApiModelProperty(value = "许可证号")
    private String permitNumber;

    @ApiModelProperty(value = "负责人")
    private String ownerUser;

    @ApiModelProperty(value = "主营线路")
    private String route;

    @ApiModelProperty(value = "父亲节点")
    private Long pid;

    @ApiModelProperty(value = "销售人员列表")
    private List<SalePersons> salePersons;
}
