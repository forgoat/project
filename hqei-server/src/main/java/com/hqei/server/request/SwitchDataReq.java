package com.hqei.server.request;

import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.NotNull;

/**
 * @Auther: zhangchen
 * @Date: 2019/7/3/0003 14:26
 * @Description:
 */
public class SwitchDataReq {
    private Long dataId;
    @NotNull
    private Long taskId;
    @NotNull
    private Integer dataType;
    @NotNull
    private Integer switchData;

    public Long getDataId() {
        return dataId;
    }

    public void setDataId(Long dataId) {
        this.dataId = dataId;
    }

    public Long getTaskId() {
        return taskId;
    }

    public void setTaskId(Long taskId) {
        this.taskId = taskId;
    }

    public Integer getDataType() {
        return dataType;
    }

    public void setDataType(Integer dataType) {
        this.dataType = dataType;
    }

    public Integer getSwitchData() {
        return switchData;
    }

    public void setSwitchData(Integer switchData) {
        this.switchData = switchData;
    }
}
