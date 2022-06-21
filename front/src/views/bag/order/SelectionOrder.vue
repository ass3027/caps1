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
        <div>
          <v-img src="@/image/storage1.png" />
        </div>
        <div class="btn_container">
          <v-btn
            @click="$router.push({name:'StorageOrder'})"
            class="btn_type2"
            color="white"
          >
            가방 보관
          </v-btn>
        </div>
      </v-col>
      <v-divider vertical />
      <v-col
        cols="6"
        sm="2"
        class="mx-auto"
      >
        <div>
          <v-img src="@/image/transport1.png" />
        </div>
        <div class="btn_container">
          <v-btn
            @click="$router.push({name:'TransportOrder'})"
            class="btn_type2"
            color="white"
          >
            가방배송
          </v-btn>
        </div>
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

.btn_type2 {
  display: block;
  overflow: hidden;
  width: 100%;
  height: 54px;
  border-radius: 3px;
  text-align: center;
  border: 1px solid black;
  color: #333;
  margin-top: 10px;
}
.btn_container{
  margin-top: 30px;
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
