package com.hqei.server.controller;

import com.hqei.common.BaseResponse;
import com.hqei.common.enums.BaseCodeEnum;
import com.hqei.server.domain.SysRoleDo;
import com.hqei.server.domain.SysUserDo;
import com.hqei.server.request.AddRoleReq;
import com.hqei.server.request.ModifyUserReq;
import com.hqei.server.request.PageReq;
import com.hqei.server.request.UpdateRoleReq;
import com.hqei.server.response.PageResp;
import com.hqei.server.service.SysUserService;
import com.hqei.server.vo.SysPermissionVo;
import com.hqei.server.vo.SysRoleVo;
import com.hqei.server.vo.SysUserVo;
import org.apache.shiro.authz.annotation.Logical;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/user")
public class SysUserController {

    @Autowired
    private SysUserService sysUserService;

    /**
     * 查询用户列表
     */
    @RequiresPermissions("user:list")
    @GetMapping("/list")
    public PageResp<List<SysUserVo>> listUser(@RequestBody PageReq pageReq) {
        return sysUserService.listUser(pageReq);
    }

    @RequiresPermissions("user:add")
    @PostMapping("/addUser")
    public BaseResponse addUser(@Valid @RequestBody SysUserDo sysUserDo) {
        return sysUserService.addUser(sysUserDo);
    }

    @RequiresPermissions("user:update")
    @PostMapping("/updateUser")
    public BaseResponse updateUser(@Valid @RequestBody ModifyUserReq modifyUserReq) {
        return sysUserService.updateUser(modifyUserReq);
    }

    @RequiresPermissions(value = {"user:add", "user:update"}, logical = Logical.OR)
    @GetMapping("/getAllRoles")
    public BaseResponse<List<SysRoleDo>> getAllRoles() {
        return new BaseResponse<>(BaseCodeEnum.SUCCESS, sysUserService.getAllRoles());
    }

    /**
     * 角色列表
     */
    @RequiresPermissions("role:list")
    @GetMapping("/listRole")
    public BaseResponse<List<SysRoleVo>> listRole() {
        return new BaseResponse<>(BaseCodeEnum.SUCCESS, sysUserService.listRole());
    }

    /**
     * 查询所有权限, 给角色分配权限时调用
     */
    @RequiresPermissions("role:list")
    @GetMapping("/listAllPermission")
    public BaseResponse<List<SysPermissionVo>> listAllPermission() {
        return new BaseResponse<>(BaseCodeEnum.SUCCESS, sysUserService.listAllPermission());
    }

    /**
     * 新增角色
     */
    @RequiresPermissions("role:add")
    @PostMapping("/addRole")
    public BaseResponse addRole(@Valid @RequestBody AddRoleReq addRoleReq) {
        return sysUserService.addRole(addRoleReq);
    }

    /**
     * 修改角色
     */
    @RequiresPermissions("role:update")
    @PostMapping("/updateRole")
    public BaseResponse updateRole(@Valid @RequestBody UpdateRoleReq updateRoleReq) {
        return sysUserService.updateRole(updateRoleReq);
    }

    /**
     * 删除角色
     */
    @RequiresPermissions("role:delete")
    @PostMapping("/deleteRole")
    public BaseResponse deleteRole(@RequestBody Long roleId) {
        return sysUserService.deleteRole(roleId);
    }
}
