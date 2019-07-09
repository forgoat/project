package com.hqei.server.controller.user;

import com.hqei.common.BaseResponse;
import com.hqei.common.enums.BaseCodeEnum;
import com.hqei.common.page.PageRequest;
import com.hqei.common.page.PageResponse;
import com.hqei.server.Constants;
import com.hqei.server.controller.SysController;
import com.hqei.server.domain.SysUserDo;
import com.hqei.server.request.AddUserReq;
import com.hqei.server.request.ModifyUserReq;
import com.hqei.server.service.SysUserService;
import com.hqei.server.vo.SimpleUserVo;
import com.hqei.server.vo.SysUserVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/user")
@Api(tags = "用户管理")
public class SysUserController extends SysController {

    @Autowired
    private SysUserService sysUserService;

    /**
     * 查询用户列表
     */
    @RequiresPermissions("user:list")
    @GetMapping("/list")
    @ApiOperation(value="查询用户")
    public PageResponse<List<SysUserVo>> listUser(PageRequest pageReq) {
        return sysUserService.listUser(pageReq);
    }

//    @RequiresPermissions("user:list")
    @GetMapping("/listProcessor")
    @ApiOperation(value="获取加工用户")
    public BaseResponse<List<SysUserVo>> listProcessor() {
        return new BaseResponse<>(BaseCodeEnum.SUCCESS, sysUserService.listProcessUser());
    }

    @RequiresPermissions("user:add")
    @PostMapping("/addUser")
    @ApiOperation(value="添加用户")
    public BaseResponse addUser(@Valid @RequestBody AddUserReq addUserReq) {
        return sysUserService.addUser(addUserReq);
    }

    @GetMapping("/getUserSimpleInfo")
    @ApiOperation(value="获取用户简单信息")
    public BaseResponse<SimpleUserVo> getUserSimpleInfo() {
        SysUserDo sysUserDo = sysUserService.selectByPrimaryKey(getLoginUser().getId());
        SimpleUserVo simpleUserVo = new SimpleUserVo();
        BeanUtils.copyProperties(sysUserDo, simpleUserVo);
        return new BaseResponse<>(BaseCodeEnum.SUCCESS, simpleUserVo);
    }

    @PostMapping("/modifySimpleInfo")
    @ApiOperation(value="编辑用户简单信息")
    public BaseResponse modifySimpleInfo(@Valid @RequestBody SimpleUserVo simpleUserVo) {
        if(getLoginUser().getId().equals(Constants.SUPER_USER_ID)){
            return new BaseResponse(BaseCodeEnum.FAILURE.getCode(), "不能编辑超管信息!");
        }
        return sysUserService.modifySimpleInfo(simpleUserVo, getLoginUser().getId());
    }

    @RequiresPermissions("user:update")
    @PostMapping("/modify")
    @ApiOperation(value="编辑用户")
    public BaseResponse modify(@Valid @RequestBody ModifyUserReq modifyUserReq) {
        return sysUserService.modify(modifyUserReq);
    }

    @PostMapping("/delete")
    @ApiOperation(value="删除用户")
    public BaseResponse delete(@RequestParam Long userId) {
        return sysUserService.del(userId);
    }
}
