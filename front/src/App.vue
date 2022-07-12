<template>
  <v-app style="min-width: 1050px">
    <v-main>
      <HeaderComponent />


      <router-view style="padding-bottom: 60px; min-width: 1050px; margin: 0 auto" />


      <FooterComponent style="border-top: 1px solid #f7f7f7; padding-bottom: 60px; min-width: 1050px; margin: 0 auto" />
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
/*@font-face {*/
/*  font-family: "GmarketSansMedium";*/
/*  src: url("https://cdn.jsdelivr.net/gh/projectnoonnu/noonfonts_2001@1.1/GmarketSansMedium.woff")*/
/*    format("woff");*/
/*  font-weight: normal;*/
/*  font-style: normal;*/
/*}*/

* {
  color : #262626 ;
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

/*.v-btn{*/
/*  box-shadow: 0px 0px 20px 4px rgb(0 0 0 / 20%), 0px 2px 2px 0px rgb(0 0 0 / 14%), 0px 1px 5px 0px rgb(0 0 0 / 12%) !important;*/
/*}*/


</style>
