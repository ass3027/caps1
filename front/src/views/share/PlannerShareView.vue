<template>
  <div>
    <h2>플래너 공유 게시판</h2>
    <template>
      <v-simple-table
        dark
        dense
      >
        <thead>
          <tr>
            <th class="text-center">
              share_id
            </th>
            <th class="text-center">
              title
            </th>
            <th class="text-center">
              place
            </th>
            <th class="text-center">
              share_created
            </th>
          </tr>
        </thead>
        <tbody>
          <tr
            v-for="(item,index) in tableSets"
            :key="index"
            class="text-center"
          >
            <td>
              <router-link :to="{name:'shareDetails', params:{id:item.share_id}}">
                {{ item.share_id }}
              </router-link>
            </td>
            <td>
              <router-link :to="{name:'shareDetails', params:{id:item.share_id}}">
                {{ item.share_title }}
              </router-link>
            </td>
            <td>
              {{ item.share_place }}
            </td>

            <td>{{ item.share_created }}</td>
          </tr>
        </tbody>
      </v-simple-table>
    </template>
    <v-btn @click="loginCheck">
      작성하기
    </v-btn>
    <place-recommend></place-recommend>
  </div>
</template>

<script>
import axios from 'axios';
import placeRecommend from "@/components/PlaceRecommend";
import PlaceRecommend from "@/components/PlaceRecommend";

export default {
  name: "PlannerShareView",
  components: {PlaceRecommend},
  data() {
    return {
      sets: [],
      tableSets: [],

    }
  },
  mounted() {
    axios.get('/api/getSharePosts')
        .then((res) => {
          this.tableSets = res.data;
          console.log(res.data);
        })
  },
  methods:{
    loginCheck(){
      if(this.$store.state.user.userId){
        this.$router.push({name:'shareWrite'})
      }else{
        alert("로그인해주세요")
      }
    }
  }
}
</script>

<style scoped>

</style>
