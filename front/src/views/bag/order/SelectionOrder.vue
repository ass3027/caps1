<template>
  <v-container>
    <div class="Bag-order">
      <p class="text-center">
        가방예약 선택
      </p>
      <v-col>
        <hr style="width: 80%; margin-left:auto; margin-right: auto; ">
      </v-col>
    </div>

    <v-row
      justify="space-around"
      style="padding-top: 35px"
    >
      <v-col
        cols="6"
        sm="2"
        class="mx-auto"
        style="position: relative; left: 50px"
      >
        <div style="margin-right: auto">
          <v-img src="@/image/storage2.png"/>
        </div>
        <div class="btn_container">
          <v-btn
            class="btn_type2"
            color="white"
            @click="$router.push({name:'storageOrder'})"
          >
            가방 보관
          </v-btn>
        </div>
      </v-col>

      <hr style="height: 300px; margin-left:auto; margin-right: auto; ">

      <v-col
        cols="6"
        sm="2"
        class="mx-auto"
        style="position: relative; right: 50px"
      >
        <div>
          <v-img src="@/image/transport2.png"/>
        </div>
        <div class="btn_container">
          <v-btn
            class="btn_type2"
            color="white"
            @click="$router.push({name:'transportOrder'})"
          >
            가방배송
          </v-btn>
        </div>
      </v-col>
    </v-row>

    <router-view/>

  </v-container>
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

.btn_container {
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
