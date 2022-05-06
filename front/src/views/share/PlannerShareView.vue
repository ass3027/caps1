<template>
  <div>
    <h2>플래너 공유 게시판</h2>
    <v-simple-table
      dark
      dense
    >
      <thead>
      <tr>
        <th class="text-center">
          게시글번호
        </th>
        <th class="text-center">
          제목
        </th>
        <th class="text-center">
          장소
        </th>
        <th class="text-center">
          작성시간
        </th>
      </tr>
      </thead>
      <tbody>
      <tr
        v-for="(item,index) in tableSets"
        :key="index"
        class="text-center"
        @click="$router.push({name:'shareDetails', params:{id:item.share_id}})"
      >
        <td>
          {{ item.share_id }}
        </td>
        <td>
          {{ item.share_title }}
        </td>
        <td>
          {{ item.share_place }}
        </td>

        <td>{{ item.share_created }}</td>
      </tr>
      </tbody>
    </v-simple-table>
    <v-btn @click="loginCheck">
      작성하기
    </v-btn>
    <v-container>
      <v-row v-col v-for="i in 3" :key="i">
        <v-col v-for="i in 10" :key="i">
          <planner-share-card></planner-share-card>
        </v-col>
      </v-row>
    </v-container>
    <place-recommend></place-recommend>
    <best-place></best-place>
  </div>
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
