<template>
  <div>
    <h2>공유 플래너 상세보기({{ $route.params.id }})</h2>

    <p>
      id:{{ share.share_id }}
    </p>
    <p>
      장소:{{ share.share_place }}
    </p>
    <p>
      제목:{{ share.share_title }}
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
        {{ schedule }}
      </li>
    </ul>
    <h3>-----------</h3>
    <h3>사진들</h3>
    <ul>
      <li
        v-for="(picture,index) in pictures"
        :key="index"
      >
        {{ picture.pic_name }}
      </li>
    </ul>

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
      axios.get('/api/copyPlanner',{
        params:{
          plan_id:this.share.plan_id,
          user_id:1
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

</style>