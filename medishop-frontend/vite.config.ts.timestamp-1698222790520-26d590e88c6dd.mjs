// vite.config.ts
import { resolve as resolve2 } from "node:path";
import process from "node:process";
import { defineConfig, loadEnv } from "file:///C:/Users/kang.chen2/develop/MediShop/medishop-frontend/node_modules/.pnpm/vite@4.5.0_@types+node@17.0.45_sass@1.69.4/node_modules/vite/dist/node/index.js";

// presets/presets.ts
import vue from "file:///C:/Users/kang.chen2/develop/MediShop/medishop-frontend/node_modules/.pnpm/@vitejs+plugin-vue@4.4.0_vite@4.5.0_vue@3.3.5/node_modules/@vitejs/plugin-vue/dist/index.mjs";
import vueJsx from "file:///C:/Users/kang.chen2/develop/MediShop/medishop-frontend/node_modules/.pnpm/@vitejs+plugin-vue-jsx@3.0.1_vite@4.5.0_vue@3.3.5/node_modules/@vitejs/plugin-vue-jsx/dist/index.mjs";
import eslint from "file:///C:/Users/kang.chen2/develop/MediShop/medishop-frontend/node_modules/.pnpm/vite-plugin-eslint@1.8.1_eslint@8.51.0_vite@4.5.0/node_modules/vite-plugin-eslint/dist/index.mjs";
import ViteCompression from "file:///C:/Users/kang.chen2/develop/MediShop/medishop-frontend/node_modules/.pnpm/vite-plugin-compression@0.5.1_vite@4.5.0/node_modules/vite-plugin-compression/dist/index.mjs";
import svgLoader from "file:///C:/Users/kang.chen2/develop/MediShop/medishop-frontend/node_modules/.pnpm/vite-svg-loader@4.0.0/node_modules/vite-svg-loader/index.js";
import { createHtmlPlugin } from "file:///C:/Users/kang.chen2/develop/MediShop/medishop-frontend/node_modules/.pnpm/vite-plugin-html@3.2.0_vite@4.5.0/node_modules/vite-plugin-html/dist/index.mjs";
import AutoImport from "file:///C:/Users/kang.chen2/develop/MediShop/medishop-frontend/node_modules/.pnpm/unplugin-auto-import@0.16.6_@vueuse+core@10.5.0/node_modules/unplugin-auto-import/dist/vite.js";
import Components from "file:///C:/Users/kang.chen2/develop/MediShop/medishop-frontend/node_modules/.pnpm/unplugin-vue-components@0.25.2_vue@3.3.5/node_modules/unplugin-vue-components/dist/vite.mjs";
import Icons from "file:///C:/Users/kang.chen2/develop/MediShop/medishop-frontend/node_modules/.pnpm/unplugin-icons@0.16.6/node_modules/unplugin-icons/dist/vite.mjs";
import IconsResolver from "file:///C:/Users/kang.chen2/develop/MediShop/medishop-frontend/node_modules/.pnpm/unplugin-icons@0.16.6/node_modules/unplugin-icons/dist/resolver.mjs";
import { FileSystemIconLoader } from "file:///C:/Users/kang.chen2/develop/MediShop/medishop-frontend/node_modules/.pnpm/unplugin-icons@0.16.6/node_modules/unplugin-icons/dist/loaders.mjs";
import {
  ElementPlusResolver,
  VueUseComponentsResolver,
  BootstrapVueNextResolver
} from "file:///C:/Users/kang.chen2/develop/MediShop/medishop-frontend/node_modules/.pnpm/unplugin-vue-components@0.25.2_vue@3.3.5/node_modules/unplugin-vue-components/dist/resolvers.mjs";
import {
  createStyleImportPlugin,
  ElementPlusResolve
} from "file:///C:/Users/kang.chen2/develop/MediShop/medishop-frontend/node_modules/.pnpm/vite-plugin-style-import@2.0.0_vite@4.5.0/node_modules/vite-plugin-style-import/dist/index.mjs";
import UnoCSS from "file:///C:/Users/kang.chen2/develop/MediShop/medishop-frontend/node_modules/.pnpm/unocss@0.53.6_postcss@8.4.31_vite@4.5.0/node_modules/unocss/dist/vite.mjs";
import Unfonts from "file:///C:/Users/kang.chen2/develop/MediShop/medishop-frontend/node_modules/.pnpm/unplugin-fonts@1.0.3_vite@4.5.0/node_modules/unplugin-fonts/dist/vite.mjs";
import VueI18nPlugin from "file:///C:/Users/kang.chen2/develop/MediShop/medishop-frontend/node_modules/.pnpm/@intlify+unplugin-vue-i18n@0.11.0_vue-i18n@9.5.0/node_modules/@intlify/unplugin-vue-i18n/lib/vite.mjs";
import { resolve } from "path";
var __vite_injected_original_dirname = "C:\\Users\\kang.chen2\\develop\\MediShop\\medishop-frontend\\presets";
var presets_default = (env) => {
  return [
    vue({
      include: [/\.vue$/, /\.md$/]
    }),
    vueJsx(),
    eslint({
      cache: true,
      fix: true,
      exclude: [
        "node_modules",
        ".DS_Store",
        "dist",
        "dist-ssr",
        "*.local",
        "presets",
        "**/*.d.ts",
        "*.d.ts",
        "**/node_modules/**"
      ]
    }),
    svgLoader(),
    AutoImport({
      dts: "./src/auto-imports.d.ts",
      imports: [
        "vue",
        "pinia",
        "vue-router",
        "vue-i18n",
        "@vueuse/core",
        {
          axios: [
            ["default", "axios"]
            // import { default as axios } from 'axios',
          ],
          "echarts/core": [["*", "echarts"]],
          "echarts/charts": [
            "BarChart",
            "BoxplotChart",
            "ScatterChart",
            "PieChart"
          ],
          "echarts/renderers": ["CanvasRenderer", "SVGRenderer"],
          "echarts/features": ["LabelLayout", "UniversalTransition"],
          // 引入提示框，标题，直角坐标系，数据集，内置数据转换器等组件
          "echarts/components": [
            "DatasetComponent",
            "DataZoomComponent",
            "GridComponent",
            "LegendComponent",
            "MarkLineComponent",
            "TitleComponent",
            "ToolboxComponent",
            "TooltipComponent",
            "TransformComponent"
          ]
        }
      ],
      // Generate corresponding .eslintrc-auto-import.json file.
      // eslint globals Docs - https://eslint.org/docs/user-guide/configuring/language-options#specifying-globals
      eslintrc: {
        enabled: true
      },
      resolvers: [ElementPlusResolver()]
    }),
    createStyleImportPlugin({
      resolves: [ElementPlusResolve()],
      libs: [
        {
          libraryName: "element-plus",
          esModule: true,
          resolveStyle: (name) => {
            return `element-plus/theme-chalk/${name}.css`;
          }
        }
      ]
    }),
    ViteCompression({
      threshold: 1024e3
      // 对大于 1mb 的文件进行压缩
    }),
    Components({
      dts: "./src/components.d.ts",
      extensions: ["vue", "md"],
      include: [/\.vue$/, /\.vue\?vue/, /\.md$/],
      // imports 指定组件所在位置，默认为 src/components; 有需要也可以加上 view 目录
      dirs: ["src/components/", "src/views/", "src/pages"],
      resolvers: [
        ElementPlusResolver(),
        BootstrapVueNextResolver(),
        IconsResolver({
          // 标识自定义图标集
          customCollections: ["own"]
        }),
        VueUseComponentsResolver()
      ]
    }),
    Icons({
      compiler: "vue3",
      autoInstall: true,
      customCollections: {
        own: FileSystemIconLoader(
          "src/assets/icons",
          (svg) => svg.replace(/^<svg /, '<svg fill="currentColor" ')
        )
      }
    }),
    Unfonts({
      google: {
        families: ["Open Sans", "Montserrat", "Fira Sans"]
      }
    }),
    VueI18nPlugin({
      defaultSFCLang: "yaml",
      include: [resolve(__vite_injected_original_dirname, "../locales/**")]
    }),
    createHtmlPlugin({
      minify: true
    }),
    UnoCSS({})
  ];
};

