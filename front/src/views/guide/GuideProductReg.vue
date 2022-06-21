<template>
  <form class="joinGItem">
    <h2>가이드 상품 등록</h2>
    <h1>{{ $store.state.user.userId }}</h1>
      <search-place @childEvent="getEmitData" />

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
        v-model="lodging.pl_id"
        name="guideIntro"
        type="text"
        class="intro"
        placeholder="장소번호"
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
        v-model="st_date"
        name="guideIntro"
        type="date"
        class="intro"
        data-placeholder="시작날짜"
        required
        aria-required="true"
      >
    </div>
    <div class="textForm">
      <input
        v-model="end_date"
        name="guideIntro"
        type="date"
        class="intro"
        data-placeholder="종료날짜"
        required
        aria-required="true"
      >
    </div>
    <div class="textForm">
      <input
        v-model="require_time"
        name="guideIntro"
        type="text"
        class="intro"
        placeholder="요구시간"
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
import SearchPlace from "@/components/SearchPlace";

export default {
  name: 'GuideProductReg',
  components: {
    SearchPlace
  },
  data(){
    return{
      lodging:'',
      gitem_id:'',
      user_id:'',
      pl_id:'',
      introduce:'',
      st_date:'',
      require_time:'',
      end_date:''
    }
  },
  props:[],
  methods:{
    getEmitData: function (lodging) {
      this.lodging = lodging
      console.log("받은데이터" + lodging)
    },
    onsubmit(){
      if (this.$store.state.user.userId===''){
        alert("로그인후 이용해주세요")
      }
      var sendform= new FormData();

      sendform.append('gitem_id', this.gitem_id);
      sendform.append('user_id', this.$store.state.user.userId);
      sendform.append('pl_id', this.lodging.pl_id);
      sendform.append('introduce', this.introduce);
      sendform.append('st_date', this.st_date);
      sendform.append('require_time', this.require_time);
      sendform.append('end_date', this.end_date);




      axios({
        method: 'post',
        url: '/api/gijoin',
        headers: {
          'Content-Type': 'multipart/form-data',
        },
        data: sendform,
      }).then((res) => {
        console.log(res)
        if (res.data === '') {
          alert('가입성공');

        }else{
          alert('가입실패');
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
input[type="date"]::before {content:attr(data-placeholder);width:100%}
input[type="date"]:focus::before,
input[type="date"]:valid::before {display:none}
</style>
