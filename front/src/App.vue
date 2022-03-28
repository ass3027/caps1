<template>
	<v-app id="app">
		<router-view />
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
@font-face {
	font-family: 'GmarketSansMedium';
	src: url('https://cdn.jsdelivr.net/gh/projectnoonnu/noonfonts_2001@1.1/GmarketSansMedium.woff')
		format('woff');
	font-weight: normal;
	font-style: normal;
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
</style>
