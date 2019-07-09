package com.hqei.server.service;

import com.hqei.common.BaseService;
import com.hqei.server.domain.SysRolePermissionDo;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface SysRolePermissionService extends BaseService<SysRolePermissionDo, Long> {


    void batchInsertRolePermission(Long roleId, List<Long> permissions);

    void deleteByRoleId(Long roleId);

    void batchRemoveRolePermission(Long roleId, List<Long> permissions);
}
