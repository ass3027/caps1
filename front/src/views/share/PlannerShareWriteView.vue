<template>
  <div>
    <h2>플래너 공유 작성하기</h2>
    <v-text-field
      v-model="title"
      placeholder="제목"
      maxlength="50"
    />
    <v-text-field
      v-model="place"
      placeholder="여행지"
      maxlength="15"
    />
    <v-textarea
      v-model="contents"
      placeholder="내용 입력"
      maxlength="250"
    />

    <v-btn @click="loadPlanner">
      플래너 가져오기
    </v-btn>

    <div v-if="toggle">
      <select v-model="selectedPlan">
        <option>선택해주세요</option>
        <option
          v-for="planner in planners"
          :key="planner.plan_id"
          :value="planner.plan_id"
        >
          {{ planner.plan_name }}
        </option>
      </select>
      <hr>
      <v-btn @click="loadPictures">
        사진 가져오기
      </v-btn>
      <ul>
        <li
          v-for="picture in pictures"
          :key="picture.pic_name"
        >
          <v-checkbox
            v-model="selectedPic"
            :label="picture.pic_name"
            :value="picture.pic_name"
          />
          <div>
            <img :src="'/api/photo/'+picture.pic_name">
          </div>
        </li>
      </ul>
    </div>
    <!--    <div><img src=></div>-->
    <v-btn @click="addPost">
      작성완료
    </v-btn>
  </div>
</template>

<script>
import axios from 'axios';

export default {
  name: "PlannerShareWriteView",
  components: {},
  data() {
    return {
      planners: [],
      pictures: [],
      toggle: false,
      selectedPlan: '',
      selectedPic: [],
      title: '',
      place: '',
      contents: ''
    }
  },
  mounted() {
  },
  methods: {
    loadPlanner() {
      axios.get('/api/loadPlanner', {
        params: {
          id: this.$store.state.user.userId
        }
      })
          .then(res => {
            this.planners = res.data;
            this.toggle = true;
          })
    },
    loadPictures() {
      console.log(this.selectedPlan)
      axios.get('/api/loadPictures', {
        params: {
          id: this.selectedPlan
        }
      })
          .then(res => {
            console.log(res.data)
            this.pictures = res.data
          })
    },
    addPost() {
      console.log(this.selectedPlan)
      if(!this.selectedPlan){
        alert("플래너를 선택해주세요")
        return
      }
      var Share = {
        share_id: '',
        user_id: this.$store.state.user.userId,
        share_place: this.place,
        share_title: this.title,
        share_contents: this.contents,
        share_created: '',
        plan_id: this.selectedPlan
      }
      axios.post('/api/addPost', Share)
          .then((res) => {
            console.log(res.data)
            var pictures = []
            var inner = {}
            this.selectedPic.forEach((i) => {
              inner = {
                pic_name: i,
                share_id: res.data
              }
              pictures.push(inner)
            })
            console.log(pictures)

            axios.post('/api/addPostPic', pictures)
                .then((res) => {
                  console.log(res.data)
                  alert('작성완료')
                  this.$router.push({name: 'share'})
                })
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
