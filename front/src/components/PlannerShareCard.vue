<template>
  <v-card height="300px">
    <v-container class="pa-0" >
<!--      사진-->
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
<!--      내용과 프사-->
      <v-row justify="space-around" class="ma-5" style="height: 100px" >
        <v-col cols="7" class="fill-height text-truncate">
          <h3 >{{ shareSet.share_title }}</h3>
          <h4 class="grey--text">{{ shareSet.share_contents }}</h4>
        </v-col>
        <v-col cols="3" class="grey--text">
          <h4>{{ shareSet.user_id }}님의 일정</h4>
          <h4>공유된 횟수 : {{shareSet.share_count}}</h4>
        </v-col>
      </v-row>
<!--      태그와작성시간-->
      <v-row justify="space-around" class="ma-5">
        <v-col cols="7">
          <h5>#{{ shareSet.share_place }} #먹거리</h5>
        </v-col>
        <v-col cols="3" class="grey--text">
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
