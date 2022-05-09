<template>
  <v-card>
    <v-container>
      <v-row>
        <v-col cols="2">
          <img src="/api/photo/storeImage/1649556244666bro.png" alt="My Image">
        </v-col>
        <v-col cols="2">
          <img src="/api/photo/storeImage/1649556244666bro.png" alt="My Image">
        </v-col>
        <v-col cols="2">
          <img src="/api/photo/storeImage/1649556244666bro.png" alt="My Image">
        </v-col>
        <v-col cols="2">
          <img src="/api/photo/storeImage/1649556244666bro.png" alt="My Image">
        </v-col>
        <v-col cols="2">
          <img src="/api/photo/shareImage/....png" alt="My Image">
        </v-col>

      </v-row>
      <v-row>
        <v-col>
          <h3>하나여행</h3>
          <h4>둘셋넷다섯~</h4>
        </v-col>
        <v-col>
          <h3>#{{ shareSet }} #먹거리</h3>
        </v-col>
        <v-col>
          <h3>bsh2님의 일정</h3>
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
  height: 100px;
  width: 100px;
}
</style>
