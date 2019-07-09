package com.hqei.server.controller.user;

import com.hqei.common.BaseResponse;
import com.hqei.common.enums.BaseCodeEnum;
import com.hqei.common.page.PageRequest;
import com.hqei.server.controller.SysController;
import com.hqei.server.domain.SysRoleDo;
import com.hqei.server.request.AddRoleReq;
import com.hqei.server.request.DeleteRoleReq;
import com.hqei.server.request.UpdateRoleReq;
import com.hqei.server.service.SysRoleService;
import com.hqei.server.vo.SysRoleVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/role")
@Api(tags = "角色管理")
public class SysRoleController extends SysController {

    @Autowired
    private SysRoleService sysRoleService;

    @GetMapping("/listAll")
    @ApiOperation(value="查询所有角色")
    public BaseResponse<List<SysRoleDo>> listAll(){
        return new BaseResponse<>(BaseCodeEnum.SUCCESS, sysRoleService.listAll());
    }

    @RequiresPermissions("role:add")
    @PostMapping("/addRole")
    @ApiOperation(value="添加角色")
    public BaseResponse addRole(@Valid @RequestBody AddRoleReq addRoleReq) {
        return sysRoleService.addRole(addRoleReq);
    }

    @RequiresPermissions("role:list")
    @GetMapping("/listTable")
    @ApiOperation(value="查询角色")
    public BaseResponse<List<SysRoleVo>> listTable(PageRequest pageReq) {
        return new BaseResponse<>(BaseCodeEnum.SUCCESS, sysRoleService.listTable(pageReq));
    }

    @RequiresPermissions("role:delete")
    @PostMapping("/deleteRole")
    @ApiOperation(value="删除角色")
    public BaseResponse deleteRole(@RequestBody @Valid  DeleteRoleReq deleteRoleReq) {
        return sysRoleService.deleteByRoleId(deleteRoleReq.getRoleId());
    }

    @RequiresPermissions("role:update")
    @PostMapping("/updateRole")
    @ApiOperation(value="修改角色")
    public BaseResponse updateRole(@Valid @RequestBody UpdateRoleReq updateRoleReq) {
        return sysRoleService.updateRole(updateRoleReq);
    }

}
