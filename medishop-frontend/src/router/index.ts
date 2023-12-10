import { createRouter, createWebHistory } from 'vue-router/auto'

import NProgress from 'nprogress'

import { ElMessage } from 'element-plus'

import useAuthStore from '@/store/auth'

const router = createRouter({
  history: createWebHistory(import.meta.env.VITE_BASE),
})

/**
 * @remarks
 * 全局路由前置守卫，在进入路由前触发，导航在所有守卫 resolve 完之前一直处于等待中。
 * @param to - RouteLocationNormalized   即将要进入的目标
 * @param from - RouteLocationNormalizedLoaded   当前导航正在离开的路
 *
 */
router.beforeEach((to, from) => {
  console.log('全局路由前置守卫,to, from\n', to, from)
  // 设置页面标题
  document.title = (to.meta.title as string) || import.meta.env.VITE_APP_TITLE
  if (!NProgress.isStarted()) {
    NProgress.start()
  }
})
router.beforeEach((to, from, next) => {
  const authStore = useAuthStore()
  const { isAuthenticated } = storeToRefs(authStore)
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
