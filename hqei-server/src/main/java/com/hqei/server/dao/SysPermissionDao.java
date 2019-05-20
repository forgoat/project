package com.hqei.server.dao;

import com.hqei.common.BaseDao;
import com.hqei.server.domain.SysPermissionDo;
import com.hqei.server.vo.UserPermissionInfoVo;

import java.util.Set;

public interface SysPermissionDao extends BaseDao<SysPermissionDo, Long> {

    UserPermissionInfoVo getUserPermission(String username);

    Set<String> getAllMenu();

    Set<String> getAllPermission();
}