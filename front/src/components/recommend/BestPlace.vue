<template>
  <v-row v-if="mode=='place'">
    <v-col
      v-for="(item,i) in items"
      :key="i"
      cols="2"
    >
      <img
        :src="item.firstimage"
        @click="$router.push({path:`/tour/${item.pl_id}`})"
      >
      <span class="innerText">
        <h3>{{ item.title }}</h3>
        <template v-if="$store.state.user.userId!='anonymousUser'">
          <v-btn @click="toCalendar(item)">
            일정에추가
          </v-btn>

        </template>

      </span>
    </v-col>
  </v-row>
  <v-row v-else-if="mode=='hotel'">
    <v-col
      v-for="(item,i) in items"
      :key="i"
      cols="2"
      @click="$router.push({path:`/palce/hotel/${item.pl_id}`})"
    >
      <img
        :src="item.firstimage"
      >
      <span class="innerText">
        <h3>{{ item.title }}</h3>
        <!--        <v-btn @click="toCalendar(item)">-->
        <!--          예약하기-->
        <!--        </v-btn>-->
      </span>
    </v-col>
  </v-row>
  <!--    가이드시-->
  <v-row v-else>
    <v-col
      v-for="(item,i) in items"
      :key="i"
      cols="2"
      @click="$router.push({path:`/GuideProduct/${item.gitem_id}`})"
    >
      <img
        :src="item.firstimage"
      >
      <span class="innerText">
        <h3>{{ item.gname }}</h3>
        <!--        <v-btn @click="toCalendar(item)">-->
        <!--          일정에추가-->
        <!--        </v-btn>-->
        <p style="height: 45px;overflow: hidden">{{ item.introduce }}</p>
      </span>
    </v-col>
  </v-row>
</template>

<script>
import axios from "axios";

export default {
  name: "PlaceRecommend",
  props: ['mode'],
  data() {
    return {
      items: [],
    }
  },
  mounted() {
    //모드의 종류 place,hotel,guide

    if (this.mode == 'place') {
      axios
        .get('/api/bestPlace')
        .then(res => {

          res.data.forEach(i => {
            if (i.pic_name == null) {
              i.pic_name = "placeImage/noImage.png"
            }
          })
          this.items = res.data

        })
    }
    if (this.mode == 'hotel') {
      axios
        .get('/api/bestHotel')
        .then(res => {

          res.data.forEach(i => {
            if (i.pic_name == null) {
              i.pic_name = "placeImage/noImage.png"
            }
          })
          this.items = res.data

        })
    }
    if (this.mode == 'guide') {
      axios
        .get('/api/bestGitem')
        .then(res => {
          console.log(res.data)
          res.data.forEach(i => {
            if (i.pic_name == null) {
              i.pic_name = "placeImage/noImage.png"
            }
          })
          this.items = res.data

        })
    }
  },
  methods: {
    toCalendar(item) {
      this.$router.push({name: 'calender', params: {rec: item.title}})
    }

  }

}
</script>

<style scoped>
/*.v-carousel{*/
/*  width: 500px;*/
/*  height: 300px;*/
/*  align: center;*/
/*}*/
.innerText {
  /*text-align: right;*/
  /*margin: 10px;*/
}

img {
  width: 180px;
  height: 180px;
}
</style>
