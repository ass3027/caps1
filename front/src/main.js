import Vue from 'vue'
import App from './App.vue'
import router from './router'


//vuetify
import vuetify from './plugins/vuetify'
import { store } from '@/store/index.js'

Vue.config.productionTip = false

new Vue({
  router,
  vuetify,

  // vuetify,
  store: store,
  render: h => h(App)
}).$mount('#app')
