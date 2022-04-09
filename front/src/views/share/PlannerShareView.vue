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
              {{ item.share_title }}
            </td>
            <td>
              {{ item.share_place }}
            </td>

            <td>{{ item.share_created }}</td>
          </tr>
        </tbody>
      </v-simple-table>
    </template>
    <router-link :to="{name:'shareWrite'}">
      go to write
    </router-link>
    <v-btn @click="testButton">
      테스트버튼
    </v-btn>
  </div>
</template>

<script>
import axios from 'axios';

export default {
  name: "PlannerShareView",
  components: {},
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
    testButton(){
      axios.get('/webapp/userImage/1649155691753bro.png')
      .then((res)=>{
        console.log(res)
      })
    }
  }
}
</script>

<style scoped>

</style>