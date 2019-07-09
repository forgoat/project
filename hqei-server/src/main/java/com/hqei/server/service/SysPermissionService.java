package com.hqei.server.service;

import com.hqei.common.BaseResponse;
import com.hqei.common.BaseService;
import com.hqei.server.domain.SysPermissionDo;
import com.hqei.server.request.LoginReq;
import com.hqei.server.vo.SysPermissionVo;
import com.hqei.server.vo.UserPermissionInfoVo;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface SysPermissionService extends BaseService<SysPermissionDo, Long> {

    UserPermissionInfoVo getUserPermission(String username, Long roleId);

    List<SysPermissionVo> listAllPermission();

    BaseResponse authLogin(LoginReq loginReq);
}
