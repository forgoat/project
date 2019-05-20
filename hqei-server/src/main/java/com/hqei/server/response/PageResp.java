package com.hqei.server.response;

import com.hqei.common.BaseResponse;

public class PageResp<T> extends BaseResponse {

    private Integer totalPage;
    private Integer totalCount;

    public Integer getTotalPage() {
        return totalPage;
    }

    public void setTotalPage(Integer totalPage) {
        this.totalPage = totalPage;
    }

    public Integer getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(Integer totalCount) {
        this.totalCount = totalCount;
    }
}
