package com.hqei.server.request;

import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.NotNull;
import java.util.List;

public class UpdateRoleReq {

    @NotNull
    private Long roleId;
    @NotBlank
    private String roleName;
    @NotEmpty
    private List<Long> permissions;

    public Long getRoleId() {
        return roleId;
    }

    public void setRoleId(Long roleId) {
        this.roleId = roleId;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public List<Long> getPermissions() {
        return permissions;
    }

    public void setPermissions(List<Long> permissions) {
        this.permissions = permissions;
    }
}
