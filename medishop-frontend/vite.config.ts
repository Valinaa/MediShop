import { resolve } from 'node:path'

import process from 'node:process'

import { defineConfig, loadEnv } from 'vite'

import presets from './presets/presets'

export default defineConfig((env) => {
  // env 环境变量
  const viteEnv = loadEnv(env.mode, process.cwd())
  return {
    // envDir: resolve(__dirname),
    base: viteEnv.VITE_BASE || './',
    plugins: [presets(env)],
    esbuild: {
      drop: ['console', 'debugger'],
    },
    resolve: {
      alias: {
        '@': resolve(__dirname, 'src'), // 把 @ 指向到 src 目录去
        'vue-i18n': 'vue-i18n/dist/vue-i18n.cjs.js',
      },
    },
    server: {
      host: true, // host设置为true才可以使用network的形式，以ip访问项目
      port: 3000,
      open: true,
      cors: true,
      strictPort: true,
      // 接口代理
      proxy: {
        '/api': {
          target: 'http://localhost:8999',
          //     target: 'https://118.89.71.118/',
          changeOrigin: true, // 允许跨域
          rewrite: (path) => path.replace('/api/', '/'),
        },
      },
      build: {
        // outDir: resolve(__dirname, `dist`), // 指定输出路径
        sourcemap: false, // 这个生产环境一定要关闭，不然打包的产物会很大
        assetsInlineLimit: 4096, // 小于此阈值的导入或引用资源将内联为 base64 编码，以避免额外的 http 请求
        emptyOutDir: true, // Vite 会在构建时清空该目录
        reportCompressedSize: false,

        // 消除打包大小超过500kb警告
        chunkSizeWarningLimit: 2000,
        minify: 'esbuild',
        assetsDir: 'static/assets',
        // 静态资源打包到dist下的不同目录
        rollupOptions: {
          output: {
            compact: true, // 压缩代码，删除换行符等
            chunkFileNames: 'static/js/[name]-[hash].js',
            entryFileNames: 'static/js/[name]-[hash].js',
            assetFileNames: 'static/[ext]/[name]-[hash].[ext]',
          },
        },
      },
      // css: {
      //     preprocessorOptions: {
      //         // 全局引入了 scss 的文件
      //         scss: {
      //             additionalData: `@import "@/assets/styles/variables.scss";`,
      //             javascriptEnabled: true,
      //         },
      //     },
      // },
    },
  }
})
