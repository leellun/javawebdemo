import Vue from 'vue'
import App from './App.vue'
import router from './routers'
import store from './store'

var vm=new Vue({
  render: h => h(App),
  router:router,
  store
});
vm.$mount('#app')
