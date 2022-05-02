<template>
  <div>
    <h2>공유 플래너 상세보기({{ $route.params.id }})</h2>
    <h2>공유된 횟수:{{share.share_count}}</h2>
    <h2>
      {{ share.share_title }}
    </h2>
    <p>
      장소:{{ share.share_place }}
    </p>
    <p>
      내용:{{ share.share_contents }}
    </p>

    <h3>-----------</h3>
    <h3>일정 or 지도 컴포넌트</h3>
    <ul>
      <li
        v-for="schedule in schedules"
        :key="schedule.sch_number"
      >
<!--@todo cheack-->
        {{ schedule }}
      </li>
    </ul>
    <h3>-----------</h3>
    <h3>사진들</h3>
    <div>
      <img
        v-for="photo in pictures"
        :key="photo.pic_name"
        :src="'/api/photo/'+photo.pic_name"
      >
    </div>

    <v-btn @click="copyPlanner">
      일정 복제하기
    </v-btn>
  </div>
</template>

<script>
import axios from 'axios';

export default {
  name: "PlannerShareDetailsView",
  components:{
  },
  data(){
    return{
      share:{},
      schedules:[],
      pictures:[]

    }
  },
  mounted() {
    axios.get('/api/getSharePostDetails',{
      params:{
        share_id:this.$route.params.id
      }
    })
    .then((res)=>{
      this.tableSets=res.data;
      console.log(res.data);
      this.share=res.data[0]
      this.schedules=res.data[1]
      this.pictures=res.data[2]
      console.log(this.share)
      console.log(this.schedules)
      console.log(this.pictures)
    })
  },
  methods:{
    copyPlanner(){
      console.log("여기요")
      console.log(this.$store.state.user.userId)
      if(!this.$store.state.user.userId){
        alert("로그인을 해주세요");
        return;
      }
      axios.get('/api/copyPlanner',{
        params:{
          share_id:this.share.share_id,
          plan_id:this.share.plan_id,
          user_id:this.$store.state.user.userId
        }
      })
      .then((res)=>{
        console.log(res)
        alert("복제성공")
      })
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
