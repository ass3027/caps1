import Vue from 'vue';
import VueX from 'vuex';
Vue.use(VueX);

export const store = new VueX.Store({
  state: { test: 'asdf' }
});
