import Vue from 'vue';
import VueX from 'vuex';
import user from './modules/user';
import hotel from './modules/hotel';

Vue.use(VueX);

export const store = new VueX.Store({

    modules: {
        user,
        hotel,
    }
});