// vite.config.ts
var __vite_injected_original_dirname2 = "C:\\Users\\kang.chen2\\develop\\MediShop\\medishop-frontend";
var vite_config_default = defineConfig((env) => {
  const viteEnv = loadEnv(env.mode, process.cwd());
  return {
    // envDir: resolve(__dirname),
    base: viteEnv.VITE_BASE || "./",
    plugins: [presets_default(env)],
    esbuild: {
      drop: ["console", "debugger"]
    },
    resolve: {
      alias: {
        "@": resolve2(__vite_injected_original_dirname2, "src"),
        // 把 @ 指向到 src 目录去
        "vue-i18n": "vue-i18n/dist/vue-i18n.cjs.js"
      }
    },
    server: {
      host: true,
      // host设置为true才可以使用network的形式，以ip访问项目
      port: 3e3,
      open: true,
      cors: true,
      strictPort: true,
      // 接口代理
      proxy: {
        "/api": {
          target: "http://localhost:8999",
          //     target: 'https://118.89.71.118/',
          changeOrigin: true,
          // 允许跨域
          rewrite: (path) => path.replace("/api/", "/")
        }
      },
      build: {
        // outDir: resolve(__dirname, `dist`), // 指定输出路径
        sourcemap: false,
        // 这个生产环境一定要关闭，不然打包的产物会很大
        assetsInlineLimit: 4096,
        // 小于此阈值的导入或引用资源将内联为 base64 编码，以避免额外的 http 请求
        emptyOutDir: true,
        // Vite 会在构建时清空该目录
        reportCompressedSize: false,
        // 消除打包大小超过500kb警告
        chunkSizeWarningLimit: 2e3,
        minify: "esbuild",
        assetsDir: "static/assets",
        // 静态资源打包到dist下的不同目录
        rollupOptions: {
          output: {
            compact: true,
            // 压缩代码，删除换行符等
            chunkFileNames: "static/js/[name]-[hash].js",
            entryFileNames: "static/js/[name]-[hash].js",
            assetFileNames: "static/[ext]/[name]-[hash].[ext]"
          }
        }
      }
      // css: {
      //     preprocessorOptions: {
      //         // 全局引入了 scss 的文件
      //         scss: {
      //             additionalData: `@import "@/assets/styles/variables.scss";`,
      //             javascriptEnabled: true,
      //         },
      //     },
      // },
    }
  };
});
export {
  vite_config_default as default
};
//# sourceMappingURL=data:application/json;base64,ewogICJ2ZXJzaW9uIjogMywKICAic291cmNlcyI6IFsidml0ZS5jb25maWcudHMiLCAicHJlc2V0cy9wcmVzZXRzLnRzIl0sCiAgInNvdXJjZXNDb250ZW50IjogWyJjb25zdCBfX3ZpdGVfaW5qZWN0ZWRfb3JpZ2luYWxfZGlybmFtZSA9IFwiQzpcXFxcVXNlcnNcXFxca2FuZy5jaGVuMlxcXFxkZXZlbG9wXFxcXE1lZGlTaG9wXFxcXG1lZGlzaG9wLWZyb250ZW5kXCI7Y29uc3QgX192aXRlX2luamVjdGVkX29yaWdpbmFsX2ZpbGVuYW1lID0gXCJDOlxcXFxVc2Vyc1xcXFxrYW5nLmNoZW4yXFxcXGRldmVsb3BcXFxcTWVkaVNob3BcXFxcbWVkaXNob3AtZnJvbnRlbmRcXFxcdml0ZS5jb25maWcudHNcIjtjb25zdCBfX3ZpdGVfaW5qZWN0ZWRfb3JpZ2luYWxfaW1wb3J0X21ldGFfdXJsID0gXCJmaWxlOi8vL0M6L1VzZXJzL2thbmcuY2hlbjIvZGV2ZWxvcC9NZWRpU2hvcC9tZWRpc2hvcC1mcm9udGVuZC92aXRlLmNvbmZpZy50c1wiO2ltcG9ydCB7IHJlc29sdmUgfSBmcm9tICdub2RlOnBhdGgnXG5cbmltcG9ydCBwcm9jZXNzIGZyb20gJ25vZGU6cHJvY2VzcydcblxuaW1wb3J0IHsgZGVmaW5lQ29uZmlnLCBsb2FkRW52IH0gZnJvbSAndml0ZSdcblxuaW1wb3J0IHByZXNldHMgZnJvbSAnLi9wcmVzZXRzL3ByZXNldHMnXG5cbmV4cG9ydCBkZWZhdWx0IGRlZmluZUNvbmZpZygoZW52KSA9PiB7XG4gIC8vIGVudiBcdTczQUZcdTU4ODNcdTUzRDhcdTkxQ0ZcbiAgY29uc3Qgdml0ZUVudiA9IGxvYWRFbnYoZW52Lm1vZGUsIHByb2Nlc3MuY3dkKCkpXG4gIHJldHVybiB7XG4gICAgLy8gZW52RGlyOiByZXNvbHZlKF9fZGlybmFtZSksXG4gICAgYmFzZTogdml0ZUVudi5WSVRFX0JBU0UgfHwgJy4vJyxcbiAgICBwbHVnaW5zOiBbcHJlc2V0cyhlbnYpXSxcbiAgICBlc2J1aWxkOiB7XG4gICAgICBkcm9wOiBbJ2NvbnNvbGUnLCAnZGVidWdnZXInXSxcbiAgICB9LFxuICAgIHJlc29sdmU6IHtcbiAgICAgIGFsaWFzOiB7XG4gICAgICAgICdAJzogcmVzb2x2ZShfX2Rpcm5hbWUsICdzcmMnKSwgLy8gXHU2MjhBIEAgXHU2MzA3XHU1NDExXHU1MjMwIHNyYyBcdTc2RUVcdTVGNTVcdTUzQkJcbiAgICAgICAgJ3Z1ZS1pMThuJzogJ3Z1ZS1pMThuL2Rpc3QvdnVlLWkxOG4uY2pzLmpzJyxcbiAgICAgIH0sXG4gICAgfSxcbiAgICBzZXJ2ZXI6IHtcbiAgICAgIGhvc3Q6IHRydWUsIC8vIGhvc3RcdThCQkVcdTdGNkVcdTRFM0F0cnVlXHU2MjREXHU1M0VGXHU0RUU1XHU0RjdGXHU3NTI4bmV0d29ya1x1NzY4NFx1NUY2Mlx1NUYwRlx1RkYwQ1x1NEVFNWlwXHU4QkJGXHU5NUVFXHU5ODc5XHU3NkVFXG4gICAgICBwb3J0OiAzMDAwLFxuICAgICAgb3BlbjogdHJ1ZSxcbiAgICAgIGNvcnM6IHRydWUsXG4gICAgICBzdHJpY3RQb3J0OiB0cnVlLFxuICAgICAgLy8gXHU2M0E1XHU1M0UzXHU0RUUzXHU3NDA2XG4gICAgICBwcm94eToge1xuICAgICAgICAnL2FwaSc6IHtcbiAgICAgICAgICB0YXJnZXQ6ICdodHRwOi8vbG9jYWxob3N0Ojg5OTknLFxuICAgICAgICAgIC8vICAgICB0YXJnZXQ6ICdodHRwczovLzExOC44OS43MS4xMTgvJyxcbiAgICAgICAgICBjaGFuZ2VPcmlnaW46IHRydWUsIC8vIFx1NTE0MVx1OEJCOFx1OERFOFx1NTdERlxuICAgICAgICAgIHJld3JpdGU6IChwYXRoKSA9PiBwYXRoLnJlcGxhY2UoJy9hcGkvJywgJy8nKSxcbiAgICAgICAgfSxcbiAgICAgIH0sXG4gICAgICBidWlsZDoge1xuICAgICAgICAvLyBvdXREaXI6IHJlc29sdmUoX19kaXJuYW1lLCBgZGlzdGApLCAvLyBcdTYzMDdcdTVCOUFcdThGOTNcdTUxRkFcdThERUZcdTVGODRcbiAgICAgICAgc291cmNlbWFwOiBmYWxzZSwgLy8gXHU4RkQ5XHU0RTJBXHU3NTFGXHU0RUE3XHU3M0FGXHU1ODgzXHU0RTAwXHU1QjlBXHU4OTgxXHU1MTczXHU5NUVEXHVGRjBDXHU0RTBEXHU3MTM2XHU2MjUzXHU1MzA1XHU3Njg0XHU0RUE3XHU3MjY5XHU0RjFBXHU1Rjg4XHU1OTI3XG4gICAgICAgIGFzc2V0c0lubGluZUxpbWl0OiA0MDk2LCAvLyBcdTVDMEZcdTRFOEVcdTZCNjRcdTk2MDhcdTUwM0NcdTc2ODRcdTVCRkNcdTUxNjVcdTYyMTZcdTVGMTVcdTc1MjhcdThENDRcdTZFOTBcdTVDMDZcdTUxODVcdTgwNTRcdTRFM0EgYmFzZTY0IFx1N0YxNlx1NzgwMVx1RkYwQ1x1NEVFNVx1OTA3Rlx1NTE0RFx1OTg5RFx1NTkxNlx1NzY4NCBodHRwIFx1OEJGN1x1NkM0MlxuICAgICAgICBlbXB0eU91dERpcjogdHJ1ZSwgLy8gVml0ZSBcdTRGMUFcdTU3MjhcdTY3ODRcdTVFRkFcdTY1RjZcdTZFMDVcdTdBN0FcdThCRTVcdTc2RUVcdTVGNTVcbiAgICAgICAgcmVwb3J0Q29tcHJlc3NlZFNpemU6IGZhbHNlLFxuXG4gICAgICAgIC8vIFx1NkQ4OFx1OTY2NFx1NjI1M1x1NTMwNVx1NTkyN1x1NUMwRlx1OEQ4NVx1OEZDNzUwMGtiXHU4QjY2XHU1NDRBXG4gICAgICAgIGNodW5rU2l6ZVdhcm5pbmdMaW1pdDogMjAwMCxcbiAgICAgICAgbWluaWZ5OiAnZXNidWlsZCcsXG4gICAgICAgIGFzc2V0c0RpcjogJ3N0YXRpYy9hc3NldHMnLFxuICAgICAgICAvLyBcdTk3NTlcdTYwMDFcdThENDRcdTZFOTBcdTYyNTNcdTUzMDVcdTUyMzBkaXN0XHU0RTBCXHU3Njg0XHU0RTBEXHU1NDBDXHU3NkVFXHU1RjU1XG4gICAgICAgIHJvbGx1cE9wdGlvbnM6IHtcbiAgICAgICAgICBvdXRwdXQ6IHtcbiAgICAgICAgICAgIGNvbXBhY3Q6IHRydWUsIC8vIFx1NTM4Qlx1N0YyOVx1NEVFM1x1NzgwMVx1RkYwQ1x1NTIyMFx1OTY2NFx1NjM2Mlx1ODg0Q1x1N0IyNlx1N0I0OVxuICAgICAgICAgICAgY2h1bmtGaWxlTmFtZXM6ICdzdGF0aWMvanMvW25hbWVdLVtoYXNoXS5qcycsXG4gICAgICAgICAgICBlbnRyeUZpbGVOYW1lczogJ3N0YXRpYy9qcy9bbmFtZV0tW2hhc2hdLmpzJyxcbiAgICAgICAgICAgIGFzc2V0RmlsZU5hbWVzOiAnc3RhdGljL1tleHRdL1tuYW1lXS1baGFzaF0uW2V4dF0nLFxuICAgICAgICAgIH0sXG4gICAgICAgIH0sXG4gICAgICB9LFxuICAgICAgLy8gY3NzOiB7XG4gICAgICAvLyAgICAgcHJlcHJvY2Vzc29yT3B0aW9uczoge1xuICAgICAgLy8gICAgICAgICAvLyBcdTUxNjhcdTVDNDBcdTVGMTVcdTUxNjVcdTRFODYgc2NzcyBcdTc2ODRcdTY1ODdcdTRFRjZcbiAgICAgIC8vICAgICAgICAgc2Nzczoge1xuICAgICAgLy8gICAgICAgICAgICAgYWRkaXRpb25hbERhdGE6IGBAaW1wb3J0IFwiQC9hc3NldHMvc3R5bGVzL3ZhcmlhYmxlcy5zY3NzXCI7YCxcbiAgICAgIC8vICAgICAgICAgICAgIGphdmFzY3JpcHRFbmFibGVkOiB0cnVlLFxuICAgICAgLy8gICAgICAgICB9LFxuICAgICAgLy8gICAgIH0sXG4gICAgICAvLyB9LFxuICAgIH0sXG4gIH1cbn0pXG4iLCAiY29uc3QgX192aXRlX2luamVjdGVkX29yaWdpbmFsX2Rpcm5hbWUgPSBcIkM6XFxcXFVzZXJzXFxcXGthbmcuY2hlbjJcXFxcZGV2ZWxvcFxcXFxNZWRpU2hvcFxcXFxtZWRpc2hvcC1mcm9udGVuZFxcXFxwcmVzZXRzXCI7Y29uc3QgX192aXRlX2luamVjdGVkX29yaWdpbmFsX2ZpbGVuYW1lID0gXCJDOlxcXFxVc2Vyc1xcXFxrYW5nLmNoZW4yXFxcXGRldmVsb3BcXFxcTWVkaVNob3BcXFxcbWVkaXNob3AtZnJvbnRlbmRcXFxccHJlc2V0c1xcXFxwcmVzZXRzLnRzXCI7Y29uc3QgX192aXRlX2luamVjdGVkX29yaWdpbmFsX2ltcG9ydF9tZXRhX3VybCA9IFwiZmlsZTovLy9DOi9Vc2Vycy9rYW5nLmNoZW4yL2RldmVsb3AvTWVkaVNob3AvbWVkaXNob3AtZnJvbnRlbmQvcHJlc2V0cy9wcmVzZXRzLnRzXCI7aW1wb3J0IHZ1ZSBmcm9tICdAdml0ZWpzL3BsdWdpbi12dWUnXG5pbXBvcnQgdnVlSnN4IGZyb20gJ0B2aXRlanMvcGx1Z2luLXZ1ZS1qc3gnXG5pbXBvcnQgZXNsaW50IGZyb20gJ3ZpdGUtcGx1Z2luLWVzbGludCdcbmltcG9ydCBWaXRlQ29tcHJlc3Npb24gZnJvbSAndml0ZS1wbHVnaW4tY29tcHJlc3Npb24nXG5pbXBvcnQgc3ZnTG9hZGVyIGZyb20gJ3ZpdGUtc3ZnLWxvYWRlcidcbmltcG9ydCB7IGNyZWF0ZUh0bWxQbHVnaW4gfSBmcm9tICd2aXRlLXBsdWdpbi1odG1sJ1xuaW1wb3J0IEF1dG9JbXBvcnQgZnJvbSAndW5wbHVnaW4tYXV0by1pbXBvcnQvdml0ZSdcbmltcG9ydCBDb21wb25lbnRzIGZyb20gJ3VucGx1Z2luLXZ1ZS1jb21wb25lbnRzL3ZpdGUnXG5pbXBvcnQgSWNvbnMgZnJvbSAndW5wbHVnaW4taWNvbnMvdml0ZSdcbmltcG9ydCBJY29uc1Jlc29sdmVyIGZyb20gJ3VucGx1Z2luLWljb25zL3Jlc29sdmVyJ1xuaW1wb3J0IHsgRmlsZVN5c3RlbUljb25Mb2FkZXIgfSBmcm9tICd1bnBsdWdpbi1pY29ucy9sb2FkZXJzJ1xuaW1wb3J0IHtcbiAgRWxlbWVudFBsdXNSZXNvbHZlcixcbiAgVnVlVXNlQ29tcG9uZW50c1Jlc29sdmVyLFxuICBCb290c3RyYXBWdWVOZXh0UmVzb2x2ZXIsXG59IGZyb20gJ3VucGx1Z2luLXZ1ZS1jb21wb25lbnRzL3Jlc29sdmVycydcbmltcG9ydCB7XG4gIGNyZWF0ZVN0eWxlSW1wb3J0UGx1Z2luLFxuICBFbGVtZW50UGx1c1Jlc29sdmUsXG59IGZyb20gJ3ZpdGUtcGx1Z2luLXN0eWxlLWltcG9ydCdcbmltcG9ydCBVbm9DU1MgZnJvbSAndW5vY3NzL3ZpdGUnXG5pbXBvcnQgVW5mb250cyBmcm9tICd1bnBsdWdpbi1mb250cy92aXRlJ1xuaW1wb3J0IFZ1ZUkxOG5QbHVnaW4gZnJvbSAnQGludGxpZnkvdW5wbHVnaW4tdnVlLWkxOG4vdml0ZSdcbmltcG9ydCB7IENvbmZpZ0VudiB9IGZyb20gJ3ZpdGUnXG5pbXBvcnQgeyByZXNvbHZlIH0gZnJvbSAncGF0aCdcblxuZXhwb3J0IGRlZmF1bHQgKGVudjogQ29uZmlnRW52KSA9PiB7XG4gIHJldHVybiBbXG4gICAgdnVlKHtcbiAgICAgIGluY2x1ZGU6IFsvXFwudnVlJC8sIC9cXC5tZCQvXSxcbiAgICB9KSxcbiAgICB2dWVKc3goKSxcbiAgICBlc2xpbnQoe1xuICAgICAgY2FjaGU6IHRydWUsXG4gICAgICBmaXg6IHRydWUsXG4gICAgICBleGNsdWRlOiBbXG4gICAgICAgICdub2RlX21vZHVsZXMnLFxuICAgICAgICAnLkRTX1N0b3JlJyxcbiAgICAgICAgJ2Rpc3QnLFxuICAgICAgICAnZGlzdC1zc3InLFxuICAgICAgICAnKi5sb2NhbCcsXG4gICAgICAgICdwcmVzZXRzJyxcbiAgICAgICAgJyoqLyouZC50cycsXG4gICAgICAgICcqLmQudHMnLFxuICAgICAgICAnKiovbm9kZV9tb2R1bGVzLyoqJyxcbiAgICAgIF0sXG4gICAgfSksXG4gICAgc3ZnTG9hZGVyKCksXG4gICAgQXV0b0ltcG9ydCh7XG4gICAgICBkdHM6ICcuL3NyYy9hdXRvLWltcG9ydHMuZC50cycsXG4gICAgICBpbXBvcnRzOiBbXG4gICAgICAgICd2dWUnLFxuICAgICAgICAncGluaWEnLFxuICAgICAgICAndnVlLXJvdXRlcicsXG4gICAgICAgICd2dWUtaTE4bicsXG4gICAgICAgICdAdnVldXNlL2NvcmUnLFxuICAgICAgICB7XG4gICAgICAgICAgYXhpb3M6IFtcbiAgICAgICAgICAgIFsnZGVmYXVsdCcsICdheGlvcyddLCAvLyBpbXBvcnQgeyBkZWZhdWx0IGFzIGF4aW9zIH0gZnJvbSAnYXhpb3MnLFxuICAgICAgICAgIF0sXG4gICAgICAgICAgJ2VjaGFydHMvY29yZSc6IFtbJyonLCAnZWNoYXJ0cyddXSxcbiAgICAgICAgICAnZWNoYXJ0cy9jaGFydHMnOiBbXG4gICAgICAgICAgICAnQmFyQ2hhcnQnLFxuICAgICAgICAgICAgJ0JveHBsb3RDaGFydCcsXG4gICAgICAgICAgICAnU2NhdHRlckNoYXJ0JyxcbiAgICAgICAgICAgICdQaWVDaGFydCcsXG4gICAgICAgICAgXSxcbiAgICAgICAgICAnZWNoYXJ0cy9yZW5kZXJlcnMnOiBbJ0NhbnZhc1JlbmRlcmVyJywgJ1NWR1JlbmRlcmVyJ10sXG4gICAgICAgICAgJ2VjaGFydHMvZmVhdHVyZXMnOiBbJ0xhYmVsTGF5b3V0JywgJ1VuaXZlcnNhbFRyYW5zaXRpb24nXSxcbiAgICAgICAgICAvLyBcdTVGMTVcdTUxNjVcdTYzRDBcdTc5M0FcdTY4NDZcdUZGMENcdTY4MDdcdTk4OThcdUZGMENcdTc2RjRcdTg5RDJcdTU3NTBcdTY4MDdcdTdDRkJcdUZGMENcdTY1NzBcdTYzNkVcdTk2QzZcdUZGMENcdTUxODVcdTdGNkVcdTY1NzBcdTYzNkVcdThGNkNcdTYzNjJcdTU2NjhcdTdCNDlcdTdFQzRcdTRFRjZcbiAgICAgICAgICAnZWNoYXJ0cy9jb21wb25lbnRzJzogW1xuICAgICAgICAgICAgJ0RhdGFzZXRDb21wb25lbnQnLFxuICAgICAgICAgICAgJ0RhdGFab29tQ29tcG9uZW50JyxcbiAgICAgICAgICAgICdHcmlkQ29tcG9uZW50JyxcbiAgICAgICAgICAgICdMZWdlbmRDb21wb25lbnQnLFxuICAgICAgICAgICAgJ01hcmtMaW5lQ29tcG9uZW50JyxcbiAgICAgICAgICAgICdUaXRsZUNvbXBvbmVudCcsXG4gICAgICAgICAgICAnVG9vbGJveENvbXBvbmVudCcsXG4gICAgICAgICAgICAnVG9vbHRpcENvbXBvbmVudCcsXG4gICAgICAgICAgICAnVHJhbnNmb3JtQ29tcG9uZW50JyxcbiAgICAgICAgICBdLFxuICAgICAgICB9LFxuICAgICAgXSxcbiAgICAgIC8vIEdlbmVyYXRlIGNvcnJlc3BvbmRpbmcgLmVzbGludHJjLWF1dG8taW1wb3J0Lmpzb24gZmlsZS5cbiAgICAgIC8vIGVzbGludCBnbG9iYWxzIERvY3MgLSBodHRwczovL2VzbGludC5vcmcvZG9jcy91c2VyLWd1aWRlL2NvbmZpZ3VyaW5nL2xhbmd1YWdlLW9wdGlvbnMjc3BlY2lmeWluZy1nbG9iYWxzXG4gICAgICBlc2xpbnRyYzoge1xuICAgICAgICBlbmFibGVkOiB0cnVlLFxuICAgICAgfSxcbiAgICAgIHJlc29sdmVyczogW0VsZW1lbnRQbHVzUmVzb2x2ZXIoKV0sXG4gICAgfSksXG4gICAgY3JlYXRlU3R5bGVJbXBvcnRQbHVnaW4oe1xuICAgICAgcmVzb2x2ZXM6IFtFbGVtZW50UGx1c1Jlc29sdmUoKV0sXG4gICAgICBsaWJzOiBbXG4gICAgICAgIHtcbiAgICAgICAgICBsaWJyYXJ5TmFtZTogJ2VsZW1lbnQtcGx1cycsXG4gICAgICAgICAgZXNNb2R1bGU6IHRydWUsXG4gICAgICAgICAgcmVzb2x2ZVN0eWxlOiAobmFtZTogc3RyaW5nKSA9PiB7XG4gICAgICAgICAgICByZXR1cm4gYGVsZW1lbnQtcGx1cy90aGVtZS1jaGFsay8ke25hbWV9LmNzc2BcbiAgICAgICAgICB9LFxuICAgICAgICB9LFxuICAgICAgXSxcbiAgICB9KSxcbiAgICBWaXRlQ29tcHJlc3Npb24oe1xuICAgICAgdGhyZXNob2xkOiAxMDI0MDAwLCAvLyBcdTVCRjlcdTU5MjdcdTRFOEUgMW1iIFx1NzY4NFx1NjU4N1x1NEVGNlx1OEZEQlx1ODg0Q1x1NTM4Qlx1N0YyOVxuICAgIH0pLFxuICAgIENvbXBvbmVudHMoe1xuICAgICAgZHRzOiAnLi9zcmMvY29tcG9uZW50cy5kLnRzJyxcbiAgICAgIGV4dGVuc2lvbnM6IFsndnVlJywgJ21kJ10sXG4gICAgICBpbmNsdWRlOiBbL1xcLnZ1ZSQvLCAvXFwudnVlXFw/dnVlLywgL1xcLm1kJC9dLFxuICAgICAgLy8gaW1wb3J0cyBcdTYzMDdcdTVCOUFcdTdFQzRcdTRFRjZcdTYyNDBcdTU3MjhcdTRGNERcdTdGNkVcdUZGMENcdTlFRDhcdThCQTRcdTRFM0Egc3JjL2NvbXBvbmVudHM7IFx1NjcwOVx1OTcwMFx1ODk4MVx1NEU1Rlx1NTNFRlx1NEVFNVx1NTJBMFx1NEUwQSB2aWV3IFx1NzZFRVx1NUY1NVxuICAgICAgZGlyczogWydzcmMvY29tcG9uZW50cy8nLCAnc3JjL3ZpZXdzLycsICdzcmMvcGFnZXMnXSxcbiAgICAgIHJlc29sdmVyczogW1xuICAgICAgICBFbGVtZW50UGx1c1Jlc29sdmVyKCksXG4gICAgICAgIEJvb3RzdHJhcFZ1ZU5leHRSZXNvbHZlcigpLFxuICAgICAgICBJY29uc1Jlc29sdmVyKHtcbiAgICAgICAgICAvLyBcdTY4MDdcdThCQzZcdTgxRUFcdTVCOUFcdTRFNDlcdTU2RkVcdTY4MDdcdTk2QzZcbiAgICAgICAgICBjdXN0b21Db2xsZWN0aW9uczogWydvd24nXSxcbiAgICAgICAgfSksXG4gICAgICAgIFZ1ZVVzZUNvbXBvbmVudHNSZXNvbHZlcigpLFxuICAgICAgXSxcbiAgICB9KSxcbiAgICBJY29ucyh7XG4gICAgICBjb21waWxlcjogJ3Z1ZTMnLFxuICAgICAgYXV0b0luc3RhbGw6IHRydWUsXG4gICAgICBjdXN0b21Db2xsZWN0aW9uczoge1xuICAgICAgICBvd246IEZpbGVTeXN0ZW1JY29uTG9hZGVyKCdzcmMvYXNzZXRzL2ljb25zJywgKHN2ZykgPT5cbiAgICAgICAgICBzdmcucmVwbGFjZSgvXjxzdmcgLywgJzxzdmcgZmlsbD1cImN1cnJlbnRDb2xvclwiICcpXG4gICAgICAgICksXG4gICAgICB9LFxuICAgIH0pLFxuICAgIFVuZm9udHMoe1xuICAgICAgZ29vZ2xlOiB7XG4gICAgICAgIGZhbWlsaWVzOiBbJ09wZW4gU2FucycsICdNb250c2VycmF0JywgJ0ZpcmEgU2FucyddLFxuICAgICAgfSxcbiAgICB9KSxcbiAgICBWdWVJMThuUGx1Z2luKHtcbiAgICAgIGRlZmF1bHRTRkNMYW5nOiAneWFtbCcsXG4gICAgICBpbmNsdWRlOiBbcmVzb2x2ZShfX2Rpcm5hbWUsICcuLi9sb2NhbGVzLyoqJyldLFxuICAgIH0pLFxuICAgIGNyZWF0ZUh0bWxQbHVnaW4oe1xuICAgICAgbWluaWZ5OiB0cnVlLFxuICAgIH0pLFxuICAgIFVub0NTUyh7fSksXG4gIF1cbn1cbiJdLAogICJtYXBwaW5ncyI6ICI7QUFBZ1csU0FBUyxXQUFBQSxnQkFBZTtBQUV4WCxPQUFPLGFBQWE7QUFFcEIsU0FBUyxjQUFjLGVBQWU7OztBQ0o0VSxPQUFPLFNBQVM7QUFDbFksT0FBTyxZQUFZO0FBQ25CLE9BQU8sWUFBWTtBQUNuQixPQUFPLHFCQUFxQjtBQUM1QixPQUFPLGVBQWU7QUFDdEIsU0FBUyx3QkFBd0I7QUFDakMsT0FBTyxnQkFBZ0I7QUFDdkIsT0FBTyxnQkFBZ0I7QUFDdkIsT0FBTyxXQUFXO0FBQ2xCLE9BQU8sbUJBQW1CO0FBQzFCLFNBQVMsNEJBQTRCO0FBQ3JDO0FBQUEsRUFDRTtBQUFBLEVBQ0E7QUFBQSxFQUNBO0FBQUEsT0FDSztBQUNQO0FBQUEsRUFDRTtBQUFBLEVBQ0E7QUFBQSxPQUNLO0FBQ1AsT0FBTyxZQUFZO0FBQ25CLE9BQU8sYUFBYTtBQUNwQixPQUFPLG1CQUFtQjtBQUUxQixTQUFTLGVBQWU7QUF4QnhCLElBQU0sbUNBQW1DO0FBMEJ6QyxJQUFPLGtCQUFRLENBQUMsUUFBbUI7QUFDakMsU0FBTztBQUFBLElBQ0wsSUFBSTtBQUFBLE1BQ0YsU0FBUyxDQUFDLFVBQVUsT0FBTztBQUFBLElBQzdCLENBQUM7QUFBQSxJQUNELE9BQU87QUFBQSxJQUNQLE9BQU87QUFBQSxNQUNMLE9BQU87QUFBQSxNQUNQLEtBQUs7QUFBQSxNQUNMLFNBQVM7QUFBQSxRQUNQO0FBQUEsUUFDQTtBQUFBLFFBQ0E7QUFBQSxRQUNBO0FBQUEsUUFDQTtBQUFBLFFBQ0E7QUFBQSxRQUNBO0FBQUEsUUFDQTtBQUFBLFFBQ0E7QUFBQSxNQUNGO0FBQUEsSUFDRixDQUFDO0FBQUEsSUFD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O0FBQUEsSUFDOUI7QUFBQSxJQUNBLFNBQVM7QUFBQSxNQUNQLE9BQU87QUFBQSxRQUNMLEtBQUtDLFNBQVFDLG1DQUFXLEtBQUs7QUFBQTtBQUFBLFFBQzdCLFlBQVk7QUFBQSxNQUNkO0FBQUEsSUFDRjtBQUFBLElBQ0EsUUFBUTtBQUFBLE1BQ04sTUFBTTtBQUFBO0FBQUEsTUFDTixNQUFNO0FBQUEsTUFDTixNQUFNO0FBQUEsTUFDTixNQUFNO0FBQUEsTUFDTixZQUFZO0FBQUE7QUFBQSxNQUVaLE9BQU87QUFBQSxRQUNMLFFBQVE7QUFBQSxVQUNOLFFBQVE7QUFBQTtBQUFBLFVBRVIsY0FBYztBQUFBO0FBQUEsVUFDZCxTQUFTLENBQUMsU0FBUyxLQUFLLFFBQVEsU0FBUyxHQUFHO0FBQUEsUUFDOUM7QUFBQSxNQUNGO0FBQUEsTUFDQSxPQUFPO0FBQUE7QUFBQSxRQUVMLFdBQVc7QUFBQTtBQUFBLFFBQ1gsbUJBQW1CO0FBQUE7QUFBQSxRQUNuQixhQUFhO0FBQUE7QUFBQSxRQUNiLHNCQUFzQjtBQUFBO0FBQUEsUUFHdEIsdUJBQXVCO0FBQUEsUUFDdkIsUUFBUTtBQUFBLFFBQ1IsV0FBVztBQUFBO0FBQUEsUUFFWCxlQUFlO0FBQUEsVUFDYixRQUFRO0FBQUEsWUFDTixTQUFTO0FBQUE7QUFBQSxZQUNULGdCQUFnQjtBQUFBLFlBQ2hCLGdCQUFnQjtBQUFBLFlBQ2hCLGdCQUFnQjtBQUFBLFVBQ2xCO0FBQUEsUUFDRjtBQUFBLE1BQ0Y7QUFBQTtBQUFBO0FBQUE7QUFBQTtBQUFBO0FBQUE7QUFBQTtBQUFBO0FBQUE7QUFBQSxJQVVGO0FBQUEsRUFDRjtBQUNGLENBQUM7IiwKICAibmFtZXMiOiBbInJlc29sdmUiLCAiX192aXRlX2luamVjdGVkX29yaWdpbmFsX2Rpcm5hbWUiLCAicmVzb2x2ZSIsICJfX3ZpdGVfaW5qZWN0ZWRfb3JpZ2luYWxfZGlybmFtZSJdCn0K
