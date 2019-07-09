package com.hqei.server.dao;

import com.hqei.common.BaseDao;
import com.hqei.server.domain.SysRolePermissionDo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface SysRolePermissionDao extends BaseDao<SysRolePermissionDo, Long> {


    int batchInsertRolePermission(@Param("roleId") Long roleId, @Param("permissions") List<Long> permissions);

    void deleteByRoleId(Long roleId);

    void batchRemoveRolePermission(@Param("roleId") Long roleId, @Param("permissions") List<Long> permissions);

}