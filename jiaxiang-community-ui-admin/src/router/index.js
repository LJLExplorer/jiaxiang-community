import Vue from 'vue'
import VueRouter from 'vue-router'
import Login from "@/components/Login.vue"
import Home from '@/components/Home.vue'
import Welcome from "@/components/Welcome.vue"
import activity from '@/components/activity/activity.vue'
import honor from '@/components/honor/honor.vue'
import profile from '@/components/profile/profile.vue'
import grid from '@/components/grid/grid.vue'
import personal_info from '@/components/personal_info/personal_info.vue'
import serve_people from '@/components/serve_people/serve_people.vue'
import matters from '@/components/matters/matters.vue'
import proof from '@/components/proof/proof.vue'


Vue.use(VueRouter)

const routes = [
    {path: '/', redirect: '/login'},
    {path: '/login', component: Login},
    {
        path: '/home',
        component: Home,
        redirect: '/Welcome',
        children: [
            {path: '/Welcome', component: Welcome},
            {path: '/activity', component: activity},
            {path: '/honor', component: honor},
            {path: '/profile', component: profile},
            {path: '/grid', component: grid},
            {path: '/personal_info', component: personal_info},
            {path: '/serve_people', component: serve_people},
            {path: '/matters', component: matters},
            {path: '/proof', component: proof},
            /*{path: '/goods/add', component: Add},
            {path: '/orders', component: Order},
            {path: '/reports', component: Report},*/
        ]
    },
]

const router = new VueRouter({
    routes
})

router.beforeEach((to, from, next) => {
    //to 代表将要访问的路径
    //from 代表从哪个路径而来
    //next 是一个函数，表示放行  next('/login')-> 要强制跳转的路径
    if (to.path === '/login') {
        //如果是登录页直接放行
        next();
    } else {
        const tokenStr = window.sessionStorage.getItem('token');
        if (!tokenStr) {
            //如果不是token去login
            next('login');
        } else {
            //是的话放行
            next();
        }
    }
})

export default router
