package com.hqei.server.controller.user;

import com.hqei.common.BaseResponse;
import com.hqei.common.enums.BaseCodeEnum;
import com.hqei.server.Constants;
import com.hqei.server.controller.SysController;
import com.hqei.server.domain.SysUserDo;
import com.hqei.server.request.LoginReq;
import com.hqei.server.service.SysPermissionService;
import com.hqei.server.vo.UserPermissionInfoVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.SecurityUtils;
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
@Api(tags = "登录相关")
public class LoginController extends SysController {

	protected Logger logger = LoggerFactory.getLogger(getClass());

	@Autowired
	private SysPermissionService sysPermissionService;

	/**
	 * 登录
	 */
	@PostMapping("/auth")
	@ApiOperation(value="登录")
	public BaseResponse authLogin(@Valid @RequestBody LoginReq loginReq) {
		return sysPermissionService.authLogin(loginReq);
	}

	/**
	 * 查询当前登录用户的信息
	 */
	@PostMapping("/getInfo")
	@ApiOperation(value="获取登录用户信息")
	public BaseResponse<UserPermissionInfoVo> getInfo() {
		//从session获取用户信息
		Session session = SecurityUtils.getSubject().getSession();
		SysUserDo userInfo = (SysUserDo) session.getAttribute(Constants.SESSION_USER_INFO);
		String username = userInfo.getUsername();
		Long roleId = (Long)session.getAttribute(Constants.SESSION_USER_SELECTED_ROLE);
		UserPermissionInfoVo userPermission = sysPermissionService.getUserPermission(username, roleId);
		session.setAttribute(Constants.SESSION_USER_PERMISSION, userPermission);
		return new BaseResponse<>(BaseCodeEnum.SUCCESS, userPermission);
	}

	/**
	 * 登出
	 */
	@PostMapping("/logout")
	@ApiOperation(value="登出")
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
