<template>
  <div
    id="app"
  >
    <div class="head_aticle">
      <h1 class="tit">
        결제내역
      </h1>
    </div>

    <ul class="list_payment">
      <li v-for="payment in guidePaymentList" :key="payment.pay_id">
        <div class="date">{{ payment.pay_time }}</div>
        <div class="payment_goods">
          <div class="name"><a>{{ gitemInfo(payment.pay_id) }}</a></div>
          <div class="payment_info"></div>
          <div class="payment_status"></div>

          {{ payment }}
        </div>
      </li>
    </ul>
  </div>
  <!--  </div>-->
</template>
<script>
import axios from "axios";

export default {
  components: {},
  data() {
    return {
      paymentList: [],
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
  },
  methods: {
    gitemInfo(pay_id) {
      console.log('test', pay_id)
      let gitem
      axios.get('/api/payment/gitem/' + pay_id).then(res => {
        return res.data
      })

    }
  }
}
</script>

<style scoped>
.list_payment {
  padding-top: 10px;
  border-top: 2px solid #333;
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

</style>
