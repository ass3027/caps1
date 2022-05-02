import Vue from 'vue';
import VueX from 'vuex';
import user from './modules/user';
import hotel from './modules/hotel';
import createPersistedState from 'vuex-persistedstate';
import stores from './modules/stores';
import createPersistedState from 'vuex-persistedstate'

Vue.use(VueX);

export const store = new VueX.Store({

  modules: {
    user,
    hotel,
    stores
  },
  plugins: [createPersistedState({
    paths: ["user"]
  })]
});
