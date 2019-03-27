package org.game.model;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class UserForm {
    @ApiModelProperty(value = "姓名", required = true)
    private String name;

    @ApiModelProperty("年龄")
    private Integer age;
}
