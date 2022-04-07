<template>
  <div>
    <h2>플래너 공유 작성 게시판</h2>
    <v-text-field  v-model="title" placeholder="제목" maxlength="50"></v-text-field>
    <v-text-field v-model="place" placeholder="여행지" maxlength="15"></v-text-field>
    <v-textarea v-model="contents" maxlength="250"></v-textarea>

    <v-btn @click="loadPlanner">플래너 가져오기</v-btn>

    <div v-if="toggle">
      <select v-model="selectedPlan">
        <option>선택해주세요</option>
        <option :value="planner.plan_id"  v-for="planner in planners" :key="planner.plan_id">{{planner.plan_name}}여행</option>
      </select>
      <v-btn @click="loadPictures">사진 가져오기</v-btn>
      <ul>
        <li v-for="picture in pictures" :key="picture.pic_name">
          <v-checkbox
              v-model="selectedPic"
              :label="picture.pic_name"
              :value="picture.pic_name"
          ></v-checkbox>
        </li>
      </ul>{{selectedPic}}
    </div>
    <!--    <div><img src=></div>-->
    <v-btn @click="addPost">작성완료</v-btn>
  </div>
</template>

<script>
import axios from 'axios';

export default {
  name: "PlannerShareWriteView",
  components:{
  },
  data(){
    return{
      planners:[],
      pictures:[],
      toggle:false,
      selectedPlan:'',
      selectedPic:[],
      title:'',
      place:'',
      contents:''
    }
  },
  mounted() {
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
      console.log(this.selectedPlan)
      axios.get('/api/loadPictures',{
        params: {
          id: this.selectedPlan
        }
      })
          .then(res=>{
            console.log(res.data)
            this.pictures=res.data
          })
    },
    addPost(){
      var Share = {
        share_id:'',
        user_id:'127',
        share_place:this.place,
        share_title:this.title,
        share_contents:this.contents,
        share_created:'',
        plan_id:this.selectedPlan
      }
      axios.post('/api/addPost',Share)
          .then((res)=>{
            console.log(res.data)
            var pictures = []
            var inner = {}
            this.selectedPic.forEach((i)=>{
              inner = {
                pic_name:i,
                share_id:res.data
              }
              pictures.push(inner)
            })
            console.log(pictures)

            axios.post('/api/addPostPic',pictures)
            .then((res)=>{
              console.log(res.data)
              alert('작성완료')
              this.$router.push({name:'share'})
            })
          })
    }

  }
}
</script>

<style scoped>

</style>