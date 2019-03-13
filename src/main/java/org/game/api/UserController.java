package org.game.api;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.game.common.Result;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@Api(tags = "用户接口")
@RestController("/user")
public class UserController {

    @GetMapping("/hello")
    @ApiOperation(value = "hello", notes = "创建人: 李海峰")
    public Result<?> hello() {
        System.out.println("hello world");
        return Result.SUCCESS;
    }
}
