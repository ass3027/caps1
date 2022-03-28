import Vue from 'vue'
import App from './App.vue'
import router from './router'

//vuetify
import vuetify from "@/plugins/vuetify";

Vue.config.productionTip = false

new Vue({
  router,
  vuetify,

  // vuetify,
  render: h => h(App)
}).$mount('#app')
