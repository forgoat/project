package com.hqei.server.dao;

import com.hqei.common.BaseDao;
import com.hqei.server.domain.SysUserRoleDo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface SysUserRoleDao extends BaseDao<SysUserRoleDo, Long> {

    int deleteByUserId(Long userId);

    int batchInsertRoleIds(@Param("userId") Long userId, @Param("roleIds") List<Long> roleIds);

    SysUserRoleDo getByUserNameAndRoleId(@Param("username") String username, @Param("roleId") Long roleId);
}