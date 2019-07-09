package com.hqei.common.page;

import com.hqei.common.BaseResponse;
import com.hqei.common.enums.BaseCodeEnum;

/**
 * @Auther: zhangchen
 * @Date: 2019/6/4/0004 16:36
 * @Description:
 */
public class PageResponse<T> extends BaseResponse{
    private Long totalCount = 0L;

    public PageResponse() {
    }

    public PageResponse(Enum enumObj) {
        super(enumObj);
    }

    public PageResponse(BaseCodeEnum baseCodeEnum, Long totalCount, T result) {
        super(baseCodeEnum, result);
        this.totalCount = totalCount;
    }

    public Long getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(Long totalCount) {
        this.totalCount = totalCount;
    }
}
