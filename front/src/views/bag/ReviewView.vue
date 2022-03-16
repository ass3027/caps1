<template>
  <v-app id="app">
    <HelloWorld />
    
    <div>
      {{ reviewView }}
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
      reviewView: '후기'
    }
    
  }
}
</script>

<style scoped>

</style>