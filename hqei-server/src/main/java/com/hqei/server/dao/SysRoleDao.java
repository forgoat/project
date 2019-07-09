package com.hqei.server.dao;

import com.hqei.common.BaseDao;
import com.hqei.server.domain.SysRoleDo;
import com.hqei.server.vo.RoleForUpdateVo;
import com.hqei.server.vo.SysRoleVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface SysRoleDao extends BaseDao<SysRoleDo, Long> {

    /**
     * 查询所有的角色
     */
    List<SysRoleDo> listAll();

    List<SysRoleVo> listTable();

    Integer hasUsers(Long roleId);

    int updateRoleName(@Param("roleId") Long roleId, @Param("roleName") String roleName);

    RoleForUpdateVo getRoleForUpdate(Long roleId);
}