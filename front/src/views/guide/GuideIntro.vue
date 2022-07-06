<template>
  <v-container style="width: 70%; ">
    <v-row>
      <v-col align="center">
        <v-card style="width: 60%;margin: 50px;padding: 50px">
          <h2>가이드 등록</h2>
          <h1>{{ $store.state.user.userId }}</h1>
          <v-text-field
            v-model="guser_intro"
            name="guideLicense"
            type="text"
            class="license"
            placeholder="소개"
            style="width: 60%; margin: 10px"
          />

          <v-btn
            class="btn"
            style="width: 60%; margin: 10px"
            @click="onsubmit()"
          >
            JOIN
          </v-btn>
        </v-card>
      </v-col>
    </v-row>
  </v-container>
</template>

<script>
import axios from "axios";

export default {
  name: "GuideIntro.vue",
  data(){
    return{
      user_id: this.$route.params.user_id,
      guser_intro:''
    }

  },
  mounted() {
    console.log(this.$route.params.user_id)
  },
  methods:{
    onsubmit(){
      var sendform = new FormData();

      sendform.append('user_id', this.user_id);
      sendform.append('guser_intro', this.guser_intro);

      axios({
        method:'put',
        url:'/api/guideIntro/'+this.user_id,
        headers:{
          'Content-Type': 'multipart/form-data'
        },
        data: sendform,
      }).then((res)=>{
        console.log(res)
      })
    }
  }
}
</script>

<style scoped>

</style>
