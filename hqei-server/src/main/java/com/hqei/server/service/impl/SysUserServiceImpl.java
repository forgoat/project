package com.hqei.server.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.hqei.common.BaseDao;
import com.hqei.common.BaseResponse;
import com.hqei.common.BaseServiceImpl;
import com.hqei.common.enums.BaseCodeEnum;
import com.hqei.server.dao.SysUserDao;
import com.hqei.server.domain.SysUserDo;
import com.hqei.server.enums.ErrorEnum;
import com.hqei.server.request.ModifyUserReq;
import com.hqei.server.request.PageReq;
import com.hqei.server.response.PageResp;
import com.hqei.server.service.SysUserService;
import com.hqei.server.util.CommonUtil;
import com.hqei.server.vo.SysUserVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Set;

@Service
public class SysUserServiceImpl extends BaseServiceImpl<SysUserDo, Long> implements SysUserService {

    @Autowired
    private SysUserDao sysUserDao;

    @Override
    public BaseDao<SysUserDo, Long> getBaseDao() {
        return sysUserDao;
    }

    @Override
    public SysUserDo getUser(String loginName, String password) {
        return sysUserDao.selectByNameAndPwd(loginName, password);
    }

    /**
     * 用户列表
     */
    @Override
    public PageResp<List<SysUserVo>> listUser(PageReq pageReq) {
        int count = sysUserDao.countUser();
        List<SysUserVo> list = sysUserDao.listUser(pageReq);
        int totalPage = CommonUtil.getPageCounts(pageReq.getPageRow(), count);
        PageResp<List<SysUserVo>> pageResp = new PageResp();
        pageResp.setResult(list);
        pageResp.setTotalCount(count);
        pageResp.setTotalPage(totalPage);
        pageResp.setCode(BaseCodeEnum.SUCCESS.getCode());
        pageResp.setMsg(BaseCodeEnum.SUCCESS.getMsg());
        return pageResp;
    }

    /**
     * 添加用户
     */
    @Override
    public BaseResponse addUser(SysUserDo sysUserDo) {
        int exist = sysUserDao.queryExistUsername(sysUserDo.getUsername());
        if (exist > 0) {
            return new BaseResponse(ErrorEnum.E_10009);
        }
        sysUserDo.setCreateTime(System.currentTimeMillis());
        sysUserDo.setStatus(1);
        sysUserDao.insert(sysUserDo);
        return BaseResponse.SUCCESS;
    }

    /**
     * 查询所有的角色
     * 在添加/修改用户的时候要使用此方法
     */
    @Override
    public JSONObject getAllRoles() {
        List<JSONObject> roles = sysUserDao.getAllRoles();
        return CommonUtil.successPage(roles);
    }

    /**
     * 修改用户
     */
    @Override
    public BaseResponse updateUser(ModifyUserReq modifyUserReq) {
        sysUserDao.updateUser(modifyUserReq);
        return BaseResponse.SUCCESS;
    }

    /**
     * 角色列表
     */
    @Override
    public JSONObject listRole() {
        List<JSONObject> roles = sysUserDao.listRole();
        return CommonUtil.successPage(roles);
    }

    /**
     * 查询所有权限, 给角色分配权限时调用
     */
    @Override
    public JSONObject listAllPermission() {
        List<JSONObject> permissions = sysUserDao.listAllPermission();
        return CommonUtil.successPage(permissions);
    }

    /**
     * 添加角色
     */
    @Transactional(rollbackFor = Exception.class)
    @SuppressWarnings("unchecked")
    @Override
    public JSONObject addRole(JSONObject jsonObject) {
//        sysUserDao.insertRole(jsonObject);
//        sysUserDao.insertRolePermission(jsonObject.getString("roleId"), (List<Integer>) jsonObject.get("permissions"));
//        return CommonUtil.successJson();
        return null;
    }

    /**
     * 修改角色
     */
    @Transactional(rollbackFor = Exception.class)
    @SuppressWarnings("unchecked")
    @Override
    public JSONObject updateRole(JSONObject jsonObject) {
//        String roleId = jsonObject.getString("roleId");
//        List<Integer> newPerms = (List<Integer>) jsonObject.get("permissions");
//        JSONObject roleInfo = sysUserDao.getRoleAllInfo(jsonObject);
//        Set<Integer> oldPerms = (Set<Integer>) roleInfo.get("permissionIds");
//        //修改角色名称
//        dealRoleName(jsonObject, roleInfo);
//        //添加新权限
//        saveNewPermission(roleId, newPerms, oldPerms);
//        //移除旧的不再拥有的权限
//        removeOldPermission(roleId, newPerms, oldPerms);
//        return CommonUtil.successJson();
        return null;
    }

    /**
     * 修改角色名称
     */
    private void dealRoleName(JSONObject paramJson, JSONObject roleInfo) {
        String roleName = paramJson.getString("roleName");
        if (!roleName.equals(roleInfo.getString("roleName"))) {
            sysUserDao.updateRoleName(paramJson);
        }
    }

    /**
     * 为角色添加新权限
     */
    private void saveNewPermission(String roleId, Collection<Integer> newPerms, Collection<Integer> oldPerms) {
        List<Integer> waitInsert = new ArrayList<>();
        for (Integer newPerm : newPerms) {
            if (!oldPerms.contains(newPerm)) {
                waitInsert.add(newPerm);
            }
        }
        if (waitInsert.size() > 0) {
            sysUserDao.insertRolePermission(roleId, waitInsert);
        }
    }

    /**
     * 删除角色 旧的 不再拥有的权限
     */
    private void removeOldPermission(String roleId, Collection<Integer> newPerms, Collection<Integer> oldPerms) {
        List<Integer> waitRemove = new ArrayList<>();
        for (Integer oldPerm : oldPerms) {
            if (!newPerms.contains(oldPerm)) {
                waitRemove.add(oldPerm);
            }
        }
        if (waitRemove.size() > 0) {
            sysUserDao.removeOldPermission(roleId, waitRemove);
        }
    }

    /**
     * 删除角色
     */
    @Transactional(rollbackFor = Exception.class)
    @SuppressWarnings("unchecked")
    @Override
    public JSONObject deleteRole(JSONObject jsonObject) {
//        JSONObject roleInfo = sysUserDao.getRoleAllInfo(jsonObject);
//        List<JSONObject> users = (List<JSONObject>) roleInfo.get("users");
//        if (users != null && users.size() > 0) {
//            return CommonUtil.errorJson(ErrorEnum.E_10008);
//        }
//        sysUserDao.removeRole(jsonObject);
//        sysUserDao.removeRoleAllPermission(jsonObject);
//        return CommonUtil.successJson();
        return null;
    }
}
