package com.hqei.server.controller;

import com.hqei.common.BaseResponse;
import com.hqei.common.enums.BaseCodeEnum;
import com.hqei.server.domain.UserDo;
import com.hqei.server.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserController extends SysController{

    @Autowired
    private UserService userService;

    @GetMapping("/{id}")
    public BaseResponse<UserDo> getUser(@PathVariable("id") Long id){
        return new BaseResponse<>(BaseCodeEnum.SUCCESS, userService.selectByPrimaryKey(id));
    }

    @PostMapping
    public BaseResponse addUser(){
        return BaseResponse.SUCCESS;
    }

    @DeleteMapping
    public BaseResponse deleteUser(){
        return BaseResponse.SUCCESS;
    }

}
