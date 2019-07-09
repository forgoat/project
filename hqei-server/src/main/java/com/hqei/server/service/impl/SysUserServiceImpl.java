package com.hqei.server.service.impl;

import com.hqei.common.BaseDao;
import com.hqei.common.BaseResponse;
import com.hqei.common.BaseServiceImpl;
import com.hqei.common.enums.BaseCodeEnum;
import com.hqei.common.page.PageRequest;
import com.hqei.common.page.PageResponse;
import com.hqei.server.dao.SysUserDao;
import com.hqei.server.domain.SysUserDo;
import com.hqei.server.enums.ErrorEnum;
import com.hqei.server.request.AddUserReq;
import com.hqei.server.request.ModifyUserReq;
import com.hqei.server.service.SysUserRoleService;
import com.hqei.server.service.SysUserService;
import com.hqei.server.vo.SimpleUserVo;
import com.hqei.server.vo.SysUserVo;
import org.apache.commons.lang.StringUtils;
import org.apache.shiro.crypto.RandomNumberGenerator;
import org.apache.shiro.crypto.SecureRandomNumberGenerator;
import org.apache.shiro.crypto.hash.Sha256Hash;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class SysUserServiceImpl extends BaseServiceImpl<SysUserDo, Long> implements SysUserService {

    @Autowired
    private SysUserDao sysUserDao;
    @Autowired
    private SysUserRoleService sysUserRoleService;

    public static final int HASH_INTERATIONS = 1024;

    private RandomNumberGenerator rng = new SecureRandomNumberGenerator();

    @Override
    public BaseDao<SysUserDo, Long> getBaseDao() {
        return sysUserDao;
    }

    @Override
    public SysUserDo getUser(String loginName, String password) {
        return sysUserDao.selectByNameAndPwd(loginName, password);
    }

    @Override
    public SysUserDo getUser(String loginName) {
        return sysUserDao.selectByName(loginName);
    }

    /**
     * 用户列表
     */
    @Override
    public PageResponse<List<SysUserVo>> listUser(PageRequest pageReq) {
        int count = sysUserDao.countUser();
        List<SysUserVo> list = sysUserDao.listUser(pageReq);
        PageResponse<List<SysUserVo>> pageResp = new PageResponse();
        pageResp.setResult(list);
        pageResp.setTotalCount(new Long(count));
        pageResp.setCode(BaseCodeEnum.SUCCESS.getCode());
        pageResp.setMsg(BaseCodeEnum.SUCCESS.getMsg());
        return pageResp;
    }

    /**
     * 用户列表
     */
    @Override
    public List<SysUserVo> listProcessUser() {
        return sysUserDao.listProcessUser();
    }

    /**
     * 添加用户
     */
    @Transactional
    @Override
    public BaseResponse addUser(AddUserReq addUserReq) {
        int exist = sysUserDao.queryExistUsername(addUserReq.getUsername());
        if (exist > 0) {
            return new BaseResponse(ErrorEnum.E_10009);
        }
        SysUserDo sysUserDo = new SysUserDo();
        ByteSource salt = rng.nextBytes();
        sysUserDo.setPassword(encryptPassword(addUserReq.getPassword(), salt));
        sysUserDo.setSalt(salt.toBase64());
        sysUserDo.setNickname(addUserReq.getNickname());
        sysUserDo.setUsername(addUserReq.getUsername());
        sysUserDo.setCreateTime(System.currentTimeMillis());
        sysUserDo.setUpdateTime(System.currentTimeMillis());
        sysUserDo.setStatus(1);
        sysUserDao.insert(sysUserDo);
        sysUserRoleService.addUserRoles(sysUserDo.getId(), addUserReq.getRoleIds());
        return BaseResponse.SUCCESS;
    }

    private String encryptPassword(String password, ByteSource salt) {
        return new Sha256Hash(password, salt, HASH_INTERATIONS).toBase64();
    }

    /**
     * 修改用户
     */
    @Transactional
    @Override
    public BaseResponse modify(ModifyUserReq modifyUserReq) {
        sysUserDao.updateUser(modifyUserReq);
        sysUserRoleService.modifyUserRoles(modifyUserReq.getUserId(), modifyUserReq.getRoleIds());
        return BaseResponse.SUCCESS;
    }

    @Override
    public BaseResponse del(Long userId) {
        sysUserDao.deleteByPrimaryKey(userId);
        sysUserRoleService.deleteByUserId(userId);
        return BaseResponse.SUCCESS;
    }

    @Override
    public BaseResponse modifySimpleInfo(SimpleUserVo simpleUserVo, Long userId) {
        SysUserDo sysUserDo = sysUserDao.selectByPrimaryKey(userId);
        sysUserDo.setNickname(simpleUserVo.getNickname());
        if(StringUtils.isNotBlank(simpleUserVo.getPassword())){
            ByteSource salt = rng.nextBytes();
            sysUserDo.setPassword(encryptPassword(simpleUserVo.getPassword(), salt));
            sysUserDo.setSalt(salt.toBase64());
        }
        sysUserDao.updateByPrimaryKey(sysUserDo);
        return BaseResponse.SUCCESS;
    }

}
