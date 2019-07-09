package com.hqei.server.service.impl;

import com.hqei.common.BaseDao;
import com.hqei.common.BaseResponse;
import com.hqei.common.BaseServiceImpl;
import com.hqei.common.page.PageRequest;
import com.hqei.server.dao.SysRoleDao;
import com.hqei.server.domain.SysRoleDo;
import com.hqei.server.enums.ErrorEnum;
import com.hqei.server.request.AddRoleReq;
import com.hqei.server.request.UpdateRoleReq;
import com.hqei.server.service.SysRolePermissionService;
import com.hqei.server.service.SysRoleService;
import com.hqei.server.vo.RoleForUpdateVo;
import com.hqei.server.vo.SysRoleVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Service
public class SysRoleServiceImpl extends BaseServiceImpl<SysRoleDo, Long> implements SysRoleService {

    @Autowired
    private SysRoleDao sysRoleDao;
    @Autowired
    private SysRolePermissionService sysRolePermissionService;

    @Override
    public BaseDao<SysRoleDo, Long> getBaseDao() {
        return sysRoleDao;
    }

    /**
     * 查询所有的角色
     */
    @Override
    public List<SysRoleDo> listAll() {
        return sysRoleDao.listAll();
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
        sysRoleDao.insertSelective(sysRoleDo);
        sysRolePermissionService.batchInsertRolePermission(sysRoleDo.getId(), addRoleReq.getPermissions());
        return BaseResponse.SUCCESS;
    }

    @Override
    public List<SysRoleVo> listTable(PageRequest pageReq) {
        return  sysRoleDao.listTable();
    }

    @Override
    public BaseResponse deleteByRoleId(Long roleId) {
        Integer num = sysRoleDao.hasUsers(roleId);
        if (num > 0) {
            return new BaseResponse(ErrorEnum.E_10008);
        }
        sysRoleDao.deleteByPrimaryKey(roleId);
        sysRolePermissionService.deleteByRoleId(roleId);
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
        RoleForUpdateVo roleInfo = sysRoleDao.getRoleForUpdate(roleId);
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
    private void dealRoleName(UpdateRoleReq updateRoleReq, RoleForUpdateVo roleForUpdateVo) {
        if (!updateRoleReq.getRoleName().equals(roleForUpdateVo.getRoleName())) {
            sysRoleDao.updateRoleName(updateRoleReq.getRoleId(), updateRoleReq.getRoleName());
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
            sysRolePermissionService.batchInsertRolePermission(roleId, waitInsert);
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
            sysRolePermissionService.batchRemoveRolePermission(roleId, waitRemove);
        }
    }
}
