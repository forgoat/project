package com.hqei.server.service.impl;

import com.hqei.common.BaseDao;
import com.hqei.common.BaseServiceImpl;
import com.hqei.server.dao.SysRoleDao;
import com.hqei.server.domain.SysRoleDo;
import com.hqei.server.service.SysRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SysRoleServiceImpl extends BaseServiceImpl<SysRoleDo, Long> implements SysRoleService {

    @Autowired
    private SysRoleDao sysRoleDao;

    @Override
    public BaseDao<SysRoleDo, Long> getBaseDao() {
        return sysRoleDao;
    }

}
