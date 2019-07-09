package com.hqei.server.request;

import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.NotNull;

public class ProcessOrgReq {
    @NotNull
    private Long id;
    @NotBlank
    private String orgUrl;
    private String orgName;
    private Integer isHospital = 0;
    @NotBlank
    private String saveUser;

    public Integer getIsHospital() {
        return isHospital;
    }

    public void setIsHospital(Integer isHospital) {
        this.isHospital = isHospital;
    }

    public String getOrgName() {
        return orgName;
    }

    public void setOrgName(String orgName) {
        this.orgName = orgName;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getOrgUrl() {
        return orgUrl;
    }

    public void setOrgUrl(String orgUrl) {
        this.orgUrl = orgUrl;
    }

    public String getSaveUser() {
        return saveUser;
    }

    public void setSaveUser(String saveUser) {
        this.saveUser = saveUser;
    }
}
