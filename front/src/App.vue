<template>
  <v-app>
    <HelloWorld/>

    <router-view/>
  </v-app>


</template>
<script>


import HelloWorld from "@/components/HelloWorld";

export default {
  components:{
  HelloWorld
  }
}
</script>

<style>
#app {
  font-family: Avenir, Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
  color: #2c3e50;
}
.font-set {
	font-family: 'GmarketSansMedium';
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
