package com.hqei.server.domain;

import com.hqei.common.IdDo;
import org.omg.CORBA.INTERNAL;

public class SysPermissionDo extends IdDo{

    private String menuCode;
    private String menuName;
    private String permissionCode;
    private String permissionName;
    private Integer requiredPermission;

    public String getMenuCode() {
        return menuCode;
    }

    public void setMenuCode(String menuCode) {
        this.menuCode = menuCode == null ? null : menuCode.trim();
    }

    public String getMenuName() {
        return menuName;
    }

    public void setMenuName(String menuName) {
        this.menuName = menuName == null ? null : menuName.trim();
    }

    public String getPermissionCode() {
        return permissionCode;
    }

    public void setPermissionCode(String permissionCode) {
        this.permissionCode = permissionCode == null ? null : permissionCode.trim();
    }

    public String getPermissionName() {
        return permissionName;
    }

    public void setPermissionName(String permissionName) {
        this.permissionName = permissionName == null ? null : permissionName.trim();
    }

    public Integer getRequiredPermission() {
        return requiredPermission;
    }

    public void setRequiredPermission(Integer requiredPermission) {
        this.requiredPermission = requiredPermission;
    }
}