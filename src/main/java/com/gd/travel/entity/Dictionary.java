package com.gd.travel.entity;

import java.time.LocalDateTime;
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
@ApiModel(value="Dictionary对象", description="")
public class Dictionary extends BaseEntity {

    private static final long serialVersionUID=1L;

    @ApiModelProperty(value = "字典名称")
    private String name;

    @ApiModelProperty(value = "父亲节点")
    private Integer pid;

    @ApiModelProperty(value = "是否是叶子节点")
    private Boolean tail;

    @ApiModelProperty(value = "创建时间")
    private LocalDateTime createtime;

    @ApiModelProperty(value = "备注")
    private String remark;


}
