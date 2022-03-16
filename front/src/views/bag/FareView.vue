<template>
  <v-app id="app">
    <HelloWorld />
    
    <div>
      {{ fareView }}
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
      fareView: '요금'
    }
  }
}
</script>

<style scoped>

</style>