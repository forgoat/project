package com.hqei.server.vo;

import java.util.List;

public class SysRoleVo {

    private Long roleId;
    private String roleName;
    private List<SimpleMenuVo> menus;
    private List<SimpleUserVo> users;

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

    public List<SimpleMenuVo> getMenus() {
        return menus;
    }

    public void setMenus(List<SimpleMenuVo> menus) {
        this.menus = menus;
    }

    public List<SimpleUserVo> getUsers() {
        return users;
    }

    public void setUsers(List<SimpleUserVo> users) {
        this.users = users;
    }
}
