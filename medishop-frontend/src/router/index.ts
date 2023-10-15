import type { RouteRecordRaw, Router } from 'vue-router'
import { createRouter, createWebHistory } from 'vue-router'

import NProgress from 'nprogress'

import { ElMessage } from 'element-plus'

import useAuctionStore from '@/store/auction'

import exceptionRoutes from '@/router/route.exception'
import asyncRoutes from '@/router/route.async'
import commonRoutes from '@/router/route.common'

const routes: Array<RouteRecordRaw> = [
  // 无鉴权的业务路由 ex:登录
  ...commonRoutes,
  // 带鉴权的业务路由
  ...asyncRoutes,
  // 异常页必须放在路由匹配规则的最后
  ...exceptionRoutes,
]

const router: Router = createRouter({
  // 新的vue-router4 使用 history路由模式 和 base前缀
  // history: createWebHistory(import.meta.env.VITE_BASE),
  history: createWebHistory(),
  routes,
})

/**
 * @remarks
 * 全局路由前置守卫，在进入路由前触发，导航在所有守卫 resolve 完之前一直处于等待中。
 * @param to - RouteLocationNormalized   即将要进入的目标
 * @param from - RouteLocationNormalizedLoaded   当前导航正在离开的路
 *
 */
router.beforeEach((to, from) => {
  console.log('全局路由前置守卫：to,from\n', to, from)
  // 设置页面标题
  document.title = (to.meta.title as string) || import.meta.env.VITE_APP_TITLE
  if (!NProgress.isStarted()) {
    NProgress.start()
  }
})
router.beforeEach((to, from, next) => {
  const auctionStore = useAuctionStore()
  const { isAuthenticated } = storeToRefs(auctionStore)
  if (to.matched.length === 0) {
    from.name ? next({ name: from.name }) : next('/')
  } else if (to.meta.requireAuth && !isAuthenticated.value) {
    ElMessage.error('请先登录!')
    next({
      path: '/',
      query: { redirect: to.fullPath }, // 将跳转的路由path作为参数，登录成功后跳转到该路由
    })
  } else {
    next()
  }
})
router.afterEach((to, from) => {
  console.log('全局路由后置守卫: to,from\n', to, from)
  NProgress.done()
})

export default router
