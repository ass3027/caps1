import Vue from 'vue';
import VueX from 'vuex';
import user from './modules/user';
import hotel from './modules/hotel';
import calendar from './modules/calendar'
import createPersistedState from 'vuex-persistedstate'

Vue.use(VueX);

export const store = new VueX.Store({

  modules: {
    user,
    hotel,
    calendar
  },
  plugins: [createPersistedState({
    paths: ["user"]
  })]
});
