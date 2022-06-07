<template>
  <v-app id="app">
    <div class="Bag-order">
      <p class="text-center">
        가방예약
      </p>
    </div>

    <v-row justify="space-around">
      <v-col
        cols="6"
        sm="2"
        class="mx-auto"
      >
        <v-row justify="center">
          <v-img src="@/image/storage1.png" />
        </v-row>
        <v-row justify="center">
          <v-btn
            color="primary"
            @click="$router.push({name:'StorageOrder'})"
            class="ma-3"
          >
            가방 보관
          </v-btn>
        </v-row>
      </v-col>
      <v-divider vertical />
      <v-col
        cols="6"
        sm="2"
        class="mx-auto"
      >
        <v-row justify="center">
          <v-img src="@/image/transport1.png" />
        </v-row>
        <v-row justify="center">
          <v-btn
            color="primary"
            @click="$router.push({name:'TransportOrder'})"
            class="ma-3"
          >
            가방배송
          </v-btn>
        </v-row>
      </v-col>
    </v-row>

    <router-view />
  </v-app>
</template>

<script>
import axios from "axios";

export default {
  components: {},
  data: () => ({

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
