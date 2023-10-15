declare module '@vue/runtime-dom' {
  interface HTMLAttributes {
    [key: string]: any
  }
}
declare module '@vue/runtime-core' {
  interface AllowedComponentProps {
    [key: string]: any
  }
}
declare module '*.vue' {
  import { DefineComponent } from 'vue'
  const component: DefineComponent<{}, {}, any>
  export default component
}
export {}
