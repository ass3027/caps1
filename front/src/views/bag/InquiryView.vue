<template>
  <v-app id="app">
    <HelloWorld/>
    
    <div class=Bag-order>
      배송조회
    </div>
    
    <router-view/>
  </v-app>

</template>

<script>
import HelloWorld from '@/components/HelloWorld.vue'

export default {
  components: {
    HelloWorld
  },
  data() {
    return {}
  }
}
</script>

<style scoped>
.Bag-order {
  margin: 10px;
  font-size: xx-large;
}
</style>