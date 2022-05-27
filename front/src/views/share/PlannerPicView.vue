<template>
  <div>
    <PlannerHeader />
    <h2>플래너 사진 공유게시판({{ $store.state.user.planId }})</h2>
    <h2>사진 불러오기</h2>
    <div>
      <img
        v-for="photo in photos"
        :key="photo.pic_name"
        :src="'/api/photo/'+photo.pic_name"
      >
    </div>
    <h2>사진 넣기</h2>
    <input
      ref="refImage"
      type="file"
      placeholder="photo"
    >
    <v-btn @click="upload">
      사진업로드
    </v-btn>
  </div>
</template>

<script>

// import axios from 'axios'
import PlannerHeader from "@/components/PlannerHeader";
import axios from "axios";

export default {
  name: 'PlannerPicView',
  components: {
    PlannerHeader,
  },
  data() {
    return {
      user_photo:'',
      photos:''
    }
  },
  mounted() {
    axios.get('/api/planner/getPlanPic',{
      params:{
        plan_id:this.$store.state.user.planId,
      }
    })
    .then(res=>{
      console.log("?")
      console.log(res.data)
      this.photos=res.data;
    })
    .catch((err)=>{
      alert(err)
    })
  },
  methods: {
    upload(){
      this.user_photo = this.$refs.refImage.files[0];
      console.log(this.user_photo)
      if(!this.user_photo){
        alert("사진을 넣어주세요")
      }
      var data = {
        pic_name: this.user_photo,
        plan_id:this.$store.state.user.planId,
        user_id:this.$store.state.user.userId
      }

      var sendform = new FormData();

      sendform.append('pic_name', this.user_photo);
      sendform.append('plan_id', this.$store.state.user.planId);
      sendform.append('user_id', this.$store.state.user.userId);

      axios({
        method: 'post',
        url: '/api/planner/addPlanPic',
        headers: {
          'Content-Type': 'multipart/form-data',
        },
        data: sendform,
      }).then((res) => {
        console.log(res.data)
        if (res.data === true) {

          alert('ok');
          location.reload();
        }
      });


    }
  }
}

</script>

<style scoped>
img{
  border: 1px solid;
  width: 200px;
  height: 200px;
}
</style>
