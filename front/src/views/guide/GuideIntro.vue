<template>
  <form class="joinGuide">
    <h2>가이드 소개 등록</h2>
    <h1>{{ $store.state.user.userId }}</h1>

    <div class="textForm">
      <input
        v-model="guser_intro"
        name="guideLicense"
        type="text"
        class="license"
        placeholder="소개"


      >
    </div>

    <v-btn
      class="btn"
      @click="onsubmit()"
    >
      JOIN
    </v-btn>
  </form>
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
  },
  mounted() {
    console.log(this.$route.params.user_id)
  }
}
</script>

<style scoped>

</style>
