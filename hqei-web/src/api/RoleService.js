import api from '@/utils/api'
export default {
  getAllPermission() {
    return api({
      url: "/permission/listAllPermission",
      method: "get"
    });
  },
  listTable() {
    return api({
      url: "/role/listTable",
      method: "get"
    });
  },
  listAll() {
    return api({
      url: "/role/listAll",
      method: "get"
    });
  },
  addRole(role) {
    return api({
      url: "/role/addRole",
      method: "post",
      data: role
    });
  },
  updateRole(role) {
    return api({
      url: "/role/updateRole",
      method: "post",
      data: role
    });
  },
  deleteRole(roleId) {
    return api({
      url: "/role/deleteRole",
      method: "post",
      data: {
        roleId: roleId
      }
    });
  }

}



