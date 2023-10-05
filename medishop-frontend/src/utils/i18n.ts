// 国际化配置
import type { App } from 'vue'

import { createI18n } from 'vue-i18n'
import messages from '@intlify/unplugin-vue-i18n/messages'

// 在语言切换时保存当前语言到本地存储
export function saveLanguage(locale: string) {
    localStorage.setItem('language', locale)
}

// 在页面加载时从本地存储读取当前语言设置
export function getSavedLanguage() {
    return localStorage.getItem('language')
}

// 默认语言设置（如果没有保存的设置）
const defaultLanguage = 'zh-CN'

// 根据保存的语言设置或默认设置应用语言
export function applyLanguage(i18n: { global: { locale: string } }) {
    const savedLanguage = getSavedLanguage()
    const language = savedLanguage || defaultLanguage
    // eslint-disable-next-line no-param-reassign
    i18n.global.locale = language
}

const i18n = createI18n({
    legacy: false,
    locale: getSavedLanguage() || defaultLanguage,
    fallbackLocale: defaultLanguage,
    // Refer a global scope Composer instance of i18n
    globalInjection: true,
    messages,
})
// setup i18n instance with glob
export const setupI18n = {
    install(app: App) {
        app.use(i18n)
    },
}
export default i18n
