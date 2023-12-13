/// <reference types="vite-svg-loader" />

import type { AttributifyAttributes } from '@unocss/preset-attributify'

// 声明自动引入的 vue 组件
declare module '*.vue' {
  import type { DefineComponent } from 'vue'

  const component: DefineComponent<object, object, any>
  export default component
}

// 声明 icons 引入的组件
declare module '~icons/*' {
  import type { FunctionalComponent, SVGAttributes } from 'vue'

  const component: FunctionalComponent<SVGAttributes>
  export default component
}

// UnoCSS Attributify
declare module '@vue/runtime-dom' {
  interface HTMLAttributes extends AttributifyAttributes {}
}

// 声明 md 文件
declare module '*.md' {
  import type { DefineComponent } from 'vue'

  const component: DefineComponent<object, object, any>
  export default component
}

declare interface Window {
  // extend the window
}
