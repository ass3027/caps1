<template>
  <div>
    <h2>플래너 공유 작성 게시판</h2>
    <p>회원의 어떤 플래너를 공유할지 찾아서 선택해야겠지? 셀렉트로 만들고</p>
    <p>사진들을 가져오면 좋을거같은데</p>
    <v-btn @click="loadPlanner">플래너 가져오기</v-btn>

    <div v-if="toggle">
      <select v-model="selectedPic">
        <option>선택해주세요</option>
        <option :value="planner.plan_id"  v-for="planner in planners" :key="planner.plan_id">{{planner.plan_name}}여행</option>
      </select>
      <v-btn @click="loadPictures">사진 가져오기</v-btn>
    </div>
  </div>



<!--    <div><img src=></div>-->




<!--  <div id='example-3'>-->
<!--    <input type="checkbox" id="jack" value="Jack" v-model="checkedNames">-->
<!--    <label for="jack">Jack</label>-->
<!--    <input type="checkbox" id="john" value="John" v-model="checkedNames">-->
<!--    <label for="john">John</label>-->
<!--    <input type="checkbox" id="mike" value="Mike" v-model="checkedNames">-->
<!--    <label for="mike">Mike</label>-->
<!--    <br>-->
<!--    <span>체크한 이름: {{ checkedNames }}</span>-->
<!--  </div>-->

</template>

<script>
import axios from 'axios';

export default {
  name: "PlannerShareView",
  components:{
  },
  data(){
    return{
      planners:[],
      toggle:false,
      checkedNames:[],
      selectedPic:''
    }
  },
  mounted() {
    axios.post('/api/addPost','')
    .then((res)=>{
      console.log(res.data);
    })
  },
  methods:{
    loadPlanner(){
      axios.get('/api/loadPlanner',{
        params: {
          id: '127'
        }
      })
      .then(res=>{
        this.planners=res.data;
        this.toggle=true;
      })
    },
    loadPictures(){
      console.log(this.selectedPic)
      axios.get('/api/loadPictures',{
        params: {
          id: this.selectedPic
        }
      })
          .then(res=>{
            console.log(res)
          })
    }

  }
}
</script>

<style scoped>

</style>