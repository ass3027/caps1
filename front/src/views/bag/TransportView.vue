<template>
  <v-app id="app">
    <HelloWorld />
    
    <div>
      {{ transport }}
    </div>
    
    <router-view/>
  </v-app>
  
</template>

<script>
import HelloWorld from '@/components/HelloWorld.vue'

export default {
  components:{
    HelloWorld
  },
  data() {
    return {
      transport: '운송조회'
    }
    
  }
}
</script>

<style scoped>

</style>