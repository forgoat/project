package com.hqei.server.service.impl;

import com.hqei.common.BaseDao;
import com.hqei.common.BaseServiceImpl;
import com.hqei.server.dao.SysUserRoleDao;
import com.hqei.server.domain.SysUserRoleDo;
import com.hqei.server.service.SysUserRoleService;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class SysUserRoleServiceImpl extends BaseServiceImpl<SysUserRoleDo, Long> implements SysUserRoleService {

    @Autowired
    private SysUserRoleDao sysUserRoleDao;

    @Override
    public BaseDao<SysUserRoleDo, Long> getBaseDao() {
        return sysUserRoleDao;
    }

    @Transactional
    @Override
    public void modifyUserRoles(Long userId, List<Long> roleIds) {
        if(CollectionUtils.isEmpty(roleIds)){
            return ;
        }
        sysUserRoleDao.deleteByUserId(userId);
        sysUserRoleDao.batchInsertRoleIds(userId, roleIds);
    }

    @Override
    public void addUserRoles(Long userId, List<Long> roleIds) {
        if(CollectionUtils.isEmpty(roleIds)){
            return ;
        }
        sysUserRoleDao.batchInsertRoleIds(userId, roleIds);
    }

    @Override
    public void deleteByUserId(Long userId) {
        sysUserRoleDao.deleteByUserId(userId);
    }

    @Override
    public SysUserRoleDo getByUserNameAndRoleId(String username, Long roleId) {
        return sysUserRoleDao.getByUserNameAndRoleId(username, roleId);
    }
}
