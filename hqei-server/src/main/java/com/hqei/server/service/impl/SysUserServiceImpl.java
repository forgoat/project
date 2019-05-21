package com.hqei.server.service.impl;

import com.hqei.common.BaseDao;
import com.hqei.common.BaseResponse;
import com.hqei.common.BaseServiceImpl;
import com.hqei.common.enums.BaseCodeEnum;
import com.hqei.server.dao.SysUserDao;
import com.hqei.server.domain.SysRoleDo;
import com.hqei.server.domain.SysUserDo;
import com.hqei.server.enums.ErrorEnum;
import com.hqei.server.request.AddRoleReq;
import com.hqei.server.request.ModifyUserReq;
import com.hqei.server.request.PageReq;
import com.hqei.server.request.UpdateRoleReq;
import com.hqei.server.response.PageResp;
import com.hqei.server.service.SysRoleService;
import com.hqei.server.service.SysUserService;
import com.hqei.server.util.CommonUtil;
import com.hqei.server.vo.RoleAllInfoVo;
import com.hqei.server.vo.SysPermissionVo;
import com.hqei.server.vo.SysRoleVo;
import com.hqei.server.vo.SysUserVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Service
public class SysUserServiceImpl extends BaseServiceImpl<SysUserDo, Long> implements SysUserService {

    @Autowired
    private SysUserDao sysUserDao;
    @Autowired
    private SysRoleService sysRoleService;

    @Override
    public BaseDao<SysUserDo, Long> getBaseDao() {
        return sysUserDao;
    }

    @Override
    public SysUserDo getUser(String loginName, String password) {
        return sysUserDao.selectByNameAndPwd(loginName, password);
    }

    /**
     * 用户列表
     */
    @Override
    public PageResp<List<SysUserVo>> listUser(PageReq pageReq) {
        int count = sysUserDao.countUser();
        List<SysUserVo> list = sysUserDao.listUser(pageReq);
        int totalPage = CommonUtil.getPageCounts(pageReq.getPageRow(), count);
        PageResp<List<SysUserVo>> pageResp = new PageResp();
        pageResp.setResult(list);
        pageResp.setTotalCount(count);
        pageResp.setTotalPage(totalPage);
        pageResp.setCode(BaseCodeEnum.SUCCESS.getCode());
        pageResp.setMsg(BaseCodeEnum.SUCCESS.getMsg());
        return pageResp;
    }

    /**
     * 添加用户
     */
    @Override
    public BaseResponse addUser(SysUserDo sysUserDo) {
        int exist = sysUserDao.queryExistUsername(sysUserDo.getUsername());
        if (exist > 0) {
            return new BaseResponse(ErrorEnum.E_10009);
        }
        sysUserDo.setCreateTime(System.currentTimeMillis());
        sysUserDo.setStatus(1);
        sysUserDao.insert(sysUserDo);
        return BaseResponse.SUCCESS;
    }

    /**
     * 查询所有的角色
     * 在添加/修改用户的时候要使用此方法
     */
    @Override
    public List<SysRoleDo> getAllRoles() {
        return sysUserDao.getAllRoles();
    }

    /**
     * 修改用户
     */
    @Override
    public BaseResponse updateUser(ModifyUserReq modifyUserReq) {
        sysUserDao.updateUser(modifyUserReq);
        return BaseResponse.SUCCESS;
    }

    /**
     * 角色列表
     */
    @Override
    public List<SysRoleVo> listRole() {
        return  sysUserDao.listRole();
    }

    /**
     * 查询所有权限, 给角色分配权限时调用
     */
    @Override
    public List<SysPermissionVo> listAllPermission() {
        List<SysPermissionVo> permissions = sysUserDao.listAllPermission();
        return permissions;
    }

    /**
     * 添加角色
     */
    @Transactional(rollbackFor = Exception.class)
    @SuppressWarnings("unchecked")
    @Override
    public BaseResponse addRole(AddRoleReq addRoleReq) {
        SysRoleDo sysRoleDo = new SysRoleDo();
        sysRoleDo.setRoleName(addRoleReq.getRoleName());
        sysRoleDo.setCreateTime(System.currentTimeMillis());
        sysRoleDo.setUpdateTime(System.currentTimeMillis());
        sysRoleDo.setStatus(1);
        sysRoleService.insert(sysRoleDo);
        sysUserDao.insertRolePermission(sysRoleDo.getId(), addRoleReq.getPermissions());
        return BaseResponse.SUCCESS;
    }

    /**
     * 修改角色
     */
    @Transactional(rollbackFor = Exception.class)
    @SuppressWarnings("unchecked")
    @Override
    public BaseResponse updateRole(UpdateRoleReq updateRoleReq) {
        Long roleId = updateRoleReq.getRoleId();
        List<Long> newPerms = updateRoleReq.getPermissions();
        RoleAllInfoVo roleInfo = sysUserDao.getRoleAllInfo(roleId);
        List<Long> oldPerms = roleInfo.getPermissionIds();
        //修改角色名称
        dealRoleName(updateRoleReq, roleInfo);
        //添加新权限
        saveNewPermission(roleId, newPerms, oldPerms);
        //移除旧的不再拥有的权限
        removeOldPermission(roleId, newPerms, oldPerms);
        return BaseResponse.SUCCESS;
    }

    /**
     * 修改角色名称
     */
    private void dealRoleName(UpdateRoleReq updateRoleReq, RoleAllInfoVo roleAllInfoVo) {
        if (!updateRoleReq.getRoleName().equals(roleAllInfoVo.getRoleName())) {
            sysUserDao.updateRoleName(updateRoleReq.getRoleId(), updateRoleReq.getRoleName());
        }
    }

    /**
     * 为角色添加新权限
     */
    private void saveNewPermission(Long roleId, Collection<Long> newPerms, Collection<Long> oldPerms) {
        List<Long> waitInsert = new ArrayList<>();
        for (Long newPerm : newPerms) {
            if (!oldPerms.contains(newPerm)) {
                waitInsert.add(newPerm);
            }
        }
        if (waitInsert.size() > 0) {
            sysUserDao.insertRolePermission(roleId, waitInsert);
        }
    }

    /**
     * 删除角色 旧的 不再拥有的权限
     */
    private void removeOldPermission(Long roleId, Collection<Long> newPerms, Collection<Long> oldPerms) {
        List<Long> waitRemove = new ArrayList<>();
        for (Long oldPerm : oldPerms) {
            if (!newPerms.contains(oldPerm)) {
                waitRemove.add(oldPerm);
            }
        }
        if (waitRemove.size() > 0) {
            sysUserDao.removeOldPermission(roleId, waitRemove);
        }
    }

    /**
     * 删除角色
     */
    @Transactional(rollbackFor = Exception.class)
    @SuppressWarnings("unchecked")
    public BaseResponse deleteRole(Long roleId) {
        RoleAllInfoVo roleAllInfoVo = sysUserDao.getRoleAllInfo(roleId);
        List<Long> users = roleAllInfoVo.getUsers();
        if (users != null && users.size() > 0) {
            return new BaseResponse(ErrorEnum.E_10008);
        }
        sysUserDao.removeRole(roleId);
        sysUserDao.removeRoleAllPermission(roleId);
        return BaseResponse.SUCCESS;
    }
}
