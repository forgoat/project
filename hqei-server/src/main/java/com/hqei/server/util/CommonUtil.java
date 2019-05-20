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
	 * 查询分页结果后的封装工具方法
	 *
	 * @param requestJson 请求参数json,此json在之前调用fillPageParam 方法时,已经将pageRow放入
	 * @param list        查询分页对象list
	 * @param totalCount  查询出记录的总条数
	 */
	public static JSONObject successPage(final JSONObject requestJson, List<JSONObject> list, int totalCount) {
//		int pageRow = requestJson.getIntValue("pageRow");
//		int totalPage = getPageCounts(pageRow, totalCount);
//		JSONObject result = successJson();
//		JSONObject info = new JSONObject();
//		info.put("list", list);
//		info.put("totalCount", totalCount);
//		info.put("totalPage", totalPage);
//		result.put("info", info);
//		return result;
		return null;
	}

	/**
	 * 查询分页结果后的封装工具方法
	 *
	 * @param list 查询分页对象list
	 */
	public static JSONObject successPage(List<JSONObject> list) {
//		JSONObject result = successJson();
//		JSONObject info = new JSONObject();
//		info.put("list", list);
//		result.put("info", info);
//		return result;
		return null;
	}

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
