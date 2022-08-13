package com.lonjoy.common.page;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * 响应分页对象
 */
@Data
@ApiModel(value = "ResPageDTO对象", description = "公共返回对分页对象")
public class ResPage<M> implements Serializable {
    /**
     * 开始页数
     */
    @ApiModelProperty(value = "开始页数", example = "1")
    private Integer pageNum = 1;
    /**
     * 每页显示数量
     */
    @ApiModelProperty(value = "每页显示数量", example = "10")
    private Integer pageSize = 10;

    /**
     * 总记录数
     */
    @ApiModelProperty(value = "总记录数", example = "100")
    private long totalCount;
    /**
     * 响应数据
     */
    @ApiModelProperty(value = "响应数据")
    private List<M> list;
}
