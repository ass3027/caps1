<template>
  <v-app>
        <HelloWorld />

    <router-view />
  </v-app>
</template>
<script>


import HelloWorld from "@/components/HelloWorld";
export default {
  components:{
    HelloWorld
  },
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
