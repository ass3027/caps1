<template>
  <form class="joinGItem">
    <h2>가이드 시간 등록</h2>



    <div class="textForm">
      <input
        v-model="gitem_id"
        name="guideIntro"
        type="text"
        class="intro"
        placeholder="상품 아이디"
      >
    </div>
    <div class="textForm">
      <input
        v-model="st_time"
        name="guideIntro"
        type="time"
        class="intro"
        placeholder="시작시간"
      >
    </div>
    <div class="textForm">
      <input
        v-model="end_time"
        name="guideIntro"
        type="time"
        class="intro"
        data-placeholder="종료시간"
        required
        aria-required="true"
      >
    </div>
    <div class="textForm">
      <input
        v-model="book_whether"
        name="guideIntro"
        type="text"
        class="intro"
        data-placeholder="예약유무"
        required
        aria-required="true"
      >
    </div>

    <v-btn
      class="btn"
      @click="timeRegister()"
    >
      JOIN
    </v-btn>
  </form>
</template>

<script>
import axios from "axios";

export default {
  name:'GuideProductTime',
  data(){
    return{
      gitem_id:'',
      st_time:'',
      end_time:'',
      book_whether:'',
    }
  },
  methods:{

    timeRegister(){


      var sendform= new FormData();

      sendform.append('gitem_id',this.gitem_id);
      sendform.append('st_time', this.st_time);
      sendform.append('end_time', this.end_time);
      sendform.append('book_whether', this.book_whether);

      axios({
        method:'post',
        url:'/api/gitjoin',
        headers:{
          'Content-Type': 'multipart/form-data'
        },
        data: sendform,
      }).then((res)=>{
        console.log(res)
      })

    }
  }
}
</script>
