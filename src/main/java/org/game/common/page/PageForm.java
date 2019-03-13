package org.game.common.page;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Data
@ApiModel
public class PageForm {
    @NotNull(message = "查询页码不能为空")
    @Min(message = "查询页码不能小于1", value = 1)
    @ApiModelProperty("查询页码")
    private Integer pageIndex;

    @NotNull(message = "分页大小不能为空")
    @Min(message = "分页大小不能小于1", value = 1)
    @ApiModelProperty("分页大小")
    private Integer pageSize;
}
