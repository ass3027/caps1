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

nav {
  padding: 30px;
}
nav a {
  font-weight: bold;
  color: #2c3e50;
}

</style>
