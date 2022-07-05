<template>
  <v-container style="width: 80%">
    <v-divider  class="blue-grey" style="border-width: 1px"></v-divider>
    <v-row v-if="$store.state.user.userId!='anonymousUser'" style="height:800px;text-align:center" class="pt-1" justify="center">
      <v-col cols="3" align-self="start" @click="$router.push({path:'/calender'})">
        <img class="" src="/api/photo/altImage/planner4.png">
        <p>여행플래너 작성하기</p>
        <place-recommend></place-recommend>

      </v-col>
      <v-divider vertical inset class="blue-grey" style="border-width: 1px"></v-divider>
      <v-col cols="3" align-self="start" @click="$router.push({path:'/SelectionOrder'})">
        <img class="" src="/api/photo/altImage/bag4.png">
        <p>가방보관/운송 신청하기</p>
        <best-place mode="place"></best-place>
      </v-col>

      <v-divider vertical inset class="blue-grey" style="border-width: 1px"></v-divider>
      <v-col cols="3" align-self="start" @click="$router.push({path:'/place/hotel'})">
        <img class="" src="/api/photo/altImage/hotel4.png">
        <p>호텔 예약하기</p>
        <best-place mode="hotel"></best-place>
      </v-col>

      <v-divider vertical inset class="blue-grey" style="border-width: 1px"></v-divider>
      <v-col cols="3" align-self="start" @click="$router.push({path:'/GuideProduct'})">
        <img class="" src="/api/photo/altImage/guide8.png">
        <p>현지인가이드 신청하기</p>
        <best-place mode="guide"></best-place>
      </v-col>
    </v-row>

    <v-row v-else style="height:800px;text-align:center" class="pt-1" justify="center">
      <v-col cols="3" align-self="start" @click="$router.push({path:'/calender'})">
        <img class="" src="/api/photo/altImage/planner4.png">
        <p>여행플래너 작성하기</p>
        <place-recommend></place-recommend>

      </v-col>
      <v-divider vertical inset class="blue-grey" style="border-width: 1px"></v-divider>
      <v-col cols="3" align-self="start" @click="$router.push({path:'/SelectionOrder'})">
        <img class="" src="/api/photo/altImage/bag4.png">
        <p>가방보관/운송 신청하기</p>
        <best-place mode="place"></best-place>
      </v-col>

      <v-divider vertical inset class="blue-grey" style="border-width: 1px"></v-divider>
      <v-col cols="3" align-self="start" @click="$router.push({path:'/place/hotel'})">
        <img class="" src="/api/photo/altImage/hotel4.png">
        <p>호텔 예약하기</p>
        <best-place mode="hotel"></best-place>
      </v-col>

      <v-divider vertical inset class="blue-grey" style="border-width: 1px"></v-divider>
      <v-col cols="3" align-self="start" @click="$router.push({path:'/GuideProduct'})">
        <img class="" src="/api/photo/altImage/guide8.png">
        <p>현지인가이드 신청하기</p>
        <best-place mode="guide"></best-place>
      </v-col>
    </v-row>
    {{ $store.state.user.userId }}
<!--    <v-divider  class="blue-grey" style="border-width: 1px"></v-divider>-->

    <v-row justify="center">
      <v-col cols="2">
      </v-col>
      <v-col cols="2">
      </v-col>
      <v-col cols="2">
      </v-col>
      <v-col cols="2">
      </v-col>

    </v-row>

    <v-row>
      <search-tour></search-tour>
    </v-row>

  </v-container>
</template>

<script>
// import axios from "axios";
import PlaceRecommend from "@/components/recommend/PlaceRecommend";
import BestPlace from "@/components/recommend/BestPlace";
import SearchTour from "@/components/SearchTour";

export default {
  name: 'MainView',
  components: {
    BestPlace,
    PlaceRecommend,
    SearchTour
  },
  data() {
    return {}
  },
  mounted() {
  },
  methods: {
    test(){
      this.$router.push({path:"/calender"})
    }
  },
}
</script>
<style scoped>
img{
  width:80%;
  height: 80%;

}
</style>
