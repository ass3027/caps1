<template>
  <div style="height: 200px; margin: 0 10px; padding: 20px 15px;">
    <div id="orderItem">
      <div
        class="order_time_info"
        style="width: 80%; border-right: thin solid #ccc; padding-right: 20px;"
      >
        <div
          class="left_top"
          style="border-bottom: thin solid #ccc; border-style: dashed;
            border-top: none;
            border-left: none;
            border-right: none;
            padding-bottom: 50px;
          ">
          <div>
            <div
              class="short_addr"
            >
              {{ start_addr_short }}
            </div>
            <span>
              {{ order.entrust_time.slice(11, 16) }}
            </span>
          </div>
          <div>
            <v-icon>
              mdi-arrow-right-thin
            </v-icon>
          </div>
          <div>
            <div
              class="short_addr"
              style="vertical-align: top"

            >
              {{ end_addr_short }}
            </div>
            <span>
              {{ order.withdraw_time.slice(11, 16) }}
            </span>
          </div>
        </div>

        <div style="margin-top: 20px">
          <span style="font-size: large">
            나로부터 {{ degree_user_start }}Km 이내
          </span>
        </div>


      </div>
      <div style="width: 20%" class="bag_info">
        <div
          v-for="(i, index) in ord_bag_info"
          :key="index"
        >
          {{ i['BAG_SIZE'] }}{{ i['CNT'] }}
        </div>
        <span>
          {{ order.ord_amount }} 원
        </span>
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
    latitude: { type: Number },
    longitude: { type: Number }
  },
  data() {
    return {
      keep_start: Object,
      keep_end: Object,
      ord_bag_info: Object
    }
  },
  method:{
    getDistanceFromLatLonInKm(lat1,lng1,lat2,lng2) {
      function deg2rad(deg) {
        return deg * (Math.PI/180)
      }

      var R = 6371; // Radius of the earth in km
      var dLat = deg2rad(lat2-lat1);  // deg2rad below
      var dLon = deg2rad(lng2-lng1);
      var a = Math.sin(dLat/2) * Math.sin(dLat/2) + Math.cos(deg2rad(lat1)) * Math.cos(deg2rad(lat2)) * Math.sin(dLon/2) * Math.sin(dLon/2);
      var c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1-a));
      var d = R * c; // Distance in km
      return d;
    },
  },
  computed: {
    start_addr_short: function () {
      return (this.keep_start.addr1 || "").split(' ')[2]
    },
    end_addr_short: function () {
      return (this.keep_end.addr1 || "").split(' ')[2]
    },

    degree_user_start: function() {
      var getDistanceFromLatLonInKm = function(lat1, lng1, lat2, lng2){
        function deg2rad(deg) {
          return deg * (Math.PI / 180)
        }

        var R = 6371; // Radius of the earth in km
        var dLat = deg2rad(lat2 - lat1);  // deg2rad below
        var dLon = deg2rad(lng2 - lng1);
        var a = Math.sin(dLat / 2) * Math.sin(dLat / 2) + Math.cos(deg2rad(lat1)) * Math.cos(deg2rad(lat2)) * Math.sin(dLon / 2) * Math.sin(dLon / 2);
        var c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
        var d = R * c; // Distance in km
        return d;
      }

      var a = getDistanceFromLatLonInKm(this.keep_start.mapy, this.keep_start.mapx, this.latitude, this.longitude)
      return a.toFixed(2) // 자릿수 반올림
    }

  },
  mounted() {
    if(this.order.keep_start !== null)
      axios({
        method: 'GET',
        url: '/api/keep/find',
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
        url: '/api/keep/find',
        params: {
          keep_id: this.order.keep_end
        }
      }).then(res => {
        this.keep_end = res.data

      })

    axios({
      method: 'GET',
      url: '/api/orders/baginfo/' + this.order.ord_id
    }).then(res => {
      this.ord_bag_info = res.data
    })
  },
  methods: {

  }
};
</script>

<style>

.bag_info > {
  width: 100%;
}
.order_time_info{
  width: 25%;
}
.short_addr{
  font-size: large;
}
#orderItem>div{
  text-align: center;
  width: 100%;
}
.left_top > div{
  width: 33%;
  display: inline-block;
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

