<template>
  
  <v-app id="app">
    <HelloWorld/>
    
    <div class=Bag-order>
      가방예약
    </div>
    
    <v-row
      justify="space-around">
      <v-col
        cols="6"
        sm="2"
      >
        <v-img
          src="@/image/storage1.png"
        ></v-img>
      </v-col>
      
      <v-col
        cols="6"
        sm="2"
      >
        <v-img
          src="@/image/transport1.png"
        ></v-img>
      </v-col>
    </v-row>
    
    <v-row
      justify="space-around"
    >
      <v-btn
        depressed
        color="primary"
      >
        가방 보관
      </v-btn>
      
      <v-btn
        @click="$router.push({name:'TransportView'})"
        depressed
        color="primary"
      >
        가방운송
      </v-btn>
    </v-row>
    
    <router-view/>
  </v-app>

</template>

<script>
import HelloWorld from '@/components/HelloWorld.vue'

export default {
  components: {
    HelloWorld,
  },
  data: () => ({
    transport: '/TransportView',
    return: {}
  }),
}
</script>

<style scoped>
.Bag-order {
  margin: 10px;
  font-size: xx-large;
}


/*.storage{*/
/*  margin: 10px;*/
/*  padding: 100px;*/
/*}*/
/*.transport{*/
/*  margin: 10px;*/
/*  padding: 50px;*/
/*}*/
</style>