import vue from '@vitejs/plugin-vue'
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
import Unfonts from 'unplugin-fonts/vite'
import VueI18nPlugin from '@intlify/unplugin-vue-i18n/vite'
import { resolve } from 'path'
import { createHtmlPlugin } from 'vite-plugin-html'

export default (env: Record<string, string>) => {
  return [
    VueRouter({
      dts: true,
      routesFolder: 'src/pages',
      extensions: ['.vue'],
      exclude: [],
      routeBlockLang: 'json5',
      importMode: 'async',
    }),
    vue({
      include: [/\.vue$/, /\.md$/],
    }),
    vueJsx(),
    svgLoader(),
    AutoImport({
      dts: true,
      imports: [
        'vue',
        'pinia',
        VueRouterAutoImports,
        'vue-i18n',
        '@vueuse/core',
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
      // Generate corresponding .eslintrc-auto-import.json file.
      // eslint globals Docs - https://eslint.org/docs/user-guide/configuring/language-options#specifying-globals
      eslintrc: {
        enabled: true,
      },
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
      // imports 指定组件所在位置，默认为 src/components; 有需要也可以加上 view 目录
      dirs: [
        'src/components/',
        'src/views/',
        // 'src/pages'  基于filename的路由，不需要动态导入
      ],
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
            if (['el-config-provider', 'effect'].includes(name)) return false
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
    Unfonts({
      google: {
        families: ['Open Sans', 'Montserrat', 'Fira Sans'],
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
  ]
}
