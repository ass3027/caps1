<template>
  <div
    id="app"
  >
    <div class="head_aticle">
      <h1 class="tit">
        상품 후기
      </h1>
    </div>
    <ul class="tab_menu">
      <li id="reviewBefore"><a @click="change('reviewBefore')">작성가능 후기 <span>({{ paymentList.length }})</span></a></li>
      <li id="reviewAfter"><a @click="change('reviewAfter')">작성완료 후기 <span>({{  }})</span></a></li>
    </ul>
    <template v-if="status === 'reviewBefore'">

      <ul class="list_payment">

        <!--        가이드 결제내역        -->
        <h2 style="border-bottom: 2px solid #333">가이드</h2>
        <li v-for="(payment, index) in guidePaymentList" :key="index">
          <PaymentItem :payment="payment" :type="'guide'"/>
        </li>


        <!--        호텔 결제내역        -->
        <h2 style="border-bottom: 2px solid #333; padding-top: 30px">호텔</h2>
        <li v-for="(payment, index) in hotelPaymentList" :key="index">
          <PaymentItem :payment="payment" :type="'hotel'"/>
        </li>

      </ul>
    </template>
    <template v-else>
      <ul class="list_payment">


        <div v-for="(review, index) in reviews" :key="index">
          {{review}}
        </div>

        <!--        가이드 결제내역        -->
        <h2 style="border-bottom: 2px solid #333">가이드</h2>


      </ul>
    </template>

  </div>
  <!--  </div>-->
</template>
<script>
import axios from "axios";
import PaymentItem from "@/components/auth/PaymentItem";


export default {
  components: {
    PaymentItem,
  },
  data() {
    return {
      paymentList: [],
      hotelPaymentList: [],
      reviews: [],
      status: 'reviewBefore'
    };
  },
  computed: {
    guidePaymentList() {
      return this.paymentList.filter(it => {
        if (it.gtime_num !== null) return true
      })
    },


  },
  mounted() {
    axios.get('/api/payment/paymentList').then(res => {
      console.log('paymentList', res.data)
      this.paymentList = res.data
    })

    axios.get('/api/payment/paymentList/hotel').then(res => {
      console.log('hotelPaymentList', res.data)
      this.hotelPaymentList = res.data
    })

    axios.get('/api/user/review').then(res => {
      console.log('review', res.data)
      this.reviews = res.data
    })



    const selected = document.getElementById('reviewBefore');

    selected.style.borderBottom = '2px solid #333'
    selected.style.color = '#333'
    selected.style.fontWeight = '700'
  },
  methods: {
    gitemInfo(pay_id) {
      console.log('test', pay_id)
      let gitem
      axios.get('/api/payment/gitem/' + pay_id).then(res => {
        return res.data
      })

    },

    change(status) {

      console.log('changeStatus, status:', status)

      this.status = status

      const statusList = ['reviewBefore', 'reviewAfter'];

      statusList.forEach(it => {
        if(it === status) {
          const selected = document.getElementById(it);

          selected.style.borderBottom = '2px solid #333'
          selected.style.color = '#333'
          selected.style.fontWeight = '700'
        } else {
          const selected_else = document.getElementById(it);

          selected_else.style.borderBottom = 'none'
          selected_else.style.color = 'none'
          selected_else.style.fontWeight = '0'
        }
      })





    }
  }
}
</script>

<style scoped>
.list_payment {
  padding-top: 10px;
  padding-left: 0;
  width: 95%;
  margin: 0 auto;
}

.tit {
  height: 36px;
  font-weight: 700;
  font-size: 24px;
  line-height: 36px;
  color: #333;
  letter-spacing: -.5px;
}

.head_aticle {
  padding: 5px 0 34px;
}

.container {
  text-align: center;
  position: center;

}

header {
  position: center;
  top: 0;
  left: 0;
  right: 0;
  height: 75px;
  padding: 1rem;
  color: white;
  background: #147ed0;
  font-weight: bold;
  display: flex;
  justify-content: space-between;
  align-items: center;
}

/** {*/
/*  position: center;*/
/*  margin: 0px;*/
/*  padding: 0px;*/
/*  text-decoration: none;*/
/*  font-family:sans-serif;*/
/*}*/


.box {
  text-align: center;
  position: center;
  font-size: 20px;
  /*border-bottom: 2px solid #adadad;*/
  margin: 20px;
  padding: 0px 10px;
}


.head_aticle {
  padding: 5px 0 34px;
}

.tit {
  height: 36px;
  font-weight: 700;
  font-size: 24px;
  line-height: 36px;
  color: #333;
  letter-spacing: -.5px;
}

li {
  list-style: none;
}

.payment_goods {
  position: relative;
  margin-top: 10px;
  padding: 0 20px;
  border: 1px solid #dddfe1;
}

.name {
  padding: 20px 0 13px;
  border-bottom: 1px solid #dddfe1;
}

.tab_menu {
  border-top: 2px solid #333;
  overflow: hidden;
  padding-top: 30px;
  padding-left: 0px;
  padding-bottom: 30px;
}

.tab_menu li {
  float: left;
  width: 50%;
  height: 50px;
  background-color: #fff;
}

.tab_menu a {
  display: block;
  overflow: hidden;
  width: 100%;
  height: 100%;
  border: 1px solid #dddfe1;
  font-size: 14px;
  color: #666;
  line-height: 44px;
  text-align: center;
}



.tab_menu li:last-child a {
  border-left: 0;
}


</style>
