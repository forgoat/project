package com.hqei.server;

public class Constants {

	public static final Long SUPER_USER_ID = 10000L;
	public static final Long SUPER_USER_ROLE_ID = 1L;

	/**
	 * session中存放用户信息的key值
	 */
	public static final String SESSION_USER_INFO = "userInfo";
	public static final String SESSION_USER_PERMISSION = "userPermission";
	public static final String SESSION_USER_SELECTED_ROLE = "userSelectedRole";
}
