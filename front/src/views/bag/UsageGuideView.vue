<template>
  <v-app id="app">
    <HelloWorld />
    
    <div>
      {{ usageGuideView }}
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
      usageGuideView: '이용안내'
    }
  }
}
</script>

<style scoped>

</style>