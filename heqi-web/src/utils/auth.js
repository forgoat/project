import Cookies from 'js-cookie'

//和cookie中登陆状态相关的函数
const LoginKey = 'hasLogin'

export function getToken() {
  return Cookies.get(LoginKey);
}

export function setToken() {
  return Cookies.set(LoginKey, "1")
}

export function removeToken() {
  return Cookies.remove(LoginKey)
}
