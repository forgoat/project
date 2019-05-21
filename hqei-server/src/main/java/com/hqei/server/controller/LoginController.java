package com.hqei.server.controller;

import com.hqei.common.BaseResponse;
import com.hqei.common.enums.BaseCodeEnum;
import com.hqei.server.Constants;
import com.hqei.server.domain.SysUserDo;
import com.hqei.server.request.LoginReq;
import com.hqei.server.service.SysPermissionService;
import com.hqei.server.vo.UserPermissionInfoVo;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/login")
public class LoginController extends SysController{

	protected Logger logger = LoggerFactory.getLogger(getClass());

	@Autowired
	private SysPermissionService sysPermissionService;

	/**
	 * 登录
	 */
	@PostMapping("/auth")
	public BaseResponse authLogin(@Valid @RequestBody LoginReq loginReq) {
		Subject currentUser = SecurityUtils.getSubject();
		UsernamePasswordToken token = new UsernamePasswordToken(loginReq.getUsername(), loginReq.getPassword());
		try {
			currentUser.login(token);
			return BaseResponse.SUCCESS;
		} catch (AuthenticationException e) {
			logger.error("auth error:" + e.getMessage(), e);
			return BaseResponse.ERROR;
		}
	}

	/**
	 * 查询当前登录用户的信息
	 */
	@PostMapping("/getInfo")
	public BaseResponse<UserPermissionInfoVo> getInfo() {
		//从session获取用户信息
		Session session = SecurityUtils.getSubject().getSession();
		SysUserDo userInfo = (SysUserDo) session.getAttribute(Constants.SESSION_USER_INFO);
		String username = userInfo.getUsername();
		UserPermissionInfoVo userPermission = sysPermissionService.getUserPermission(username);
		session.setAttribute(Constants.SESSION_USER_PERMISSION, userPermission);
		return new BaseResponse<>(BaseCodeEnum.SUCCESS, userPermission);
	}

	/**
	 * 登出
	 */
	@PostMapping("/logout")
	public BaseResponse logout() {
		try {
			Subject currentUser = SecurityUtils.getSubject();
			currentUser.logout();
		} catch (Exception e) {
			logger.error("logout error:" + e.getMessage(), e);
		}
		return BaseResponse.SUCCESS;
	}
}
