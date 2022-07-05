<template>
  <v-container>
    <v-col>
      <v-row>
        <img
          :src="'/api/photo/' + product.pic_name"
          style="width: 50%"
        >
      </v-row>
      <v-row>
        숙소이름 : {{ product.title }}
      </v-row>
      <v-row>
        객실타입 : {{ product.pd_name }}
      </v-row>
      <v-row>
        체크인 : <input
        v-model="st_date"
        name="guideIntro"
        type="date"
        class="intro"
        data-placeholder="시작날짜"
        required
        aria-required="true"
      >
      </v-row>
      <v-row>
        체크아웃 : <input
        v-model="end_date"
        name="guideIntro"
        type="date"
        class="intro"
        data-placeholder="시작날짜"
        required
        aria-required="true"
      >
      </v-row>
      <v-row>
        <v-select
          v-model="room"
          :items="roomItems"
          item-text="text"
          :value="lists.room"
          @change="picRoom"
        />
      </v-row>
      <v-row>
        <v-btn @click="book">
          예약하기
        </v-btn>
      </v-row>
<!--      <v-card-actions>-->
<!--        <v-btn-->
<!--          color="deep-purple lighten-2"-->
<!--          text-->
<!--          @click="reserve()"-->
<!--        >-->
<!--          Reserve-->
<!--        </v-btn>-->
<!--      </v-card-actions>-->
    </v-col>
  </v-container>
</template>

<script>
import axios from "axios";

export default {
  name: "ProductBook",
  props: {
    product: Object,
  },
  data: () => ({
    name: '',
    phone: '',
    sDate: '',
    productName: '',
    productPrice: '',
    productId: '',

    st_date: '',
    end_date: '',

    copy: [],

    lists: [],
    room: '',
    roomItems: [
      {text: '101호', value: '101'},
    ],

    roomBookList: {},

    productTime: [],

  }),
  mounted() {
    this.importGuide()
    //this.timeGitem()
    const script = document.createElement("script")
    const script2 = document.createElement("script")
    script2.src = "https://code.jquery.com/jquery-3.6.0.min.js";
    script2.integrity = "sha256-/xUj+3OJU5yExlq6GSYGSHk7tPXikynS7ogEvDej/m4=";
    script2.setAttribute("crossOrigin", "anonymous");

    document.head.appendChild(script2);

    script.src = "https://cdn.iamport.kr/js/iamport.payment-1.2.0.js";
    script.type = "text/javascript"

    document.head.appendChild(script);
  },
  created() {
    axios({
      method: 'GET',
      url: '/api/productTime',
      params: {'pd_id': this.product.pd_id}
    })
      .then((res) => {
        this.productTime = res.data
        console.log(this.productTime)
      })
      .catch((err) => {
        console.log(err)
      })
  },
  methods: {
    productBook() {
      let BookInfo = {}
    },

    picRoom() {
      console.log(this.room)
    },

    ex() {
      // const tempDate = new Date(this.st_date)
      // const endDate = new Date(this.end_date)
      //
      // for (let i = 0; tempDate <= endDate; i++) {
      //   this.dateArr.push(this.dateFormat(tempDate))
      //   tempDate.setDate(tempDate.getDate() + 1)
      // }
      // console.log(this.dateArr)
      //
      // const PayBook = {};
      //
      // PayBook["pd_id"] =
    },

    dateFormat(date) {
      let month = date.getMonth() + 1;
      let day = date.getDate();
      let hour = date.getHours();
      let minute = date.getMinutes();
      let second = date.getSeconds();

      month = month >= 10 ? month : '0' + month;
      day = day >= 10 ? day : '0' + day;
      hour = hour >= 10 ? hour : '0' + hour;
      minute = minute >= 10 ? minute : '0' + minute;
      second = second >= 10 ? second : '0' + second;

      return date.getFullYear() + '-' + month + '-' + day;
    },


    book() {

      const dateArr = []

      const tempDate = new Date(this.st_date)
      const endDate = new Date(this.end_date)

      for (; tempDate <= endDate - 1;) {
        dateArr.push(this.dateFormat(tempDate))
        tempDate.setDate(tempDate.getDate() + 1)
      }

      console.log(dateArr)

      const bookInfo = []

      dateArr.forEach((it) => {

        const payBook = {};
        payBook["pd_id"] = this.productTime[0].pd_id
        payBook["date"] = it
        payBook["room_num"] = this.room

        bookInfo.push(payBook)

      })

      console.log(bookInfo)

      // axios({
      //   method: 'PUT',
      //   url: '/api/productPut',
      //   params: {'product_time_num': this.productTime[0].product_time_num}
      // })
      //   .then((res) => {
      //
      //   })
      //   .catch((err) => {
      //     console.log(err)
      //   })

      var IMP = window.IMP;
      IMP.init('imp19569487');
      console.log(this.lists)
      IMP.request_pay({
        pg:"html5_inics",
        pay_method: "card",
        merchant_uid:"iamport_test_id" + new Date().getTime(),
        name:this.lists.title,
        amount: this.lists.gitem_price,
        buyer_email:"testiamport@naver.com",
        buyer_name: this.$store.state.user.userId,
        buyer_tel:"01012341234"
      }, rsp =>{
        console.log(rsp);
        if (rsp.success){

          axios({
            method:'put',
            url:'/api/gitemTimeUpdate/',
            params:{
              'id': this.selectednum
            }
          })
            .then(()=>{
              console.log("O")
              // this.timeGitem()
            })
          console.log(rsp)

          const sendform = new FormData();

          sendform.append('pay_price', this.lists.gitem_price)
          sendform.append('user_id', this.$store.state.user.userId)
          sendform.append('gtime_num', this.selectednum)

          axios({
            method: 'POST',
            url: '/api/productPost',
            headers: {
              'Content-Type': 'application/json',
            },
            data: JSON.stringify(bookInfo),
          })
            .then((res) => {

            })
            .catch((err) => {
              console.log(err)
            })

          alert("예약이 완료 되었습니다.")

        } else{
          alert("실패")
        }
      })
    },
  }
}

</script>

<style scoped>

</style>
