<template>
  
  <v-app id="app">
    <HelloWorld />
    <router-view/>
    
    <div class = Bag-order>
      {{ order }}
    </div>
    <v-row
      align="center"
      justify="space-around"
    >
<!--   <v-img src="..src/"></v-img>-->
      <v-btn
        depressed
        color="primary"
      >
        가방 보관
      </v-btn>
  
      <v-btn
        depressed
        color="primary"
      >
        가방운송
      </v-btn>
      
    </v-row>
  </v-app>
  
  

</template>

<script>
import HelloWorld from '@/components/HelloWorld.vue'

export default {
  components: {
    HelloWorld
  },
  data() {
    return {
      order: '가방예약'
    }
  }
}
</script>

<style scoped>
.Bag-order{
  margin: 10px;
  font-size: xx-large;
}


</style>