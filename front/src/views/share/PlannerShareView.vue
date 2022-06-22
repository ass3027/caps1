<template>
  <v-container style="padding-top: 60px">
    <v-row
      justify="center"
    >
      <v-col
        cols="9"
      >
        <v-row justify="space-between" style="padding-bottom: 20px">
          <h2>플래너 공유 게시판</h2>
          <button @click="loginCheck" style="margin-top: 20px">
            글 작성하기
          </button>
        </v-row>
        <v-row justify="center">
          <v-col
            v-for="(item,index) in tableSets"
            :key="index"
            cols="12"
            style="padding: 0; margin-bottom: 20px"
            @click="$router.push({name:'shareDetails', params:{id:item.share_id}})"
          >
            <planner-share-card :share-set="item" />
          </v-col>
        </v-row>
        <place-recommend />
        <best-place />
      </v-col>
    </v-row>
  </v-container>
</template>

<script>

import axios from 'axios';
import PlaceRecommend from "@/components/recommend/PlaceRecommend";
import bestPlace from "@/components/recommend/BestPlace";
import PlannerShareCard from "@/components/recommend/PlannerShareCard";


export default {
  name: "PlannerShareView",
  components: {PlaceRecommend, bestPlace, PlannerShareCard},
  data() {
    return {
      sets: [],
      tableSets: [],

    }
  },
  mounted() {
    axios.get('/api/getSharePosts')
      .then((res) => {
        let now = new Date()
        let today = new Date(now.toLocaleDateString());
        res.data.forEach(i => {
          var thisDate = new Date(i.share_created)
          if (today > thisDate) {
            i.share_created = i.share_created.substring(0, 10)
          } else {
            i.share_created = thisDate.toString().substring(16, 21)
          }
        })
        this.tableSets = res.data;
      })
  },
  methods: {
    loginCheck() {
      if (this.$store.state.user.userId) {
        this.$router.push({name: 'shareWrite'})
      } else {
        alert("로그인해주세요")
      }
    }
  }
}
</script>

<style scoped>

button {
  border: 1px solid black;
  background-color: white;
  color: black;
  display: inline-block;
  width: 120px;
  vertical-align: top;
  height: 44px;
  border-radius: 3px;
  font-weight: 700;
  font-size: 14px;
  line-height: 40px;
  text-align: center;
  outline: none;
}

</style>
