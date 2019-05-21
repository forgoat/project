package com.hqei.server.vo;

import java.util.List;

public class SimpleMenuVo {

    private String menuCode;
    private String menuName;
    private List<SimplePermissionVo>  permissions;

    public String getMenuCode() {
        return menuCode;
    }

    public void setMenuCode(String menuCode) {
        this.menuCode = menuCode;
    }

    public String getMenuName() {
        return menuName;
    }

    public void setMenuName(String menuName) {
        this.menuName = menuName;
    }

    public List<SimplePermissionVo> getPermissions() {
        return permissions;
    }

    public void setPermissions(List<SimplePermissionVo> permissions) {
        this.permissions = permissions;
    }
}
