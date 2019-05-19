import Vue from 'vue'
import Router from 'vue-router'

const _import = require('./_import_' + process.env.NODE_ENV)
Vue.use(Router)

//共有路由
export const constantRouterMap = [
  {
    path: '/login', component: _import('login/HelloWorld')
  }
]

export default new Router({
  routes: constantRouterMap
})

//动态路由
export const asyncRouterMap = [
  
]