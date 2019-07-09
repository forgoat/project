package com.hqei.server.controller.user;

import com.hqei.common.BaseResponse;
import com.hqei.common.enums.BaseCodeEnum;
import com.hqei.server.controller.SysController;
import com.hqei.server.service.SysPermissionService;
import com.hqei.server.vo.SysPermissionVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/permission")
@Api(tags = "权限管理")
public class SysPermissionController extends SysController {

    @Autowired
    private SysPermissionService sysPermissionService;

    /**
     * 查询所有权限, 给角色分配权限时调用
     */
    @RequiresPermissions("role:list")
    @GetMapping("/listAllPermission")
    @ApiOperation(value="获取所有权限")
    public BaseResponse<List<SysPermissionVo>> listAllPermission() {
        return new BaseResponse<>(BaseCodeEnum.SUCCESS, sysPermissionService.listAllPermission());
    }

}
