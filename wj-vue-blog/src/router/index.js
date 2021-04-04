import Vue from 'vue'
import VueRouter from 'vue-router'
import Home from '../components/Home.vue'
import Login from '../views/Login.vue'
import AppIndex from '../views/AppIndex.vue'
import LibraryIndex from '../components/library/LibraryIndex.vue'

Vue.use(VueRouter)

const routes = [{
        path: '/home',
        name: 'Home',
        component: Home,
        //Home页面不需要被访问
        redirect: '/index',
        children: [{
            path: '/index',
            name: 'AppIndex',
            component: AppIndex,
            meta: {
                requireAuth: true
            }
        }, {
            path: '/library',
            name: 'Library',
            component: LibraryIndex,
            meta: {
                requireAuth: true
            }
        }]
    },
    {
        path: '/login',
        name: 'Login',
        component: Login
    },

    {
        path: '/about',
        name: 'About',
        // route level code-splitting
        // this generates a separate chunk (about.[hash].js) for this route
        // which is lazy-loaded when the route is visited.
        component: () => import( /* webpackChunkName: "about" */ '../views/About.vue')
    }
]

const router = new VueRouter({
    mode: 'history',
    base: process.env.BASE_URL,
    routes
})

export default router