<template>
  <v-container>
    <v-row
      justify="center"
    >
      <v-col
        cols="9"
      >

        <v-row justify="space-between">
          <h2>플래너 공유 게시판</h2>
          <v-btn @click="loginCheck">
            글 작성하기
          </v-btn>
        </v-row>
        <v-row justify="center">
          <v-col
            v-for="(item,index) in tableSets" :key="index" cols="12"
            @click="$router.push({name:'shareDetails', params:{id:item.share_id}})"
          >
            <planner-share-card :shareSet="item"></planner-share-card>
          </v-col>
        </v-row>
        <place-recommend></place-recommend>
        <best-place></best-place>

      </v-col>
    </v-row>
  </v-container>
</template>

<script>

import axios from 'axios';
import PlaceRecommend from "@/components/PlaceRecommend";
import bestPlace from "@/components/BestPlace";
import PlannerShareCard from "@/components/PlannerShareCard";


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

</style>
