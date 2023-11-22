import type {
  ComponentPublicInstance,
  FunctionalComponent,
  VNodeChild,
} from 'vue'

declare global {
  const __APP_INFO__: {
    pkg: {
      name: string
      version: string
      dependencies: Recordable<string>
      devDependencies: Recordable<string>
    }
    lastBuildTime: string
  }

  // vue
  //  type PropType<T> = VuePropType<T>;
  type VueNode = VNodeChild | JSX.Element

  type Nullable<T> = T | null
  type NullUndefable<T> = T | null | undefined
  type Recordable<T = any> = Record<string, T>

  interface ImportMetaEnv extends ViteEnv {
    __: unknown
  }

  interface ViteEnv {
    VITE_PORT: number
    VITE_USE_MOCK: boolean
    VITE_USE_PWA: boolean
    VITE_PUBLIC_PATH: string
    VITE_PROXY: [string, string][]
    VITE_GLOB_APP_TITLE: string
    VITE_GLOB_APP_SHORT_NAME: string
    VITE_USE_CDN: boolean
    VITE_DROP_CONSOLE: boolean
    VITE_BUILD_COMPRESS: 'gzip' | 'brotli' | 'none'
    VITE_BUILD_COMPRESS_DELETE_ORIGIN_FILE: boolean
    VITE_LEGACY: boolean
    VITE_USE_IMAGEMIN: boolean
    VITE_GENERATE_UI: string
  }

  function parseInt(s: string | number, radix?: number): number

  function parseFloat(string: string | number): number

  namespace JSX {
    // tslint:disable no-empty-interface
    // type Element = VNode
    // tslint:disable no-empty-interface
    // type ElementClass = ComponentRenderProxy
    interface ElementAttributesProperty {
      $props: {}
    }

    interface IntrinsicElements {
      [elem: string]: any
    }

    interface IntrinsicAttributes {
      [elem: string]: any
    }
  }
}

declare module 'vue' {
  export type JSXComponent<Props = any> =
    | { new (): ComponentPublicInstance<Props> }
    | FunctionalComponent<Props>
}
