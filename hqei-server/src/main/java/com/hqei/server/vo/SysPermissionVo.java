package com.hqei.server.vo;

import com.hqei.server.domain.SysPermissionDo;

import java.util.List;

public class SysPermissionVo {

    private String menuName;
    private List<SysPermissionDo> permissions;

    public String getMenuName() {
        return menuName;
    }

    public void setMenuName(String menuName) {
        this.menuName = menuName;
    }

    public List<SysPermissionDo> getPermissions() {
        return permissions;
    }

    public void setPermissions(List<SysPermissionDo> permissions) {
        this.permissions = permissions;
    }
}
