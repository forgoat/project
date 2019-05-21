package com.hqei.server.service;

import com.hqei.common.BaseResponse;
import com.hqei.common.BaseService;
import com.hqei.server.domain.SysRoleDo;
import com.hqei.server.domain.SysUserDo;
import com.hqei.server.request.AddRoleReq;
import com.hqei.server.request.ModifyUserReq;
import com.hqei.server.request.PageReq;
import com.hqei.server.request.UpdateRoleReq;
import com.hqei.server.response.PageResp;
import com.hqei.server.vo.SysPermissionVo;
import com.hqei.server.vo.SysRoleVo;
import com.hqei.server.vo.SysUserVo;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface SysUserService extends BaseService<SysUserDo, Long> {

    SysUserDo getUser(String loginName, String password);
    /**
     * 用户列表
     */
    PageResp<List<SysUserVo>> listUser(PageReq pageReq);

    /**
     * 查询所有的角色
     * 在添加/修改用户的时候要使用此方法
     */
    List<SysRoleDo> getAllRoles();

    /**
     * 添加用户
     */
    BaseResponse addUser(SysUserDo sysUserDo);

    /**
     * 修改用户
     */
    BaseResponse updateUser(ModifyUserReq modifyUserReq);

    /**
     * 角色列表
     */
    List<SysRoleVo> listRole();

    /**
     * 查询所有权限, 给角色分配权限时调用
     */
    List<SysPermissionVo> listAllPermission();

    /**
     * 添加角色
     */
    BaseResponse addRole(AddRoleReq addRoleReq);

    /**
     * 修改角色
     */
    BaseResponse updateRole(UpdateRoleReq updateRoleReq);

    /**
     * 删除角色
     */
    BaseResponse deleteRole(Long roleId);
}
