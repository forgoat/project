package com.hqei.server.service;

import com.hqei.common.BaseResponse;
import com.hqei.common.BaseService;
import com.hqei.common.page.PageRequest;
import com.hqei.common.page.PageResponse;
import com.hqei.server.domain.SysUserDo;
import com.hqei.server.request.AddUserReq;
import com.hqei.server.request.ModifyUserReq;
import com.hqei.server.vo.SimpleUserVo;
import com.hqei.server.vo.SysUserVo;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface SysUserService extends BaseService<SysUserDo, Long> {

    SysUserDo getUser(String loginName, String password);

    SysUserDo getUser(String loginName);
    /**
     * 用户列表
     */
    PageResponse<List<SysUserVo>> listUser(PageRequest pageReq);


    List<SysUserVo> listProcessUser();
    /**
     * 添加用户
     */
    BaseResponse addUser(AddUserReq addUserReq);

    /**
     * 修改用户
     */
    BaseResponse modify(ModifyUserReq modifyUserReq);

    /**
     * 删除
     */
    BaseResponse del(Long userId);

    BaseResponse modifySimpleInfo(SimpleUserVo simpleUserVo, Long userId);
}
