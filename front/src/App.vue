<template>
  <v-app id="app">
    <div>
      <router-link to="/join">JoinView</router-link> |
      <router-link to="/about">AboutView</router-link> |
      <router-link to="/home">HomeView</router-link> |
      <router-link to="/map">MapView</router-link>  |
      <router-link to="/supplies">Supplies</router-link> |
    </div>
    <router-view></router-view>
  </v-app>
</template>

<script>
  import router from './router/index';
  // import KakaoMap from "@/components/KakaoMap";

  export default {
    // components: {KakaoMap},
    router,
  };

</script>





<style>
#app {
  font-family: Avenir, Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
  color: #2c3e50;
}

nav {
  padding: 30px;
}

nav a {
  font-weight: bold;
  color: #2c3e50;
}

</style>
