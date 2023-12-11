import router from '@/router/index'

import i18n from './utils/i18n'
import { usePiniaStore } from './store'

import App from './App.vue'

import 'bootstrap/dist/css/bootstrap.css'
import 'bootstrap-vue-next/dist/bootstrap-vue-next.css'

import 'virtual:uno.css'

// 和其它UI组件一同使用，尽可能不覆盖reset
import '@unocss/reset/tailwind-compat.css'

// 自定义样式
import '@/assets/styles/index.scss'

import 'uno.css'

// import 'virtual:unocss-devtools'

const app = createApp(App)
usePiniaStore(app)
app.use(router).use(i18n).mount('#app')
