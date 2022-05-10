<template>
  <v-card height="300px">
    <v-container class="pa-0" >
      <v-row no-gutters>
        <v-col cols="auto" v-for="(pic,index) in shareSet.pic_name" :key="index">
          <img :src="'/api/photo/'+pic.pic_name" alt="My Image">
        </v-col>
<!--        <v-col cols="auto">-->
<!--          <img :src="'/api/photo/'+shareSet.pic_name[1].pic_name" alt="My Image">-->
<!--        </v-col>-->
<!--        <v-col cols="auto">-->
<!--          <img :src="'/api/photo/'+shareSet.pic_name[2].pic_name" alt="My Image">-->
<!--        </v-col>-->
<!--        <v-col cols="auto">-->
<!--          <img :src="'/api/photo/'+shareSet.pic_name[3].pic_name" alt="My Image">-->
<!--        </v-col>-->

        <v-col cols="auto">
          <img src="/api/photo/shareImage/....png" alt="My Image">
        </v-col>

      </v-row>
      <v-row class="ma-5" style="height: 100px">
        <v-col cols="3" class="fill-height">
          <h3 >{{ shareSet.share_title }}</h3>
          <h4 class="grey--text">{{ shareSet.share_contents }}</h4>
        </v-col>
        <v-col align-self="center" cols="6">
          <h3>#{{ shareSet.share_place }} #먹거리</h3>
        </v-col>
        <v-col class="grey--text">
          <h4>{{ shareSet.user_id }}님의 일정</h4>
          <h3>공유된 횟수 : {{shareSet.share_count}}</h3>
        </v-col>
      </v-row>
      <v-row justify="end" class="ma-5">
        <v-col class="grey--text" cols="auto">
          <h5>{{shareSet.share_created}}</h5>
        </v-col>
      </v-row>
    </v-container>
  </v-card>
</template>

<script>
// import axios from 'axios';


export default {
  name: "PlannerShareCard",
  props: ["shareSet"],
  data() {
    return {}
  },
  mounted() {
  },
  methods: {}
}
</script>

<style scoped>
img{
  height: 120px;
  width: 120px;
}

</style>
