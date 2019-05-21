package com.hqei.server.util;

import com.alibaba.fastjson.JSONObject;

import javax.servlet.http.HttpServletRequest;
import java.util.Enumeration;
import java.util.List;

/**
 * @author: hxy
 * @description: 本后台接口系统常用的json工具类
 * @date: 2017/10/24 10:12
 */
public class CommonUtil {


	/**
	 * 获取总页数
	 *
	 * @param pageRow   每页行数
	 * @param itemCount 结果的总条数
	 */
	public static int getPageCounts(int pageRow, int itemCount) {
		if (itemCount == 0) {
			return 1;
		}
		return itemCount % pageRow > 0 ?
				itemCount / pageRow + 1 :
				itemCount / pageRow;
	}




}
