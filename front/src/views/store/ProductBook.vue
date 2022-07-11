<template>


  <div class="book_info">
    <v-card width="100%"
            elevation="10"
            style="padding:30px"
    >
      <v-row>
        <v-col>
          <h3>예약자 이름</h3>
          <v-text-field
            label="체크인 시 필요한 정보입니다."
            solo
            :value="this.$store.state.user.userId"
          />
          <h3>휴대폰 번호</h3>
          <v-text-field
            label="체크인 시 필요한 정보입니다."
            solo
            :value="this.$store.state.user.user_phone"
          />
          <h3>숙소 이름</h3>
          <v-text-field
            solo
            :value="products.title"
            readonly
            style="font-size: 20px"
          />

          <h3>객실 타입</h3>
          <v-text-field
            solo
            :value="products.pd_name"
            readonly
            style="font-size: 20px"
          />

          <h3>가격</h3>
          <v-text-field
            solo
            :value="products.pd_price"
            readonly
            style="font-size: 20px"
          />

          <h3>방번호</h3>
          <v-text-field
            solo
            :value="room"
            readonly
            style="font-size: 20px"
          />
<!--          <v-select-->
<!--            v-model="room"-->
<!--            :items="roomItems"-->
<!--            item-text="text"-->
<!--            :value="lists.room"-->
<!--            @change="picRoom"-->
<!--            style="width:50%"-->
<!--          />-->
          <h3>체크인</h3>
          <input
            v-model="st_date"
            name="guideIntro"
            type="date"
            class="intro"
            data-placeholder="시작날짜"
            required
            aria-required="true">
          <h3>체크아웃</h3>
          <input
            v-model="end_date"
            name="guideIntro"
            type="date"
            class="intro"
            data-placeholder="시작날짜"
            required
            aria-required="true"/>
          <br><br><br>
          <v-btn @click="book" style="width: 50%; height: 50px;" color="#139DF2">
            결제하기
          </v-btn>
        </v-col>
        <v-col cols="6">
          <v-img
            :src="products.firstimage"
          />
        </v-col>
      </v-row>
    </v-card>
  </div>
</template>

<script>
import axios from "axios";

export default {
  name: "ProductBook",

  data: () => ({
    name: '',
    phone: '',
    sDate: '',
    productName: '',
    productPrice: '',
    productId: '',
    pay_price: '',
    user_id: '',

    st_date: '',
    end_date: '',

    copy: [],

    lists: [],
    room: '101',
    roomItems: [
      {text: '101호', value: '101'},
    ],

    roomBookList: {},

    productTime: [],

    products: [],


  }),
  mounted() {
    axios({
      method: 'GET',
      url: '/api/bookPdId',
      params: {'pd_id': this.$route.params.pd_id}
    })
    .then((res)=>{
      this.products = res.data
      console.log(this.products)
    })

    console.log(this.$route.params.pd_id)

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
      params: {'pd_id': this.$route.params.pd_id}
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
      axios({
        method: 'GET',
        url: '/api/productNoBook',
        params: {
          'pd_id': this.$route.params.pd_id,
          'st_date': this.st_date,
          'end_date': this.end_date
        }
      })
        .then((res) => {

          if (res.data.length !== 0) {
            let alertMessage = ""
            res.data.forEach(it => {
              alertMessage += `${it.date}에 ${it.room_num}호 \n`
            })
            alertMessage += "때문에 예약이 불가능 합니다"
            alert(alertMessage)

          } else {
            var IMP = window.IMP;
            IMP.init('imp19569487');
            console.log(this.lists)
            IMP.request_pay({
              pg: "html5_inics",
              pay_method: "card",
              merchant_uid: "iamport_test_id" + new Date().getTime(),
              name: this.products.title,
              amount: this.products.pd_price,
              buyer_email: "testiamport@naver.com",
              buyer_name: this.$store.state.user.userId,
              buyer_tel: "01012341234"
            }, rsp => {
              console.log(rsp);


              if (rsp.success) {

                const sendform = new FormData();

                sendform.append('pay_price', this.products.pd_price)
                sendform.append('user_id', this.$store.state.user.userId)

                axios({
                  method: 'POST',
                  url: '/api/productPost',
                  headers: {
                    'Content-Type': 'application/json',
                  },
                  data: JSON.stringify(bookInfo),
                  params: {
                    'user_id': this.$store.state.user.userId,
                    'pay_price': this.products.pd_price
                  }
                })
                  .then((res) => {
                    alert("예약이 완료되었습니다.")
                    this.$router.push('/productBookView')
                  })
                  .catch((err) => {
                    alert("결제 실패하였습니다.")
                    console.log(err)
                  })
                console.log(rsp)
              }
            })
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
          }
        })
    },
  }
}
</script>
<style scoped>
.book_info {
  position: relative;

  width: 10px;
  top: 50px;
}

h3 {
  margin-bottom: 15px;
}

.v-text-field.v-text-field--enclosed {
  width: 90%
}
</style>
