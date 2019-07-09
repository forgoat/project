package com.hqei.server.dao;

import com.hqei.common.BaseDao;
import com.hqei.server.domain.SysPermissionDo;
import com.hqei.server.vo.SysPermissionVo;
import com.hqei.server.vo.UserPermissionInfoVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Set;

public interface SysPermissionDao extends BaseDao<SysPermissionDo, Long> {

    UserPermissionInfoVo getUserPermission(@Param("username") String username, @Param("roleId") Long roleId);

    Set<String> getAllMenu();

    Set<String> getAllPermission();

    List<SysPermissionVo> listAllPermission();

}