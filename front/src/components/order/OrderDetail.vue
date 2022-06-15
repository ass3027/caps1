<template>
  <table style="display:table; border: 2px solid black; height: 200px; margin: 0 10px; padding: 20px 15px; width: 35%; margin: 0 auto">
    <div style="margin-top: 10px">
      <p style="display: inline; font-size: large; margin-left: 5px">
        배송경로
      </p>
      <div style="display: inline; float: right; font-size: large; margin-right: 5px">
        주문번호 #{{ order.ord_id }}
      </div>
    </div>
    <div
      id="map"
      style="width:100%;height:350px;"
    />
    <!--    <div id="order_info">-->
    <!--      <div>금액 {{ order.ord_amount }} 원</div>-->
    <!--      <div>픽업/완료 {{ entrust_time }}     {{ withdraw_time }}</div>-->
    <!--      <div>출발지 {{ keep_start.addr1 }}</div>-->
    <!--      <div>{{ degree_start_end }}Km</div>-->
    <!--      <div>도착지 {{ keep_end.addr1 }}</div>-->
    <!--      <div>나로부터</div>-->
    <!--      <div>물품정보</div>-->
    <!--      <div>요청사항</div>-->
    <!--    </div>-->
    <table>
      <thead scope="row">
        <tr>

        </tr>
      </thead>
      <tbody>
        <tr>
          <th>금액</th>
          <td>{{ order.ord_amount }} 원</td>
        </tr>
        <tr>
          <th>픽업/완료</th>
          <td>
            <div>
              {{ entrust_time }}
            </div>
            <div>
              {{ withdraw_time }}
            </div>
          </td>
        </tr>
        <tr>
          <th>출발지</th>
          <td>{{ keep_start.addr1 }}</td>
        </tr>
        <tr>
          <th>도착지</th>
          <td>{{ keep_end.addr1 }}</td>
        </tr>
        <tr>
          <th>거리</th>
          <td>{{ degree_start_end }}Km</td>
        </tr>
        <tr>
          <th>나로부터</th>
          <td>{{ degree_user_start }}Km</td>
        </tr>
        <tr>
          <th>물품정보</th>
          <td></td>
        </tr>
        <tr>
          <th>요청사항</th>
          <td></td>
        </tr>
      </tbody>
    </table>
  </table>
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
  name: 'OrderDetail',
  props:{
    ordId: { type: String },
  },
  data() {
    return {
      order: Object,
      keep_start: Object,
      keep_end: Object,
      ord_bag_info: Object,
      map: null,
      degree_start_end: null,
      userId: this.$store.state.user.userId,
      user_lng: null,
      user_lat: null
    }
  },
  computed: {
    entrust_time: function(){
      var text = this.order.entrust_time || ''
      var result = text.substring(5,7) + "/" +text.substring(8,10) + " " + text.substring(11, 16)
      // var result = this.order.entrust_time
      return result
    },
    withdraw_time: function () {
      var text = this.order.withdraw_time || ''
      var result = text.substring(5,7) + "/" +text.substring(8,10) + " " + text.substring(11, 16)
      // var result = this.order.withdraw_time
      return result
    },
    degree_user_start: function() {
      var a = this.getDistanceFromLatLonInKm(this.keep_start.mapy, this.keep_start.mapx, this.user_lat, this.user_lng)
      return a.toFixed(2) // 자릿수 반올림
    }
  },
  created() {
    axios.get("/api/location/check?duser_id=" + this.userId).then(res => {

      var location = Object.keys(res.data).map(function(key) {
        return res.data[key];
      });

      this.user_lng = location[0]
      this.user_lat = location[1]
    })

    axios({
      method: 'GET',
      url: '/api/orders/order/',
      params: {
        ord_id: this.ordId
      }
    }).then(res => {
      this.order = res.data
      console.log("[api/orders/order/]: ")
      console.log(this.order)

      axios.get("/api/keep/find?keep_id=" + this.order.keep_start).then(res => { this.keep_start = res.data }).then(() => {
        axios.get("/api/keep/find?keep_id=" + this.order.keep_end).then(res => { this.keep_end = res.data }).then(() => {
          if(window.kakao && window.kakao.maps) {
            this.initMap();
          } else {
            const script = document.createElement("script");

            script.onload = () => kakao.maps.load(this.initMap);
            script.src =
              "//dapi.kakao.com/v2/maps/sdk.js?autoload=false&appkey=915cffed372954b7b44804ed422b9cf0";
            document.head.appendChild(script);
          }
        })
      })
    })
  },
  mounted() {

  },
  methods: {
    initMap() {
      console.log("[initMap()]")

      const mapContainer = document.getElementById("map");

      var center_x = (this.keep_start.mapx + this.keep_end.mapx)/2
      var center_y = (this.keep_start.mapy + this.keep_end.mapy)/2

      console.log(center_x + " " + center_y)
      const mapOptions = {
        center: new kakao.maps.LatLng(center_y, center_x),
        level: 8,
        marker: new kakao.maps.LatLng(center_y, center_x)
      };

      this.map = new kakao.maps.Map(mapContainer, mapOptions)

      var start_marker = new kakao.maps.InfoWindow({
        position: new kakao.maps.LatLng(this.keep_start.mapy, this.keep_start.mapx),
        content:  '출발'
      });
      var end_marker = new kakao.maps.InfoWindow({
        position: new kakao.maps.LatLng(this.keep_end.mapy, this.keep_end.mapx),
        content: '도착'
      });

      end_marker.setMap(this.map)
      start_marker.setMap(this.map)
      var a = this.getDistanceFromLatLonInKm(this.keep_start.mapy, this.keep_start.mapx, this.keep_end.mapy, this.keep_end.mapx)
      this.degree_start_end = a.toFixed(2) // 자릿수 반올림
    },

    // longitude 경도(3자리), latitude 위도(2자리)
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
    }

  }
};
</script>

<style>

body {
  padding:1.5em;
  background: #f5f5f5
}

td div {
  display: inline-block;
  width: 50%;
}

table {
  border: 1px #a39485 solid;
  font-size: .9em;
  box-shadow: 0 2px 5px rgba(0,0,0,.25);
  width: 100%;
  border-collapse: collapse;
  border-radius: 5px;
  overflow: hidden;
}

th {
  text-align: left;


}


td, th {
  padding: 1em .5em;
  vertical-align: middle;
}

td {
  border-bottom: 1px solid rgba(0,0,0,.1);
  background: #fff;
}

a {
  color: #73685d;
}

@media all and (max-width: 768px) {

  table, thead, tbody, th, td, tr {
    display: block;
  }

  th {
    text-align: right;
  }

  table {
    position: relative;
    padding-bottom: 0;
    border: none;
    box-shadow: 0 0 10px rgba(0,0,0,.2);
  }

  tbody {
    overflow-x: auto;
    overflow-y: hidden;
    position: relative;
    white-space: nowrap;
  }

  tr {
    display: inline-block;
    vertical-align: top;
  }

  th {
    border-bottom: 1px solid #a39485;
  }

  td {
    border-bottom: 1px solid #e5e5e5;
  }


}
#order_info {
  margin-top: 15px;
  border: 1px solid black;

}
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

