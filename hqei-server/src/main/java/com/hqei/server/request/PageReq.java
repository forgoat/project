package com.hqei.server.request;

public class PageReq {

    private Integer pageNo = 1;
    private Integer pageRow = 50;
    private Integer offSet = 0;

    public Integer getPageNo() {
        return pageNo;
    }

    public void setPageNo(Integer pageNo) {
        this.pageNo = pageNo;
    }

    public Integer getPageRow() {
        return pageRow;
    }

    public void setPageRow(Integer pageRow) {
        this.pageRow = pageRow;
    }

    public Integer getOffSet() {
        return (pageNo - 1) * pageRow;
    }

    public void setOffSet(Integer offSet) {
        this.offSet = offSet;
    }
}
