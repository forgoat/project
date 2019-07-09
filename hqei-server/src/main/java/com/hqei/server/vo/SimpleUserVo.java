package com.hqei.server.vo;

import org.hibernate.validator.constraints.NotBlank;

public class SimpleUserVo {

    private Long userId;
    @NotBlank
    private String nickname;
    private String password;

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }
}
