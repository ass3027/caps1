import Vue from 'vue';
import VueX from 'vuex';
import user from './modules/user';
import stores from './modules/stores';
import createPersistedState from 'vuex-persistedstate'
import calendar from "@/store/modules/calendar";

Vue.use(VueX);

export const store = new VueX.Store({

  modules: {
    user,
    stores,
    calendar,
  },
  plugins: [createPersistedState({
    paths: ["user"]
  })]
});
