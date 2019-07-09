package com.hqei.server.controller;

import com.hqei.common.BaseController;
import com.hqei.server.Constants;
import com.hqei.server.domain.SysUserDo;
import com.hqei.server.vo.UserPermissionInfoVo;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.session.Session;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SysController extends BaseController {

    private static final Logger logger = LoggerFactory.getLogger(SysController.class);

    /**
     * 获取当前登录用户信息
     * @return
     */
    protected SysUserDo getLoginUser(){
        Session session = SecurityUtils.getSubject().getSession();
        SysUserDo user = (SysUserDo) session.getAttribute(Constants.SESSION_USER_INFO);
        return user;
    }

    protected boolean isSuperUser(){
        return getLoginUser().getId().equals(Constants.SUPER_USER_ID);
    }

    protected boolean isAuditUser(){
        Session session = SecurityUtils.getSubject().getSession();
        UserPermissionInfoVo permission = (UserPermissionInfoVo) session.getAttribute(Constants.SESSION_USER_PERMISSION);
        if(permission.getPermissionList().contains("task:audit")){
            return true;
        }
        return false;
    }

}
