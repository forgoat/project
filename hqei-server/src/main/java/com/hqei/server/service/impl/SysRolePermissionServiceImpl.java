package com.hqei.server.service.impl;

import com.hqei.common.BaseDao;
import com.hqei.common.BaseServiceImpl;
import com.hqei.server.dao.SysRolePermissionDao;
import com.hqei.server.domain.SysRolePermissionDo;
import com.hqei.server.service.SysRolePermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SysRolePermissionServiceImpl extends BaseServiceImpl<SysRolePermissionDo, Long> implements SysRolePermissionService {

    @Autowired
    private SysRolePermissionDao sysRolePermissionDao;

    @Override
    public BaseDao<SysRolePermissionDo, Long> getBaseDao() {
        return sysRolePermissionDao;
    }

}
