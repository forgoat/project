package com.hqei.server.dao;

import com.hqei.common.BaseDao;
import com.hqei.server.domain.SysRoleDo;
import com.hqei.server.domain.SysUserDo;
import com.hqei.server.request.ModifyUserReq;
import com.hqei.server.request.PageReq;
import com.hqei.server.vo.RoleAllInfoVo;
import com.hqei.server.vo.SysPermissionVo;
import com.hqei.server.vo.SysRoleVo;
import com.hqei.server.vo.SysUserVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface SysUserDao extends BaseDao<SysUserDo, Long> {

    SysUserDo selectByNameAndPwd(@Param("loginName") String loginName, @Param("password") String password);

    /**
     * 查询用户数量
     */
    int countUser();

    /**
     * 查询用户列表
     */
    List<SysUserVo> listUser(PageReq pageReq);

    /**
     * 查询所有的角色
     * 在添加/修改用户的时候要使用此方法
     */
    List<SysRoleDo> getAllRoles();

    /**
     * 校验用户名是否已存在
     */
    int queryExistUsername(String username);

    /**
     * 修改用户
     */
    int updateUser(ModifyUserReq modifyUserReq);

    /**
     * 角色列表
     */
    List<SysRoleVo> listRole();

    /**
     * 查询所有权限, 给角色分配权限时调用
     */
    List<SysPermissionVo> listAllPermission();

    /**
     * 批量插入角色的权限
     *
     * @param roleId      角色ID
     * @param permissions 权限
     */
    int insertRolePermission(@Param("roleId") Long roleId, @Param("permissions") List<Long> permissions);

    /**
     * 将角色曾经拥有而修改为不再拥有的权限 delete_status改为'2'
     */
    int removeOldPermission(@Param("roleId") Long roleId, @Param("permissions") List<Long> permissions);

    /**
     * 修改角色名称
     */
    int updateRoleName(@Param("roleId") Long roleId, @Param("roleName") String roleName);

    /**
     * 查询某角色的全部数据
     * 在删除和修改角色时调用
     */
    RoleAllInfoVo getRoleAllInfo(Long roleId);

    /**
     * 删除角色
     */
    int removeRole(Long roleId);

    /**
     * 删除本角色全部权限
     */
    int removeRoleAllPermission(Long roleId);
}