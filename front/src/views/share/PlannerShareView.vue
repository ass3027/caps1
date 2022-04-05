<template>
  <div>
    <h2>플래너 공유 게시판</h2>
    <template>
      <v-simple-table dark dense
      >
          <thead>
          <tr>
            <th class="text-center">
              share_id
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
            <td>{{ item.share_id }}</td>
            <td>{{ item.share_created }}</td>
          </tr>
          </tbody>
      </v-simple-table>
    </template>
    {{tableSets}}
    <router-link :to="{name:'shareWrite'}">go to write</router-link>
  </div>
</template>

<script>
import axios from 'axios';

export default {
  name: "PlannerShareView",
  components:{
  },
  data(){
    return{
      sets:[],
      tableSets:[],

    }
  },
  mounted() {
    axios.get('/api/getSharePosts')
    .then((res)=>{
      this.tableSets=res.data;
      console.log(res.data);
    })
  }
}
</script>

<style scoped>

</style>