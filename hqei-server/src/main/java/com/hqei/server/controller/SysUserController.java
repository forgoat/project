package com.hqei.server.controller;

import com.alibaba.fastjson.JSONObject;
import com.hqei.common.BaseResponse;
import com.hqei.common.enums.BaseCodeEnum;
import com.hqei.server.domain.SysUserDo;
import com.hqei.server.domain.UserDo;
import com.hqei.server.request.ModifyUserReq;
import com.hqei.server.request.PageReq;
import com.hqei.server.response.PageResp;
import com.hqei.server.service.SysUserService;
import com.hqei.server.service.UserService;
import com.hqei.server.vo.SysUserVo;
import org.apache.shiro.authz.annotation.Logical;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
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
    public JSONObject getAllRoles() {
        return sysUserService.getAllRoles();
    }

    /**
     * 角色列表
     */
    @RequiresPermissions("role:list")
    @GetMapping("/listRole")
    public JSONObject listRole() {
        return sysUserService.listRole();
    }

    /**
     * 查询所有权限, 给角色分配权限时调用
     */
    @RequiresPermissions("role:list")
    @GetMapping("/listAllPermission")
    public JSONObject listAllPermission() {
        return sysUserService.listAllPermission();
    }

    /**
     * 新增角色
     */
    @RequiresPermissions("role:add")
    @PostMapping("/addRole")
    public JSONObject addRole(@RequestBody JSONObject requestJson) {
//        CommonUtil.hasAllRequired(requestJson, "roleName,permissions");
//        return sysUserService.addRole(requestJson);
        return null;
    }

    /**
     * 修改角色
     */
    @RequiresPermissions("role:update")
    @PostMapping("/updateRole")
    public JSONObject updateRole(@RequestBody JSONObject requestJson) {
//        CommonUtil.hasAllRequired(requestJson, "roleId,roleName,permissions");
//        return sysUserService.updateRole(requestJson);
        return null;
    }

    /**
     * 删除角色
     */
    @RequiresPermissions("role:delete")
    @PostMapping("/deleteRole")
    public JSONObject deleteRole(@RequestBody JSONObject requestJson) {
//        CommonUtil.hasAllRequired(requestJson, "roleId");
//        return sysUserService.deleteRole(requestJson);
        return null;
    }
}
