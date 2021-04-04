import Vue from 'vue'
import App from './App.vue'
import router from './router'
import store from './store'
import axios from 'axios'
//element-ui的引入需要引入模块和样式
import ElementUI from 'element-ui'
import 'element-ui/lib/theme-chalk/index.css'

//设置反向代理，前端发送的请求默认发送到http://localhost:8888/api
axios.defaults.baseURL = 'http://localhost:8888/api'
//全局注册，之后可通过this.$axios在其他组件发送请求
Vue.prototype.$axios = axios

Vue.config.productionTip = false

Vue.use(ElementUI)

//意思是在访问每一个路由前调用。
router.beforeEach((to, from, next) => {
    if (to.meta.requireAuth) {
        //判断vuex中userName有没有（清除缓存就没有了）
        if (store.state.user.userName) {//有就调到目标路径
            next()
        } else {
            console.log(11);
            console.log(to.fullPath);
            console.log(to);
            next({//没有调到登录
                path: 'login',
                query: { redirect: to.fullPath }
            })
        }
    } else {
        next()
    }
})

new Vue({
    router,
    store,
    render: h => h(App)
}).$mount('#app')