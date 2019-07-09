package com.hqei.server.service;

import com.hqei.common.BaseResponse;
import com.hqei.common.BaseService;
import com.hqei.common.page.PageRequest;
import com.hqei.server.domain.SysRoleDo;
import com.hqei.server.request.AddRoleReq;
import com.hqei.server.request.UpdateRoleReq;
import com.hqei.server.vo.SysRoleVo;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface SysRoleService extends BaseService<SysRoleDo, Long> {

    /**
     * 查询所有的角色
     */
    List<SysRoleDo> listAll();

    /**
     * 添加角色
     */
    BaseResponse addRole(AddRoleReq addRoleReq);

    List<SysRoleVo> listTable(PageRequest pageReq);

    BaseResponse deleteByRoleId(Long roleId);

    BaseResponse updateRole(UpdateRoleReq updateRoleReq);
}
