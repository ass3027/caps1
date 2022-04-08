<template>
  <v-app id="app">
    <div class="Bag-order">
      요금안내
    </div>
    
    <router-view />
  </v-app>
</template>

<script>


export default {
  components:{
  
  },
  data() {
    return {
    
    }
  }
}
</script>

<style scoped>
.Bag-order {
  margin: 10px;
  font-size: xx-large;
}
</style>