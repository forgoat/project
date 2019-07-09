package com.hqei.server.dao;

import com.hqei.common.BaseDao;
import com.hqei.common.page.PageRequest;
import com.hqei.server.domain.SysUserDo;
import com.hqei.server.request.ModifyUserReq;
import com.hqei.server.vo.SysUserVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface SysUserDao extends BaseDao<SysUserDo, Long> {

    SysUserDo selectByNameAndPwd(@Param("loginName") String loginName, @Param("password") String password);

    /**
     * 查询用户数量
     */
    int countUser();

    /**
     * 查询用户列表
     */
    List<SysUserVo> listUser(PageRequest pageReq);

    /**
     * 获取加工用户
     */
    List<SysUserVo> listProcessUser();

    /**
     * 校验用户名是否已存在
     */
    int queryExistUsername(String username);

    /**
     * 修改用户
     */
    int updateUser(ModifyUserReq modifyUserReq);


    SysUserDo selectByName(String loginName);
}