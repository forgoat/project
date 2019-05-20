package com.hqei.server.service;

import com.alibaba.fastjson.JSONObject;
import com.hqei.common.BaseService;
import com.hqei.server.domain.SysPermissionDo;
import com.hqei.server.vo.UserPermissionInfoVo;
import org.springframework.stereotype.Service;

@Service
public interface SysPermissionService extends BaseService<SysPermissionDo, Long> {

    UserPermissionInfoVo getUserPermission(String username);

}
