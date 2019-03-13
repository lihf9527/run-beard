package org.game.common;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@ApiModel("请求响应结果")
@NoArgsConstructor
@AllArgsConstructor
public class Result<T> {
    public static final Result SUCCESS = of(1, "success");

    @ApiModelProperty("状态码: 1 - 成功, 其它 - 失败")
    private int code;
    @ApiModelProperty("状态描述")
    private String message;
    @ApiModelProperty("数据")
    private T data;

    public static <T> Result<T> of(int code, String message, T data) {
        return new Result<>(code, message, data);
    }

    public static <T> Result<T> of(int code, String message) {
        return of(code, message, null);
    }

    public static <T> Result<T> success(T data) {
        return of(1, "success", data);
    }

    public static <T> Result<T> failure(String message, T data) {
        return of(0, message, data);
    }

    public static <T> Result<T> failure(String message) {
        return failure(message, null);
    }
}
