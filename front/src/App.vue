<template>
  <v-app>
    <v-main>
      <HeaderComponent />


      <router-view />
    </v-main>
  </v-app>
</template>
<script>
import HeaderComponent from "@/components/HeaderComponent";

export default {
  components: {
    HeaderComponent,
  },

  data() {
    return {
<<<<<<< Updated upstream
      drawer: false,
      items: [
        { title: "Review", icon: "mdi-vue-dashboard", to: "/ReviewView" },
        { title: "Plan", icon: "mdi-vue-dashboard", to: "/plan" },
        { title: "Join", icon: "mdi-vue-dashboard", to: "/join" },
        { title: "MyPage", icon: "mdi-vue-dashboard", to: "/MyPage"}
      ],
=======

>>>>>>> Stashed changes
    };
  },
};
</script>

<style>
@font-face {
  font-family: "GmarketSansMedium";
  src: url("https://cdn.jsdelivr.net/gh/projectnoonnu/noonfonts_2001@1.1/GmarketSansMedium.woff")
    format("woff");
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
