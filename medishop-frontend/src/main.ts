import router from '@/router/index'

import i18n from './utils/i18n'
import { usePiniaStore } from './store'

import App from './App.vue'

import 'bootstrap/dist/css/bootstrap.css'
import 'bootstrap-vue-next/dist/bootstrap-vue-next.css'

// import 'uno.css'
import 'virtual:uno.css'

// import 'virtual:unocss-devtools'
import '@unocss/reset/normalize.css'

import '@/assets/styles/index.scss'

const app = createApp(App)
usePiniaStore(app)
app.use(router).use(i18n).mount('#app')
