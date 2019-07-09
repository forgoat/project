package com.hqei.server.request;

import javax.validation.constraints.NotNull;

public class DeleteRoleReq {

    @NotNull
    private Long roleId;

    public Long getRoleId() {
        return roleId;
    }

    public void setRoleId(Long roleId) {
        this.roleId = roleId;
    }
}
