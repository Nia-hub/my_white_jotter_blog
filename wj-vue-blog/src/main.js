import Vue from 'vue'
import App from './App.vue'
import router from './router'
import store from './store'
import axios from 'axios'
//element-ui的引入需要引入模块和样式
import ElementUI from 'element-ui'
import 'element-ui/lib/theme-chalk/index.css'

//设置反向代理，前端发送的请求默认发送到http://localhost:8443/api
axios.defaults.baseURL = 'http://localhost:9999/api'
//全局注册，之后可通过this.$axios在其他组件发送请求
Vue.prototype.$axios = axios

Vue.config.productionTip = false

Vue.use(ElementUI)

new Vue({
  router,
  store,
  render: h => h(App)
}).$mount('#app')
