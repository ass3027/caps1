<template>
  <div>
    <h1>게시판 상세보기</h1>

    <v-card
      class="mx-auto"
      max-width="1200"
      max-height="500"
    >
      <v-card-text>
        <p class="text-h6 text--primary">
          분류
        </p>
        <div class="b">
          {{ post.inq_type }}
        </div>

        <p class="text-h6 text--primary">
          아이디
        </p>
        <div class="b">
          {{ post.user_id }}
        </div>

        <p class="text-h6 text--primary">
          제목
        </p>
        <div class="b">
          {{ post.inq_title }}
        </div>

        <p class="text-h6 text--primary">
          내용
        </p>
        <div class="b">
          {{ post.inq_body }}
        </div>

        <p class="text-h6 text--primary">
          문의일시
        </p>
        <div class="b">
          {{ post.inq_time }}
        </div>
      </v-card-text>
    </v-card>




    <br>
    <v-card
      class="mx-auto"
      max-width="1200"
      max-height="500"
    >
      <v-card-text>
        <p class="text-h6 text--primary">
          관리자답변
        </p>
        <p class="text-h6 text--primary">
          제목
        </p>
        <div class="b">
          {{ ans.ans_title }}
        </div>

        <p class="text-h6 text--primary">
          답변내용
        </p>
        <div class="b">
          {{ ans.ans_body }}
        </div>

        <p class="text-h6 text--primary">
          답변일시
        </p>
        <div class="b">
          {{ ans.ans_time }}
        </div>
      </v-card-text>
    </v-card>

    <div id="button">
      <v-row justify="center">
        <div v-if="$store.state.user.userId == post.user_id">
          <v-btn elevation="3" type="button" @click="Modify">수정</v-btn>
          <v-btn elevation="3" type="button" style="margin: 10px" @click="Delete">삭제</v-btn>
        </div>
        <v-btn elevation="3" type="button" style="margin: 10px" @click="PageList">목록</v-btn>
      </v-row>

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
      items:['여행시설문의','가방관리문의','가이드문의', '기타문의'], //배열
      post:{}, //객체
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
      // console.log(res.data.inquiry)
      // console.log(res.data.answer.ans_id)
      this.post = res.data["inquiry"];
      console.log(this.post.user_id)
      // console.log(res.data.inquiry.user_id)
      console.log(this.$store.state.user.userId)
      // console.log(this.ans)
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
    Modify(){ //수정
      this.$router.push(`/detailPage/${this.$route.params.id}/WritingMod`)
      console.log("사이트가 움직인다")
  },
    Delete(){ //삭제
      if (confirm("삭제하시겠습니까?") ){ //조건을 걸고싶으면 밑에 if문 써서 조검 달아줌
        alert("삭제되셨습니다.")
      }else{
        return; //종료(밑에꺼까지 실행안됨!)
      }
      axios.delete("/api/inquiry/DetailPage" ,{
        params: {
          inq_id: this.$route.params.id,
         }
      })
      .then(res=>{
        console.log(this.$store.state.user.userId)
        // console.log(res.data)
        // alert("삭제하시겠습니까?")
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
