// The Vue build version to load with the `import` command
// (runtime-only or standalone) has been set in webpack.base.conf with an alias.
import Vue from 'vue'
import App from './App'
import router from './router'
import 'normalize.css/normalize.css'
import store from './store'
import '@/permission' //鉴权工具
import {default as api} from './utils/api'
import {hasPermission} from './utils/hasPermission'

Vue.prototype.api = api //添加属性api
Vue.prototype.hasPerm = hasPermission

Vue.config.productionTip = false

/* eslint-disable no-new */
new Vue({
  el: '#app',
  router,
  store,
  components: { App },
  template: '<App/>'
})
