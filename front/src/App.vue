<template>
  <v-app style="min-width: 1050px">
    <v-main>
      <HeaderComponent />


      <router-view style="border-bottom: 1px solid #f7f7f7; padding-bottom: 60px; min-width: 1050px; margin: 0 auto"/>


      <FooterComponent />
    </v-main>
  </v-app>
</template>
<script>
import HeaderComponent from "@/components/HeaderComponent";
import FooterComponent from "@/components/FooterComponent";

export default {
  components: {
    HeaderComponent,
    FooterComponent
  },

  data() {
    return {
      drawer: false,
      items: [
        { title: "Review", icon: "mdi-vue-dashboard", to: "/ReviewView" },
        { title: "Plan", icon: "mdi-vue-dashboard", to: "/plan" },
        { title: "Join", icon: "mdi-vue-dashboard", to: "/join" },
        { title: "MyPage", icon: "mdi-vue-dashboard", to: "/MyPage"}
      ],
    };
  },

  method: {

  }
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
