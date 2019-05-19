package com.hqei.common;

public class RecordDo extends IdDo{
    private String createUser;
    private Long createTime;
    private String modifyUser;
    private Long modifyTime;

    public String getCreateUser() {
        return createUser;
    }

    public void setCreateUser(String createUser) {
        this.createUser = createUser;
    }

    public Long getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Long createTime) {
        this.createTime = createTime;
    }

    public String getModifyUser() {
        return modifyUser;
    }

    public void setModifyUser(String modifyUser) {
        this.modifyUser = modifyUser;
    }

    public Long getModifyTime() {
        return modifyTime;
    }

    public void setModifyTime(Long modifyTime) {
        this.modifyTime = modifyTime;
    }
}
