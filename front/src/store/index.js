import Vue from 'vue';
import VueX from 'vuex';
import user from './modules/user';
import place from './modules/place';
import createPersistedState from 'vuex-persistedstate'
import calendar from "@/store/modules/calendar";

Vue.use(VueX);

export const store = new VueX.Store({

  state:{
    lists:[],
  },
  modules: {
    user,
    calendar,
    place,
  },
  plugins: [createPersistedState({
    paths: ["user"]
  })]
});
