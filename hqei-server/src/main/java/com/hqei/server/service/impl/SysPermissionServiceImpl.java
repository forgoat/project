package com.hqei.server.service.impl;

import com.hqei.common.BaseDao;
import com.hqei.common.BaseResponse;
import com.hqei.common.BaseServiceImpl;
import com.hqei.common.enums.BaseCodeEnum;
import com.hqei.server.Constants;
import com.hqei.server.dao.SysPermissionDao;
import com.hqei.server.domain.SysPermissionDo;
import com.hqei.server.domain.SysUserRoleDo;
import com.hqei.server.request.LoginReq;
import com.hqei.server.service.SysPermissionService;
import com.hqei.server.service.SysUserRoleService;
import com.hqei.server.vo.SysPermissionVo;
import com.hqei.server.vo.UserPermissionInfoVo;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
public class SysPermissionServiceImpl extends BaseServiceImpl<SysPermissionDo, Long> implements SysPermissionService {

    protected Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private SysPermissionDao sysPermissionDao;
    @Autowired
    private SysUserRoleService sysUserRoleService;

    @Override
    public BaseDao<SysPermissionDo, Long> getBaseDao() {
        return sysPermissionDao;
    }

    @Override
    public UserPermissionInfoVo getUserPermission(String username, Long roleId) {
//        UserPermissionInfoVo userPermission = sysPermissionDao.getUserPermission(username, roleId);
//        if (userPermission.getRoleIds().contains(Constants.SUPER_USER_ROLE_ID)) {
//            //查询所有菜单  所有权限
//            Set<String> menuList = sysPermissionDao.getAllMenu();
//            Set<String> permissionList = sysPermissionDao.getAllPermission();
//            userPermission.setMenuList(menuList);
//            userPermission.setPermissionList(permissionList);
//            return userPermission;
//        }
        UserPermissionInfoVo userPermission = sysPermissionDao.getUserPermission(username, roleId);
        if (userPermission.getRoleIds().contains(roleId) && roleId.equals(Constants.SUPER_USER_ROLE_ID)) {
            //查询所有菜单  所有权限
            Set<String> menuList = sysPermissionDao.getAllMenu();
            Set<String> permissionList = sysPermissionDao.getAllPermission();
            userPermission.setMenuList(menuList);
            userPermission.setPermissionList(permissionList);
            return userPermission;
        }
        return sysPermissionDao.getUserPermission(username, roleId);
    }

    /**
     * 查询所有权限, 给角色分配权限时调用
     */
    @Override
    public List<SysPermissionVo> listAllPermission() {
        List<SysPermissionVo> permissions = sysPermissionDao.listAllPermission();
        return permissions;
    }

    @Override
    public BaseResponse authLogin(LoginReq loginReq) {
        SysUserRoleDo sysUserRoleDo = sysUserRoleService.getByUserNameAndRoleId(loginReq.getUsername(), loginReq.getRoleId());
        if(null == sysUserRoleDo){
            return new BaseResponse(BaseCodeEnum.FAILURE.getCode(), "登录失败，请检查输入信息");
        }
        Subject currentUser = SecurityUtils.getSubject();
        UsernamePasswordToken token = new UsernamePasswordToken(loginReq.getUsername(), loginReq.getPassword());
        try {
            currentUser.login(token);
            Session session = currentUser.getSession();
            session.setAttribute(Constants.SESSION_USER_SELECTED_ROLE, loginReq.getRoleId());
            return BaseResponse.SUCCESS;
        } catch (AuthenticationException e) {
            logger.error("auth error:" + e.getMessage(), e);
            return new BaseResponse(BaseCodeEnum.LOGIN_ERROR);
        }
    }
}
