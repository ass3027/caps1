<template>
  <div>
    <h1>문의사항</h1>
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
      v-model="inq_title"
      type="text"
      label="제목"
    />

    <v-textarea
      v-model="inq_body"
      type="text"
      label="내용"
      maxlenght="5"
    />


    <v-row style="width:50%">
      :::첨부파일1 <v-file-input elevation="5">
        파일선택
      </v-file-input><br>
    </v-row>
    <v-row style="width:50%">
      :::첨부파일2 <v-file-input>파일선택</v-file-input>
    </v-row>

    <div id="button">
      <v-btn type="button" color="pink" @click="check">등록</v-btn>
      <v-btn type="button" @click="cancel">취소</v-btn>
    </div>

    <!--    <p>{{user_id}}</p>-->
  </div>
</template>

<script>

import axios from "axios";

export default {
  name: "WritingView",
  data(){
    return{
      inq_type: '',
      inq_title: '',
      inq_body: '',
      user_id:this.$store.state.user.userId,
      items:['여행시설문의','가방관리문의','가이드문의', '기타문의']
    }
  },
  mounted() {
    axios.get('/api/data/'+ this.$store.state.user.userId)
      .then(res=>{
        this.user_id = res.data.user_id
        console.log(res.data)

      }).catch((error)=>{
        this.error=error
    })
  },

  methods: {
    check() {
      console.log(this.user_id)
      if (!this.inq_title){
        alert("제목을 입력해 주세요");
        return;
      }

      var sendform = new FormData()
      console.log(this.user_id)
      sendform.append("inq_type", this.inq_type)
      sendform.append("user_id", this.user_id)
      sendform.append("inq_title", this.inq_title)
      sendform.append("inq_body", this.inq_body)
      console.log(sendform)
      // sendform.get("inq_type")

      // sendform.append("user_phone", this.user_phone)

      axios({
        method:'post',
        url: '/api/inquiry/Writing',
        headers:{
          "Content-Type" : 'multipart/form-data', //사진같은 다양한 파일을 올릴때
        },
        data:sendform,
      })
        .then((res) => {
          console.log(res.data)
          if (res.data === true) {
            alert('등록이 완료되었습니다..');
            this.$router.push('/')
          }
          else{
            alert('다시 확인해주세요')
          }
        })
    },
    cancel() {
      this.$router.push('/Questions' + '')
    },
    bookmark() {
      this.$router.push('/BookMark')
    },
    pay() {
    },
    myData() {
      this.$router.push('/MyData')
    },
    questions() {
      this.$router.push('/Questions')
    },
  }
}
</script>

<style scoped>
#button{
  position: center;
  text-align: center;
}
</style>

