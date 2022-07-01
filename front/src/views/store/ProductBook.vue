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
        <v-btn @click="book">
          예약하기
        </v-btn>
      </v-row>
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

    productTime: [],

  }),
  mounted() {
    console.log(this.product)
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

    ex(){

    },


    book(){
      alert("예약이 완료 되었습니다.")
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

      axios({
        method: 'POST',
        url: '/api/productPost',
        data:{},
        params: {'product_time_num': this.productTime[0].product_time_num, 'user_id' : this.$store.state.user.userId,
          'pay_price' : this.product.pd_price
        }
      })
        .then((res) => {

        })
        .catch((err) => {
          console.log(err)
        })
    }
  },
}

</script>

<style scoped>

</style>
