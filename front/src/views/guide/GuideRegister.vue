<template>
  <form class="joinGuide">
    <h2>가이드 등록</h2>
    <div class="textForm">
      <input
        v-model="user_id"
        name="userId"
        type="text"
        class="id"
        placeholder="아이디"
      >
    </div>

    <div class="textForm">
      <input
        v-model="guide_license"
        name="guideLicense"
        type="text"
        class="license"
        placeholder="자격증"
      >
    </div>
    <div class="textForm">
      <input
        v-model="guide_intro"
        name="guideIntro"
        type="text"
        class="intro"
        placeholder="간단한 소개"
      >
    </div>
    <v-btn
      class="btn"
      @click="onsubmit()"
    >
      JOIN
    </v-btn>
  </form>
</template>

<script>


import axios from "axios";

export default {
  name: 'GuideRegister',
  components: {
  },
  data(){
    return{
      user_id:'',
      guide_license:'',
      guide_intro:''
    }
  },
  methods:{
    onsubmit(){
      var sendform= new FormData();

      sendform.append('user_id', this.user_id);
      sendform.append('guide_license', this.guide_license);
      sendform.append('guide_intro', this.guide_intro);

      console.log(sendform.get('user_id'))

      axios({
        method: 'post',
        url: '/api/gjoin',
        headers: {
          'Content-Type': 'multipart/form-data',
        },
        data: sendform,
      }).then((res) => {
        if (res.data == 'ok') {
          alert('ok');
          window.location.href = '/';
        }
      });
    }
  }
}

</script>
<style>
* {
  margin: 0px;
  padding: 0px;
  text-decoration: none;
  font-family:sans-serif;


}



.joinGuide {
  position:absolute;
  width:400px;
  height:400px;
  padding: 30px;
  background-color:#FFFFFF;
  text-align:center;
  top:30%;
  left:50%;
  transform: translate(-50%,-50%);
  border-radius: 15px;
  position: relative;
}

.joinForm h2 {
  text-align: center;
  margin: 30px;
}

.textForm {
  border-bottom: 2px solid #adadad;
  margin: 30px;
  padding: 10px 10px;
}


.id {
  width: 100%;
  border:none;
  outline:none;
  color: #636e72;
  font-size:16px;
  height:25px;
  background: none;
}


.license {
  width: 100%;
  border:none;
  outline:none;
  color: #636e72;
  font-size:16px;
  height:25px;
  background: none;
}

.intro {
  width: 100%;
  border:none;
  outline:none;
  color: #636e72;
  font-size:16px;
  height:25px;
  background: none;
}

.btn {
  position:relative;
  left:40%;
  transform: translateX(-50%);
  margin-bottom: 40px;
  width:80%;
  height:40px;
  background: linear-gradient(125deg,#81ecec,#6c5ce7,#81ecec);
  background-position: left;
  background-size: 200%;
  color:white;
  font-weight: bold;
  border:none;
  cursor:pointer;
  transition: 0.4s;
  display:inline;
}

.btn:hover {
  background-position: right;
}
</style>