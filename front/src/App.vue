<template>
  <v-app>
    <v-main>
      <HeaderComponent />
      <v-app-bar-nav-icon @click="drawer = !drawer" />

      <v-navigation-drawer
        v-model="drawer"
        app
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
    </v-main>
  </v-app>
</template>
<script>
import HeaderComponent from "@/components/HeaderComponent";

export default {
  components: {
    HeaderComponent
  },

  data() {
    return {
      drawer: false,
      items : [
        {title: 'Review', icon: 'mdi-vue-dashboard', to: '/ReviewView'},
        {title: 'Plan', icon: 'mdi-vue-dashboard', to: '/plan'},
        {title: 'Join', icon: 'mdi-vue-dashboard', to: '/join'}

      ]
    }
  }
}
</script>

<style>
@font-face {
  font-family: 'GmarketSansMedium';
  src: url('https://cdn.jsdelivr.net/gh/projectnoonnu/noonfonts_2001@1.1/GmarketSansMedium.woff') format('woff');
  font-weight: normal;
  font-style: normal;
}

.v-application--wrap {
  min-height: unset;
}

nav {
  padding: 30px;
}

nav a {
  font-weight: bold;
  color: #2c3e50;
}


.v-application--wrap {
  min-height: unset;
}
</style>
