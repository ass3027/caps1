//const { defineConfig } = require('@vue/cli-service')
module.exports = {

  transpileDependencies: [
    'vuetify'
  ],
  outputDir: "../src/main/resources/static",
  devServer: {
    proxy: {
      '/api': {
        target: 'http://localhost:8000',
        changeOrigin: true // cross origin 허용
      }
    },
  },
  //indexPath: "../static/index.html",
}
