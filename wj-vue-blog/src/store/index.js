import Vue from 'vue'
import Vuex from 'vuex'

Vue.use(Vuex)

export default new Vuex.Store({
    state: {
        user: {
            //localStorage，即本地存储，在项目打开的时候会判断本地存储中是否有 user 这个对象存在，如果存在就取出来并获得 loginName 的值，否则则把 loginName 设置为空。这样我们只要不清除缓存，登录的状态就会一直保存
            loginName: window.localStorage.getItem('user' || '[]') == null ? '' : JSON.parse(window.localStorage.getItem('user' || '[]')).loginName
        }
    },
    mutations: {
        //外部$store.commit("login", 参数) 来触发这里的 login 方法在localStorage中保存当前user（loginName）
        login(state, user) {
            state.user = user
            window.localStorage.setItem('user', JSON.stringify(user))
        },
        //在localStorage中移除当前user（loginName）
        logout(state) {
            state.user = []
            window.localStorage.removeItem('user')
        }
    },
    actions: {},
    modules: {}
})