import api from '@/utils/api'

export default {
  list(listQuery) {
    return api({
      url: "/user/list",
      method: "get",
      params: listQuery
    });
  },
  listProcessor() {
    return api({
      url: "/user/listProcessor",
      method: "get"
    });
  },
  getUserSimpleInfo() {
    return api({
      url: "/user/getUserSimpleInfo",
      method: "get"
    });
  },
  modifySimpleInfo(modifySimpleInfoReq) {
    return api({
      url: "/user/modifySimpleInfo",
      method: "post",
      data: modifySimpleInfoReq
    });
  },
  add(user) {
    return api({
      url: "/user/addUser",
      method: "post",
      data: user
    });
  },
  modify(user) {
    return api({
      url: "/user/modify",
      method: "post",
      data: user
    });
  },
  del(userId) {
    return api({
      url: "/user/delete?userId=" + userId,
      method: "post"
    });
  },
  getByRoleType(roleType) {
    return api({
      url: "/user/getByRoleType?roleType="+roleType,
      method: "get"
    });
  }

}


