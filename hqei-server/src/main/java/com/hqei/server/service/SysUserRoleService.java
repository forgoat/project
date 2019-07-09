package com.hqei.server.service;

import com.hqei.common.BaseService;
import com.hqei.server.domain.SysUserRoleDo;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface SysUserRoleService extends BaseService<SysUserRoleDo, Long> {

    void modifyUserRoles(Long userId, List<Long> roleIds);

    void addUserRoles(Long userId, List<Long> roleIds);

    void deleteByUserId(Long userId);

    SysUserRoleDo getByUserNameAndRoleId(String username, Long roleId);
}
