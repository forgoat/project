package com.hqei.server.service.impl;

import com.hqei.common.BaseDao;
import com.hqei.common.BaseServiceImpl;
import com.hqei.server.dao.SysRolePermissionDao;
import com.hqei.server.domain.SysRolePermissionDo;
import com.hqei.server.service.SysRolePermissionService;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class SysRolePermissionServiceImpl extends BaseServiceImpl<SysRolePermissionDo, Long> implements SysRolePermissionService {

    @Autowired
    private SysRolePermissionDao sysRolePermissionDao;

    @Override
    public BaseDao<SysRolePermissionDo, Long> getBaseDao() {
        return sysRolePermissionDao;
    }

    @Transactional
    @Override
    public void batchInsertRolePermission(Long roleId, List<Long> permissions) {
        if(CollectionUtils.isEmpty(permissions)){
            return;
        }
        sysRolePermissionDao.batchInsertRolePermission(roleId, permissions);
    }

    @Override
    public void deleteByRoleId(Long roleId) {
        sysRolePermissionDao.deleteByRoleId(roleId);
    }

    @Override
    public void batchRemoveRolePermission(Long roleId, List<Long> permissions) {
        if(CollectionUtils.isEmpty(permissions)){
            return;
        }
        sysRolePermissionDao.batchRemoveRolePermission(roleId, permissions);
    }
}
