package com.hqei.server.request;

import org.hibernate.validator.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class ModifyUserReq {

    @NotNull
    private Long userId;
    private String password;
    @NotBlank
    private String nickname;
    @NotBlank
    private Long roleId;
    @NotNull
    private Integer status;

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public Long getRoleId() {
        return roleId;
    }

    public void setRoleId(Long roleId) {
        this.roleId = roleId;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }
}
