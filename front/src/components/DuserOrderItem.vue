<template>
  <div style="border: 2px solid black; height: 200px; margin: 0 10px; padding: 20px 15px;">
    <div id="orderItem">
      <div >
        <div
          class="short_addr"
        >
          {{ start_addr_short }}
        </div>
        <div
          class="order_time_info"
        >
          {{ order.entrust_time.slice(11, 16) }}
        </div>
      </div>
      <div>
        <v-icon>
          mdi-arrow-right-thin
        </v-icon>
      </div>

      <div>
        <div
          class="short_addr"
        >
          {{ end_addr_short }}
        </div>
        <div
          class="order_time_info"
        >
          {{ order.withdraw_time.slice(11, 16) }}
        </div>
      </div>

      <div>
        <div
          v-for="(i, index) in ord_bag_info"
          :key="index"
        >
          {{ i['BAG_SIZE'] }}{{ i['CNT'] }}
        </div>
      </div>
    </div>
  </div>
  <!-- 출발키퍼 주소 --> <!-- 거리 --> <!-- 도착키퍼 주소 -->    <!-- 운송품에 대한 간략한 정보 -->
  <!-- 운송원과 출발키퍼사이의 거리 -->                         <!-- 금액 -->

  <!-- 최소 도착시간(주문서.찾을시간) -->
  <!-- 예상 도착시간 (필요한가) -->

  <!--      ord_id:           {{ order.ord_id }} <br>-->
  <!--      user_id:          {{ order.user_id }} <br>-->
  <!--      ord_amount:       {{ order.ord_amount }} <br>-->
  <!--      entrust_time:     {{ order.entrust_time }} <br>-->
  <!--      withdraw_time:    {{ order.withdraw_time }} <br>-->
  <!--      keep_start:       {{ order.keep_start }} <br>-->
  <!--      keep_end:         {{ order.keep_end }} <br>-->
  <!--      delivery_id:      {{ order.delivery_id }} <br>-->
  <!--      call_time:        {{ order.call_time }} <br>-->
  <!--      status:           {{ order.status }} <br>-->
  <!--      pay_id:           {{ order.pay_id }} <br>-->
  <!--      ord_selection:    {{ order.ord_selection }} <br>-->
</template>

<script>
import axios from "axios";


export default {
  name: 'DuserOrderItem',
  props:{
    order: { type: Object },

  },
  data() {
    return {
      keep_start: Object,
      keep_end: Object,
      ord_bag_info: Object
    }
  },
  computed: {
    start_addr_short: function () {
      return (this.keep_start.addr1 || "").split(' ')[2]
    },
    end_addr_short: function () {
      return (this.keep_end.addr1 || "").split(' ')[2]
    },

  },
  mounted() {
    if(this.order.keep_start !== null)
      axios({
        method: 'GET',
        url: 'api/keep/find',
        params: {
          keep_id: this.order.keep_start
        }
      }).then(res => {
        this.keep_start = res.data

        this.addr_short = this.keep_start.addr1.split(' ')[2]

      })

    if(this.order.keep_end !== null)
      axios({
        method: 'GET',
        url: 'api/keep/find',
        params: {
          keep_id: this.order.keep_end
        }
      }).then(res => {
        this.keep_end = res.data

      })

    axios({
      method: 'GET',
      url: 'api/orders/baginfo',
      params: {
        ord_id: this.order.ord_id
      }
    }).then(res => {
      this.ord_bag_info = res.data
    })
  },
  methods: {

  }
};
</script>

<style>
.order_time_info{
  font-size: larger;
}
.short_addr{
  font-size: x-large;
}
#orderItem>div{
  float: left;
  width: 25%;
  text-align: center;
}
.order_item_info{
  float: left;
  width: 26.6%;
}
#gnb {
  display: flex;
  justify-content: center;
}
.gnb_stop {
  z-index: 300;
  position: fixed;
  top: 0;
  left: 0;
  width: 100%;
  height: 67px;
  background-color: white;
}

.login-box {
  display: block;
  /*justify-content: flex-end;*/
  float: right;
}

.menu-Bar {
  display: flex;
  justify-content: center;

}

/*.text-center {*/
/*  margin-top: 20px;*/
/*  display: flex;*/
/*  justify-content: center;*/
/*}*/

/*.hotel-list-form {*/
/*  display: flex;*/
/*  align-items: center;*/
/*}*/

/*.select-box {*/
/*  padding: 0 2%;*/
/*}*/

/*.select-size {*/
/*  width: 50%;*/
/*}*/

/*.divider-padding {*/
/*  margin-top: 10px;*/
/*}*/
</style>

