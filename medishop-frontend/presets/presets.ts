import { resolve } from 'node:path'

import VueDevTools from 'vite-plugin-vue-devtools'
import Vue from '@vitejs/plugin-vue'
import VueRouter from 'unplugin-vue-router/vite'
import { VueRouterAutoImports } from 'unplugin-vue-router'
import vueJsx from '@vitejs/plugin-vue-jsx'
import svgLoader from 'vite-svg-loader'
import AutoImport from 'unplugin-auto-import/vite'
import Components from 'unplugin-vue-components/vite'
import vitePluginImp from 'vite-plugin-imp'
import Icons from 'unplugin-icons/vite'
import IconsResolver from 'unplugin-icons/resolver'
import { FileSystemIconLoader } from 'unplugin-icons/loaders'
import {
  BootstrapVueNextResolver,
  ElementPlusResolver,
  VueUseComponentsResolver,
} from 'unplugin-vue-components/resolvers'
import UnoCSS from 'unocss/vite'
import VueI18nPlugin from '@intlify/unplugin-vue-i18n/vite'
import { createHtmlPlugin } from 'vite-plugin-html'

export default (env: Record<string, string>) => {
  return [
    VueRouter({
      extensions: ['.vue', '.md'],
    }),
    VueDevTools(),
    Vue({
      include: [/\.vue$/, /\.md$/],
    }),
    vueJsx(),
    svgLoader(),
    AutoImport({
      dts: true,
      imports: [
        'vue',
        'vue-i18n',
        'pinia',
        '@vueuse/core',
        VueRouterAutoImports,
        {
          axios: [
            ['default', 'axios'], // import { default as axios } from 'axios',
          ],
          /*
          'echarts/core': [['*', 'echarts']],
          'echarts/charts': [
            'BarChart',
            'BoxplotChart',
            'ScatterChart',
            'PieChart',
          ],
          'echarts/renderers': ['CanvasRenderer', 'SVGRenderer'],
          'echarts/features': ['LabelLayout', 'UniversalTransition'],
          // 引入提示框，标题，直角坐标系，数据集，内置数据转换器等组件
          'echarts/components': [
            'DatasetComponent',
            'DataZoomComponent',
            'GridComponent',
            'LegendComponent',
            'MarkLineComponent',
            'TitleComponent',
            'ToolboxComponent',
            'TooltipComponent',
            'TransformComponent',
          ],
          */
        },
      ],
      eslintrc: {
        enabled: true,
      },
      vueTemplate: true,
      resolvers: [ElementPlusResolver()],
    }),
    // ViteCompression({
    //   // 对大于 1mb 的文件进行压缩
    //   threshold: 1024000,
    // }),
    Components({
      dts: true,
      extensions: ['vue', 'md'],
      include: [/\.vue$/, /\.vue\?vue/, /\.md$/],
      resolvers: [
        ElementPlusResolver(),
        BootstrapVueNextResolver(),
        IconsResolver({
          // 标识自定义图标集
          customCollections: ['own'],
        }),
        VueUseComponentsResolver(),
      ],
    }),
    vitePluginImp({
      libList: [
        {
          libName: 'element-plus',
          libDirectory: 'es/components',
          nameFormatter: (name) => {
            return name.replace('el-', '')
          },
          style: (name) => {
            if (['el-config-provider', 'effect'].includes(name)) {
              return false
            }
            return `element-plus/es/components/${name.replace(
              'el-',
              ''
            )}/style/css.mjs`
          },
        },
      ],
    }),
    Icons({
      compiler: 'vue3',
      autoInstall: true,
      customCollections: {
        own: FileSystemIconLoader('src/assets/icons', (svg) =>
          svg.replace(/^<svg /, '<svg fill="currentColor" ')
        ),
      },
    }),
    VueI18nPlugin({
      defaultSFCLang: 'yaml',
      include: [resolve(__dirname, '../locales/**')],
    }),
    UnoCSS({}),
    createHtmlPlugin({
      minify: true,
      inject: {
        data: {
          title: env.VITE_APP_TITLE,
          reCaptchaScript: `<script src='https://www.google.com/recaptcha/api.js?render=${env.VITE_LOCALHOST_RECAPTCHA_SITE_KEY}'></script>`,
        },
      },
    }),
    // webfontDownload([
    //   // 'https://fonts.googleapis.com/css2?family=Press+Start+2P&display=swap',
    //   // 'https://fonts.googleapis.com/css2?family=Fira+Code&display=swap',
    // ]),
  ]
}
