// 需要鉴权的业务路由
import type { RouteRecordRaw } from 'vue-router'

const indexPage = () => import('@/pages/index.vue')
const userInfoPage = () => import('@/components/UserInfo.vue')
const myGoodsPage = () => import('@/components/table/MyGoods.vue')
const auctionRankPage = () => import('@/components/table/AuctionRank.vue')
const myAuctionsPage = () => import('@/components/table/MyAuctions.vue')
const shopCartPage = () => import('@/components/table/ShoppingCart.vue')
const goodDescPage = () => import('@/components/GoodDesc.vue')
const asyncRoutes: Array<RouteRecordRaw> = [
  {
    path: '/index',
    name: 'indexPage',
    component: indexPage,
    meta: {
      requireAuth: true,
    },
  },
  {
    path: '/userInfo/:data',
    name: 'userInfoPage',
    component: userInfoPage,
    meta: {
      requireAuth: true,
    },
  },
  {
    path: '/myGoods/:data',
    name: 'myGoods',
    component: myGoodsPage,
    meta: {
      requireAuth: true,
    },
  },
  {
    path: '/myAuctions/:data',
    name: 'myAuctions',
    component: myAuctionsPage,
    meta: {
      requireAuth: true,
    },
  },
  {
    path: '/shopCart/:data',
    name: 'shopCart',
    component: shopCartPage,
    meta: {
      requireAuth: true,
    },
  },
  {
    path: '/rank/:data',
    name: 'auctionRank',
    component: auctionRankPage,
    meta: {
      requireAuth: true,
    },
  },
  {
    path: '/goodDesc/:data',
    name: 'goodDesc',
    component: goodDescPage,
    meta: {
      requireAuth: true,
    },
  },
  {
    path: '/auctionRank/:data',
    name: 'auctionRank',
    component: auctionRankPage,
    meta: {
      requireAuth: true,
    },
  },
]

export default asyncRoutes
