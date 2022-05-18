<template>
  <div>
    <h1>게시판 상세보기</h1>

    <v-col
      class="d-flex"
      cols="12"
      sm="10"
    />
    <v-select
      v-model="inq_type"
      :items="items"
      label="분류"
      outlined
    />

    <v-text-field
      v-model="user_id"
      type="text"
      label="아이디"
    />


    <v-text-field
      v-model="post.inq_title"
      type="text"
      label="제목"
    />

    <v-textarea
      v-model="post.inq_body"
      type="text"
      label="내용"
      maxlenght="5"
    />
    <div id ="button">
      <v-btn elevation="3" type="button" @click="Modify">수정</v-btn>
    </div>

  </div>
</template>

<script>
import axios from "axios";

export default {
  name: "DetailPageView",
  data(){
    return{
      inq_type:'',
      user_id:'',
      inq_title:'',
      inq_body:'',
      inq_file:'',
      items:['여행시설문의','가방관리문의','가이드문의', '기타문의'],
      post:{},
    }
  },
  mounted() {
    axios.get("/api/inquiry/DetailPage", {
      params:{
        inq_id:this.$route.params.id
      }
    })
    .then(res=>{
      console.log(res.data)
      this.post = res.data;
      this.inq_type = res.data.inq_type
      this.user_id = res.data.user_id
      this.inq_title = res.data.inq_title
      this.inq_body = res.data.inq_body
      console.log(this.post.inq_title)
      console.log(this.post.user_id)
    })
  },
  methods:{
    Modify(){
      this.$router.push(`/detailPage/${this.$route.params.id}/WritingMod`)
      console.log("사이트가 움직인다")
  }
}

}
</script>

<style scoped>
#button{
  position: center;
  text-align: center;

}
</style>
