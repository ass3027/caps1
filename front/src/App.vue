<template>
  <v-app>
<!--    <HelloWorld />-->

    <v-app-bar-nav-icon @click="drawer = !drawer"></v-app-bar-nav-icon>

    <v-navigation-drawer
        v-model="drawer"
        absolute
        temporary
    >
      <v-list
          nav
          dense
      >
        <v-list-item
          v-for="item in items"
          :key="item.title"
          link
          :to="item.to"
        >
          <v-list-item-icon>
            <v-icon>{{ item.icon }}</v-icon>
          </v-list-item-icon>

          <v-list-item-content>
            <v-list-item-title>{{ item.title }}</v-list-item-title>
          </v-list-item-content>
        </v-list-item>
      </v-list>
    </v-navigation-drawer>

    <router-view />
  </v-app>
</template>
<script>
import router from './router/index';

export default {
  components:{
  },
  router,
  data() {
    return {
      drawer: false,
      items: [
          {title: 'ReviewTest', icon:'mdi-vue-dashboard', to:'/ReviewTest'},
          {title: 'plan', icon:'mdi-vue-dashboard', to:'/plan'}
      ]
    }
  }
}
</script>

<style>
@font-face {
	font-family: 'GmarketSansMedium';
	src: url('https://cdn.jsdelivr.net/gh/projectnoonnu/noonfonts_2001@1.1/GmarketSansMedium.woff')
		format('woff');
	font-weight: normal;
	font-style: normal;
}


nav {
	padding: 30px;
}
nav a {
	font-weight: bold;
	color: #2c3e50;
}


</style>
