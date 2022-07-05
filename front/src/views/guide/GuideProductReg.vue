<template>
  <v-container style="width: 70%">
    <v-row>
      <v-col align="center">
        <h2>가이드 상품 등록</h2>
        <h1>{{ $store.state.user.userId }}</h1>
        <div />
      </v-col>
    </v-row>
    <v-row style="width: 70%">
      <v-col
        align="center"
        style="padding-left: 45%"
      >
        <div class="textForm" style="margin-top: -70px">
          <div style="width: 50%; display: inline-block; vertical-align: bottom;">
            <input
                  v-model="lodging.pl_id"
                  name="guideIntro"
                  type="text"
                  class="intro"
                  placeholder="장소번호"
                >
          </div>

          <div style="width: 50%; display: inline-block;">
                <search-place-tour
                  @childEvent="getEmitData"
                  class="place"
                  style="margin: 50px 80px 5px;"
                >

                </search-place-tour>
              </div>

            </div>


        <div class="textForm">
          <input
            v-model="gname"
            name="guideIntro"
            type="text"
            class="intro"
            placeholder="상품이름"
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
        <div class="textForm">
          <input
            v-model="gitem_price"
            name="guideIntro"
            type="text"
            class="intro"
            placeholder="상품가격"
          >
        </div>

      </v-col>
    </v-row>

    <v-row
      style="padding-left: 195px"
    >
      <v-btn
        class="btn"
        @click="onsubmit()"
        style="margin: 30px"
        width="45%"
      >
        JOIN
      </v-btn>
    </v-row>
  </v-container>
</template>

<script>


import axios from "axios";
import SearchPlaceTour from "@/components/SearchPlaceTour";

export default {
  name: 'GuideProductReg',
  components: {
    SearchPlaceTour
  },
  props:[],
  data(){
    return{
      lodging:'',
      user_id:'',
      pl_id:'',
      introduce:'',
      st_date:'',
      require_time:'',
      end_date:'',
      gitem_price:'',
      gname:'',
    }
  },
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

      sendform.append('user_id', this.$store.state.user.userId);
      sendform.append('pl_id', this.lodging.pl_id);
      sendform.append('gname', this.gname);
      sendform.append('introduce', this.introduce);
      sendform.append('st_date', this.st_date);
      sendform.append('require_time', this.require_time);
      sendform.append('end_date', this.end_date);
      sendform.append('gitem_price', this.gitem_price);





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
          alert('상품등록');

        }else{
          alert('등록실패');
        }

      });

    }
  }
}

</script>
<style scoped>
* {

  text-decoration: none;
  font-family:sans-serif;


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
  width:50%;
  height:40px;
  background: linear-gradient(125deg,#81ecec,#6c5ce7,#81ecec);
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
