import vue from '@vitejs/plugin-vue'
import vueJsx from '@vitejs/plugin-vue-jsx'
import eslint from 'vite-plugin-eslint'
import ViteCompression from 'vite-plugin-compression'
import svgLoader from 'vite-svg-loader'
import { createHtmlPlugin } from 'vite-plugin-html'
import AutoImport from 'unplugin-auto-import/vite'
import Components from 'unplugin-vue-components/vite'
import Icons from 'unplugin-icons/vite'
import IconsResolver from 'unplugin-icons/resolver'
import { FileSystemIconLoader } from 'unplugin-icons/loaders'
import {
  ElementPlusResolver,
  VueUseComponentsResolver,
  BootstrapVueNextResolver,
} from 'unplugin-vue-components/resolvers'
import {
  createStyleImportPlugin,
  ElementPlusResolve,
} from 'vite-plugin-style-import'
import UnoCSS from 'unocss/vite'
import Unfonts from 'unplugin-fonts/vite'
import VueI18nPlugin from '@intlify/unplugin-vue-i18n/vite'
import { ConfigEnv } from 'vite'
import { resolve } from 'path'

export default (env: ConfigEnv) => {
  return [
    vue({
      include: [/\.vue$/, /\.md$/],
    }),
    vueJsx(),
    eslint({
      cache: true,
      fix: true,
      exclude: [
        'node_modules',
        '.DS_Store',
        'dist',
        'dist-ssr',
        '*.local',
        'presets',
        '**/*.d.ts',
        '*.d.ts',
        '**/node_modules/**',
      ],
    }),
    svgLoader(),
    AutoImport({
      dts: './src/auto-imports.d.ts',
      imports: [
        'vue',
        'pinia',
        'vue-router',
        'vue-i18n',
        '@vueuse/core',
        {
          axios: [
            ['default', 'axios'], // import { default as axios } from 'axios',
          ],
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
        },
      ],
      // Generate corresponding .eslintrc-auto-import.json file.
      // eslint globals Docs - https://eslint.org/docs/user-guide/configuring/language-options#specifying-globals
      eslintrc: {
        enabled: true,
      },
      resolvers: [ElementPlusResolver()],
    }),
    createStyleImportPlugin({
      resolves: [ElementPlusResolve()],
      libs: [
        {
          libraryName: 'element-plus',
          esModule: true,
          resolveStyle: (name: string) => {
            return `element-plus/theme-chalk/${name}.css`
          },
        },
      ],
    }),
    ViteCompression({
      threshold: 1024000, // 对大于 1mb 的文件进行压缩
    }),
    Components({
      dts: './src/components.d.ts',
      extensions: ['vue', 'md'],
      include: [/\.vue$/, /\.vue\?vue/, /\.md$/],
      // imports 指定组件所在位置，默认为 src/components; 有需要也可以加上 view 目录
      dirs: ['src/components/', 'src/views/', 'src/pages'],
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
    createHtmlPlugin({
      minify: true,
    }),
    UnoCSS({}),
  ]
}
