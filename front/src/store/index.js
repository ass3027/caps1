import Vue from 'vue';
import VueX from 'vuex';
import user from './modules/user'

Vue.use(VueX);

export const store = new VueX.Store({
    state:{
      id:'ss'
    },
    modules: {
        user
    }
});
