// 不需要鉴权的业务路由
import type { RouteRecordRaw } from 'vue-router'

const registerPage = () => import('@/pages/register.vue')
const loginPage = () => import('@/pages/login.vue')

const commonRoutes: Array<RouteRecordRaw> = [
    {
        path: '/',
        name: 'loginPage',
        component: loginPage,
    },
    {
        path: '/register',
        name: 'registerPage',
        component: registerPage,
    },
]

export default commonRoutes
