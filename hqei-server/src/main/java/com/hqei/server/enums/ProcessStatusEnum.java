package com.hqei.server.enums;

/**
 * Created by BensonZc on 2019/5/29.
 */
public enum ProcessStatusEnum {
    UN_PROCESS(0),SEARCHED(1),PROCESSED(2),AUDITED(3);

    private Integer status;

    ProcessStatusEnum(Integer status) {
        this.status = status;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }
}
