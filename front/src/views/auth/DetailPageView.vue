<template>
  <div>
    <h1>게시판 상세보기</h1>

    <v-card
      class="mx-auto"
      max-width="1200"
      max-height="500"

    >

    <v-card-text>
      <p class="text-h6 text--primary">분류</p>
      <div class="b"> {{post.inq_type}}</div>

      <p class="text-h6 text--primary">아이디</p>
      <div class="b"> {{post.user_id}}</div>

      <p class="text-h6 text--primary">제목</p>
      <div class="b"> {{post.inq_title}}</div>

      <p class="text-h6 text--primary">내용</p>
      <div class="b"> {{post.inq_body}}</div>

      <p class="text-h6 text--primary">문의일시</p>
      <div class="b"> {{post.inq_time}}</div>

    </v-card-text>
    </v-card>




    <br>
    <v-card
      class="mx-auto"
      max-width="1200"
      max-height="500"
    >

      <v-card-text>
        <p class="text-h6 text--primary">관리자답변</p>
        <p class="text-h6 text--primary">제목</p>
        <div class="b"> {{ans.ans_title}}</div>

        <p class="text-h6 text--primary">답변내용</p>
        <div class="b"> {{ans.ans_body}}</div>

        <p class="text-h6 text--primary">답변일시</p>
        <div class="b"> {{ans.ans_time}}</div>

      </v-card-text>
    </v-card>


<!--    <v-col-->
<!--      class="d-flex"-->
<!--      cols="12"-->
<!--      sm="10"-->
<!--    />-->
<!--    <v-select-->
<!--      v-model="inq_type"-->
<!--      :items="items"-->
<!--      label="분류"-->
<!--      outlined-->
<!--    />-->


<!--    <v-text-field-->
<!--      v-model="user_id"-->
<!--      type="text"-->
<!--      label="아이디"-->
<!--    />-->


<!--    <v-text-field-->
<!--      v-model="post.inq_title"-->
<!--      type="text"-->
<!--      label="제목"-->
<!--    />-->

<!--    <v-textarea-->
<!--      v-model="post.inq_body"-->
<!--      type="text"-->
<!--      label="내용"-->
<!--      maxlenght="5"-->
<!--    />-->
    <div id="button">
      <v-btn elevation="3" type="button" @click="Modify">수정</v-btn>
      <v-btn elevation="3" type="button" style="margin: 10px" @click="PageList">목록</v-btn>
      <v-btn elevation="3" type="button" style="margin: 10px" @click="Delete">삭제</v-btn>

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
      inq_time:'',
      ans_title:'',
      ans_body:'',
      ans_time:'',
      items:['여행시설문의','가방관리문의','가이드문의', '기타문의'],
      post:{},
      ans:{}
    }
  },
  mounted() {
    axios.get("/api/inquiry/DetailPage", {
      params:{
        inq_id:this.$route.params.id
      }
    })
    .then(res=>{
      // console.log(res)
      // console.log(res.data["answer"].ans_body)
      this.post = res.data["inquiry"];
      // console.log(this.post)
      // console.log(this.post.inq_body)
      this.ans = res.data["answer"];
      // console.log(this.ans)
      // console.log(this.ans.ans_body)
    })
    // axios.get("api/inquiry/Questions", {
    //   params:{
    //     inq_id:this.$route.params.id
    //   }
    // })
    //   .then(res=>{
    //     // this.post_list = res.data;
    //     // console.log(res.data.inq_count)
    //     this.inq_count = res.data.inq_count;
    //   })
  },
  methods:{
    Modify(){
      this.$router.push(`/detailPage/${this.$route.params.id}/WritingMod`)
      console.log("사이트가 움직인다")
  },
    Delete(){
      axios.delete("/api/inquiry/DetailPage" ,{
        params: {
          inq_id: this.$route.params.id
         }
      })
      .then(res=>{
        alert("삭제하시겠습니까?")
        alert(res.data)
        this.$router.push('/Questions')
      })
      // this.$router.push('/')
      // console.log("사이트가 삭제된다")
    },
    PageList(){
      this.$router.push("/Questions")
    }
}

}
</script>

<style scoped>
#button{
  position: center;
  text-align: center;
  margin: 15px;

}
.b{
  font-size: 15px;
  margin: 15px;
}

</style>
