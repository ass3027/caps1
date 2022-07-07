<template>
  <v-card
    class="mx-auto my-12"
    max-width="374"
  >
    <v-card-title>USERID:{{ lists.user_id }}</v-card-title>

    <v-card-text>
      <v-row
        align="center"
        class="mx-0"
      >
        <div class="grey--text ms-4">
          ID:{{ lists.gitem_id }}
        </div>
      </v-row>

      <div class="my-4 text-subtitle-1">
        장소:{{ lists.title }}
      </div>

      <div>소개:{{ lists.introduce }}</div>
      <v-card-title>가격:{{ lists.gitem_price }}</v-card-title>
    </v-card-text>

    <v-divider class="mx-4"/>

    <v-card-title>예약시간</v-card-title>
    <v-date-picker
      v-model="gdate"
      @click:date="dateClick"

    >

    </v-date-picker>
    {{ gdate }}

    <v-card-text>
      <v-chip-group
        v-for="(item,index) in items"
        :key="index"
        active-class="deep-purple accent-4 white--text"
        column
      >
        <v-chip
          v-if="item.book_whether==1"
          @click="num(item.time_num)"
        >
          {{ item.st_time }} ~ {{ item.end_time }}
        </v-chip>
      </v-chip-group>
    </v-card-text>

    <v-card-actions>
      <v-btn
        color="deep-purple lighten-2"
        text
        @click="reserve()"
      >
        Reserve
      </v-btn>
    </v-card-actions>
    <ReviewView :id="gitem_id" :type="'가이드 상품'"/>
  </v-card>
</template>

<script>
import axios from "axios";
import ReviewView from "@/views/ReviewView";

export default {
  name: "GuideProductView.vue",
  components: {
    ReviewView
  },
  props: ['gitem_id'],
  data() {
    return {
      lists: '',
      items: [],
      selectednum: '',
      user_id: '',
      pay_price: '',
      gdate: '',
    }
  },
  computed() {
  },
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
  methods: {
    dateClick() {
      console.log("DDDD")
      axios({
        method: 'get',
        url: '/api/gtime/' + this.gitem_id,
        params: {
          'time': this.gdate
        }
      })
        .then((res) => {
          this.items = res.data;
          console.log('시간상세보기', res.data)
        })
    },

    importGuide() {
      axios({
        method: 'get',
        url: `/api/gitemview/` + this.gitem_id,
      })
        .then((res) => {
          this.lists = res.data;
          console.log(res.data + '상품상세보기')
        })
    },
    reserve() {
      if(this.$store.state.user.userId=='anonymousUser'||this.$store.state.user.userId==''){
        alert('로그인 후 이용가능합니다')
        return
      }
      var IMP = window.IMP;
      IMP.init('imp19569487');
      console.log(this.lists)
      IMP.request_pay({
        pg: "html5_inics",
        pay_method: "card",
        merchant_uid: "iamport_test_id" + new Date().getTime(),
        name: this.lists.title,
        amount: this.lists.gitem_price,
        buyer_email: "testiamport@naver.com",
        buyer_name: this.$store.state.user.userId,
        buyer_tel: "01012341234"
      }, rsp => {
        console.log(rsp);
        if (rsp.success) {

          axios({
            method: 'put',
            url: '/api/gitemTimeUpdate/',
            params: {
              'id': this.selectednum
            }
          })
            .then(() => {
              console.log("O")
              // this.timeGitem()
            })
          console.log(rsp)

          const sendform = new FormData();

          sendform.append('pay_price', this.lists.gitem_price)
          sendform.append('user_id', this.$store.state.user.userId)
          sendform.append('gtime_num', this.selectednum)

          axios({
            method: 'post',
            url: '/api/gPayInsert',
            data: sendform,
          }).then((res) => {
            console.log(res + 'success')
          })

        } else {
          alert("실패")
        }
      })
    },

    num(selectednum) {
      this.selectednum = selectednum
      console.log(this.selectednum)
    },

    // timeGitem(){
    //   axios({
    //     method:'get',
    //     url:'/api/gtime/' + this.gitem_id,
    //     params: {
    //       'time': this.gdate
    //     }
    //   })
    //   .then((res)=>{
    //     this.items = res.data;
    //     console.log('시간상세보기', res.data)
    //   })
    //
    // },
  }
}
</script>

<style scoped>

</style>
