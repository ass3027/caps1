<template>
  <v-hover
    v-slot="{ hover }"
  >
    <v-card
      height="300px"
      :elevation="hover ? 5 : 2"
    >
      <v-container class="pa-0">
        <!--      사진-->
        <v-row no-gutters>
          <v-col
            v-for="(pic,index) in shareSet.pic_name"
            :key="index"
            cols="auto"
          >
            <img
              :src="'/api/photo/'+pic.pic_name"
              alt="My Image"
            >
          </v-col>
          <v-col
            v-for="(firstimage,index) in shareSet.firstimage"
            :key="index+'f'"
            cols="auto"
          >
            <img
              :src="firstimage"
              alt="My Image"
            >
          </v-col>
        </v-row>
        <!--      내용과 프사-->
        <v-row
          justify="space-around"
          class="ma-0"
          style="height: 100px"
        >
          <v-col
            cols="7"
            class="fill-height text-truncate"
          >
            <h3>{{ shareSet.share_title }}</h3>
            <h4 class="grey--text">
              {{ shareSet.share_contents }}
            </h4>
          </v-col>
          <v-col
            cols="3"
            class="grey--text"
          >
            <h4>공유 수 : {{ shareSet.share_count }}</h4>
            <h4>추천 수 : {{ recommends }}</h4>
          </v-col>
        </v-row>
        <!--      태그와작성시간-->
        <v-row
          justify="space-around"
          class="ma-5"
        >
          <v-col cols="7">
            <h5>#{{ shareSet.share_place }} #{{ preference }}</h5>
          </v-col>
          <v-col
            cols="3"
            class="grey--text"
          >
            <h5>{{ shareSet.user_id }}님의 일정</h5>
            <h5>작성일 : {{ shareSet.share_created }}</h5>
          </v-col>
        </v-row>
      </v-container>
    </v-card>
  </v-hover>
</template>

<script>
import axios from 'axios';

export default {
  name: "PlannerShareCard",
  props: ["shareSet"],
  data() {
    return {
      preference: '',
      recommends:''
    }
  },
  mounted() {
    axios.get("/api/getPreference", {params: {user_id: this.shareSet.user_id}})
      .then((res) => {
        this.preference = res.data
      })
    axios.get('/api/getShareRec', {params: {share_id: this.shareSet.share_id}})
      .then((res) => {
        this.recommends = res.data;
      })

  },
  methods: {}
}
</script>

<style scoped>
img {
  height: 120px;
  width: 120px;
}

</style>
