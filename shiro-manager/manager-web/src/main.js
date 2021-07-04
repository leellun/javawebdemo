import Vue from 'vue'
import App from '@/App.vue'

import ElementUI from 'element-ui'
import 'element-ui/lib/theme-chalk/index.css'
import './styles/index.scss'

import axios from './config/httpConfig'
import '@/icons'
import router from './router'
import store from '@/store/index'

Vue.prototype.$http = axios
Vue.use(ElementUI)



Vue.config.productionTip = false

router.beforeEach((to, from, next) => {
  if (!store.state.UserToken) {
      if (to.matched.length > 0 && !to.matched.some(record => record.meta.requiresAuth)) {
          next()
      } else {
          next({ path: '/login' })
      }
  } else {
      if (!store.state.permission.permissionList) {
          store.dispatch('permission/FETCH_PERMISSION').then(() => {
              next({ path: to.path })
          })
      } else {
          if (to.path !== '/login') {
              next()
          } else {
              next(from.fullPath)
          }
      }
  }
})

router.afterEach((to, from, next) => {
  var routerList = to.matched
  store.commit('setCrumbList', routerList)
  store.commit('permission/SET_CURRENT_MENU', to.name)
})

new Vue({
  render: h => h(App),
}).$mount('#app')
