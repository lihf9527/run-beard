package org.game.api;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.game.common.Result;
import org.game.model.UserForm;
import org.springframework.web.bind.annotation.*;

@Api(tags = "用户接口")
@RestController
@RequestMapping("/user")
public class UserController {

    @GetMapping("/hello")
    @ApiOperation(value = "hello", notes = "创建人: 李海峰")
    public Result<?> hello() {
        System.out.println("hello world");
        return Result.SUCCESS;
    }

    @PostMapping("/hi")
    @ApiOperation(value = "hi", notes = "创建人: 李海峰")
    public Result<?> hi(@RequestBody UserForm form) {
        System.out.println("hi world");
        System.out.println(form);
        return Result.SUCCESS;
    }
}
