<<<<<<< Updated upstream
<template>
  <form class="joinGItem">
    <h2>가이드 상품 등록</h2>
    <div class="textForm">
      <input
        v-model="gitem_id"
        name="userId"
        type="text"
        class="id"
        placeholder="상품 아이디"
      >
    </div>

    <div class="textForm">
      <input
        v-model="user_id"
        name="guideLicense"
        type="text"
        class="license"
        placeholder="아이디"
      >
    </div>
    <div class="textForm">
      <input
        v-model="pl_id"
        name="guideIntro"
        type="text"
        class="intro"
        placeholder="장소 번호"
      >
    </div>
    <div class="textForm">
      <input
        v-model="introduce"
        name="guideIntro"
        type="text"
        class="intro"
        placeholder="소개"
      >
    </div>
    <div class="textForm">
      <input
        v-model="usage_time"
        name="guideIntro"
        type="datetime-local"
        class="intro"
        placeholder="날짜"
      >
    </div>
    <div class="textForm">
      <input
        v-model="require_time"
        name="guideIntro"
        type="text"
        class="intro"
        placeholder="시간"
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
  name: 'GuideProductReg',
  components: {
  },
  data(){
    return{
      gitem_id:'',
      user_id:'',
      pl_id:'',
      introduce:'',
      usage_time:'',
      require_time:''
    }
  },
  methods:{
    onsubmit(){
      var sendform= new FormData();

      sendform.append('gitem_id', this.gitem_id);
      sendform.append('user_id', this.user_id);
      sendform.append('pl_id', this.pl_id);
      sendform.append('introduce', this.introduce);
      sendform.append('usage_time', this.usage_time);
      sendform.append('require_time', this.require_time);

      if(this.user_id != '')
        alert('가이드에 없는 아이디입니다.')


      axios({
        method: 'post',
        url: '/api/gijoin',
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



.joinGItem {

  width:400px;
  height:40px;
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
=======
>>>>>>> Stashed changes
