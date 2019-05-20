package com.hqei.server.service.impl;

import com.hqei.common.BaseDao;
import com.hqei.common.BaseServiceImpl;
import com.hqei.server.Constants;
import com.hqei.server.dao.SysPermissionDao;
import com.hqei.server.domain.SysPermissionDo;
import com.hqei.server.service.SysPermissionService;
import com.hqei.server.vo.UserPermissionInfoVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class SysPermissionServiceImpl extends BaseServiceImpl<SysPermissionDo, Long> implements SysPermissionService {

    @Autowired
    private SysPermissionDao sysPermissionDao;

    @Override
    public BaseDao<SysPermissionDo, Long> getBaseDao() {
        return sysPermissionDao;
    }

    @Override
    public UserPermissionInfoVo getUserPermission(String username) {
        UserPermissionInfoVo userPermission = sysPermissionDao.getUserPermission(username);
        if (Constants.SUPER_USER_ID == userPermission.getRoleId()) {
            //查询所有菜单  所有权限
            Set<String> menuList = sysPermissionDao.getAllMenu();
            Set<String> permissionList = sysPermissionDao.getAllPermission();
            userPermission.setMenuList(menuList);
            userPermission.setPermissionList(permissionList);
        }
        return sysPermissionDao.getUserPermission(username);
    }
}
