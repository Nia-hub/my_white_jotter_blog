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
//前端每次发送请求时就会带上 sessionId，shiro 就可以通过 sessionId 获取登录状态并执行是否登录的判断
axios.defaults.withCredentials = true
//全局注册，之后可通过this.$axios在其他组件发送请求
Vue.prototype.$axios = axios

Vue.config.productionTip = false

Vue.use(ElementUI)

//意思是在访问每一个路由都会被拦截。
router.beforeEach((to, from, next) => {
    //拦截后判断是否登陆
    if (to.meta.requireAuth) {
        //判断vuex中loginName有没有（清除缓存就没有了）
        if (store.state.user.loginName) {//有的话，还要判断这个用户是否处于登陆状态（因为可能登陆过，localStorage中保有信息，但是，没登录却可以直接通过路径访问相应资源）
            axios.get('/authentication').then(resp => {
                if (resp.data) {
                    next()
                } else {
                    next({//没有跳到登录
                        path: 'login',
                        query: { redirect: to.fullPath }
                    })
                }
            })
        } else {
            next({//没有跳到登录
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