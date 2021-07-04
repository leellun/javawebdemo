import Vue from 'vue'
import Router from 'vue-router'
import Login from '@/pages/login/login'
Vue.use(Router)
export default new Router({
    routers:[
        {
            path:"/login",
            component:Login
        }
    ]
})