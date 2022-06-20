<template>
  <div>
    <h1>게시판 수정하기</h1>

    <v-col
      class="d-flex"
      cols="12"
      sm="10"
    />
    <v-select
      v-model="post.inq_type"
      :items="items"
      label="분류"
      outlined
    />

    <v-text-field
      v-model="post.user_id"
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
    <div id="button">
      <v-btn elevation="3" type="button" @click="Modify">수정</v-btn>
    </div>
  </div>
</template>

<script>
import axios from "axios";

export default {
  name: "WritingModView",
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

      })
  },
  methods:{
    Modify(){
      var sendform = new FormData()
      sendform.append("inq_type", this.post.inq_type)
      sendform.append("user_id", this.post.user_id)
      sendform.append("inq_title", this.post.inq_title)
      sendform.append("inq_body", this.post.inq_body)
      sendform.append("inq_id",this.$route.params.id)

      axios({
        method:'post',
        url:'/api/inquiry/WritingMod',
        headers:{
          "Content-Type" : 'multipart/form-data', //사진같은 다양한 파일을 올릴때
        },
        data:sendform,
      })
      .then((res) => {
        if (res.data === true) {
          alert("수정이 완료되었습니다");
          this.$router.push("/Questions")
        } else {
          alert('제목을 적어주세요')
        }
      })
    }
  }

}
</script>

<style scoped>

</style>
