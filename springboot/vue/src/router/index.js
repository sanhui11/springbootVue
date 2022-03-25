import Vue from 'vue'
import VueRouter from 'vue-router'
import store from "@/store";
import current from "element-ui/packages/table/src/store/current";


Vue.use(VueRouter)

const routes = [
    {
        path: '/',
        component: () => import(/* webpackChunkName: "about" */ '../views/Manage.vue'),
        redirect: "/home",
        meta: {
            requireAuth: true, // 判断是否需要登录
        },
        children: [
            {path: 'home', name: '首页', component: () => import( '../views/Home.vue')},
            {path: 'user', name: '用户管理', component: () => import( '../views/User.vue')},
            {path: 'person', name: '个人信息', component: () => import( '../views/Person.vue')},
            {path: 'file', name: '文件管理', component: () => import( '../views/File.vue')},
        ]
    },
    {
        path: '/about',
        name: 'about',

        // route level code-splitting
        // this generates a separate chunk (about.[hash].js) for this route
        // which is lazy-loaded when the route is visited.
        component: () => import(/* webpackChunkName: "about" */ '../views/AboutView.vue')
    },
    {
        path: '/login',
        name: '登录',
        // route level code-splitting
        // this generates a separate chunk (about.[hash].js) for this route
        // which is lazy-loaded when the route is visited.
        component: () => import(/* webpackChunkName: "about" */ '../views/Login.vue')
    },
    {
        path: '/register',
        name: '注册',
        // route level code-splitting
        // this generates a separate chunk (about.[hash].js) for this route
        // which is lazy-loaded when the route is visited.
        component: () => import(/* webpackChunkName: "about" */ '../views/Register.vue')
    },
    {
        path: '/vlog',
        name: '小红书卡片',
        // route level code-splitting
        // this generates a separate chunk (about.[hash].js) for this route
        // which is lazy-loaded when the route is visited.
        component: () => import(/* webpackChunkName: "about" */ '../views/Vlog.vue')
    },
    {
        path: '/test',
        name: '测试',

        // route level code-splitting
        // this generates a separate chunk (about.[hash].js) for this route
        // which is lazy-loaded when the route is visited.
        component: () => import(/* webpackChunkName: "about" */ '../views/test.vue')
    },
]

const router = new VueRouter({
    mode: 'history',
    base: process.env.BASE_URL,
    routes
})

//路由守卫
router.beforeEach((to, from, next) => {

    localStorage.setItem("currentPathName",to.name)
    store.commit("setPath")
    // store.commit("setPath")
    //to将要访问的路径，from表示从那个路径跳转过来，next表示放行
    next()
})


export default router
