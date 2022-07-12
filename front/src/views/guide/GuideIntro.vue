<template>
  <div class="resume-view">
    <div id="Contents">
      <div class="resumeWrap">
        <div class="resumeType1">
          <div class="resumeType1-inner">
            <div id="ResumeBaseInfo" class="resumeView">
              <h2 class="hide">
                가이드 소개 수정
              </h2>
              <br>
              <v-textarea
                name="input-7-1"
                v-model="guser_intro"
                filled
                label="소개"
                auto-grow
              ></v-textarea>
              <v-btn
                class="btn"
                @click="onsubmit()"
              >
                SUBMIT
              </v-btn>
            </div>
          </div>

        </div>
      </div>
    </div>
  </div>
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
        this.$router.push("/GuideView/"+ this.$store.state.user.userId)
      })
    }
  }
}
</script>

<style scoped>

</style>
