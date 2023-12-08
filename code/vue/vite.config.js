import {fileURLToPath, URL} from 'node:url'

import {defineConfig} from 'vite'
import legacy from '@vitejs/plugin-legacy'
import vue2 from '@vitejs/plugin-vue2'

// https://vitejs.dev/config/
export default defineConfig({
    base: "./",
    build: {
      outDir: "dist",
      assetsDir: "assets"
    },
    plugins: [
        vue2(),
        legacy({
            targets: ['ie >= 11'],
            additionalLegacyPolyfills: ['regenerator-runtime/runtime']
        })
    ],
    resolve: {
        alias: {
            '@': fileURLToPath(new URL('./src', import.meta.url))
        }
    },
    server: {
        host: '0.0.0.0',
        port: 81,
        // 是否开启 https
        https: false,
        proxy: {
            "/dev-api": {
                target: "http://localhost:8081",//代理到的服务器地址
                changeOrigin: true,//用于控制请求头中的值,
                rewrite: path => path.replace(/^\/dev-api/, '')
            }
        }
    },

})
