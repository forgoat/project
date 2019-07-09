package com.hqei.server.request;

public class AuditDepartmentReq extends AuditTaskReq {

    private String deptUrl;

    public String getDeptUrl() {
        return deptUrl;
    }

    public void setDeptUrl(String deptUrl) {
        this.deptUrl = deptUrl;
    }
}
