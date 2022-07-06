<template>
  <v-container>
    <table v-if="item.ord_selection===`물품운송`"
           style="display:table; border: 2px solid black; height: 200px; padding: 20px 15px; width: 480px; margin: 0 auto; background-color: white">
      <div style="margin-top: 10px">
        <p style="display: inline; font-size: large; margin-left: 5px">
          배송경로
        </p>
        <div style="display: inline; float: right; font-size: large; margin-right: 5px">
          주문번호 #{{ order.ord_id }}
        </div>
      </div>
      <div v-if="item.ord_selection===`물품운송`"
           id="map"
           style="width:100%;height:350px;"
      />

      <table id="order_detail">
        <thead scope="row">
        <tr/>
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
        <!--        <tr>-->
        <!--          <th>나로부터</th>-->
        <!--          <td>{{ degree_user_start }}Km</td>-->
        <!--        </tr>-->
        <tr>
          <th>물품정보</th>
          <td>
            <span
              v-for="(i, index) in ord_bag_info"
              :key="index"
            >
              {{ i['BAG_SIZE'] }}x{{ i['CNT'] }}
              <span v-if="index !== ord_bag_info.length-1">, </span>
            </span>
          </td>
        </tr>
        <tr>
          <th>요청사항</th>
          <td>{{ item.ord_request }}</td>
        </tr>
        </tbody>
        <div>
          <v-btn @click="backTrackingView()">목록</v-btn>
        </div>

        <div v-if="order.status==='승인대기'">
          <v-btn
            class="btn_type2"
            height="54px"
            color="white"
            @click="deliveryCall()">승인완료</v-btn>
        </div>
      </table>

      <div v-if="order.status === '운송요청'">
        <v-btn
          class="btn_type2"
          height="54px"
          color="white"
          @click="requestMatch()"
        >
          매칭요청
        </v-btn>
      </div>
      <div v-if="order.status === '운송중'">
        <v-btn
          class="btn_type2"
          height="54px"
          color="white"
          @click="orderArrival()"
        >
          배송완료
        </v-btn>
      </div>
    </table>

    <table v-else
           style="display:table; border: 2px solid black; height: 200px; padding: 20px 15px; width: 480px; margin: 0 auto; background-color: white">
      <div style="margin-top: 10px">
        <p style="display: inline; font-size: large; margin-left: 5px">
          보관 상세조회
        </p>
        <div style="display: inline; float: right; font-size: large; margin-right: 5px">
          주문번호 #{{ item.ord_id }}
        </div>
      </div>

      <table>
        <thead scope="row">
        <tr/>
        </thead>
        <tbody>
        <tr>
          <th>금액</th>
          <td>{{ item.ord_amount }} 원</td>
        </tr>
        <tr>
          <th>보관시작시간/보관만료시간</th>
          <td>
            <div>
              {{ item.entrust_time }}
            </div>
            <div>
              {{ item.withdraw_time }}
            </div>
          </td>
        </tr>
        <tr>
          <th>물품보관장소</th>
          <td>{{ item.keep_start_title }}</td>
        </tr>

        <tr>
          <th>요청사항</th>
          <td>{{ item.ord_request }}</td>
        </tr>
        </tbody>
      </table>

      <div v-if="order.status==='보관요청'">
        <v-btn
          class="btn_type2"
          height="54px"
          color="white"
          @click="inStorage()">보관중으로 변경
        </v-btn>
      </div>

      <div>
        <v-btn
          class="btn_type2"
          height="54px"
          color="white"
          @click="backTrackingView()">목록
        </v-btn>
      </div>
    </table>
  </v-container>
</template>

<script>
import axios from "axios";


export default {
  name: 'KeeperOrderDetail',
  props: {
    order: {type: Object},
  },
  data() {
    return {
      keep_start: Object,
      keep_end: Object,
      ord_bag_info: null,
      map: null,
      degree_start_end: null,
      userId: this.$store.state.user.userId,
      user_lng: null,
      user_lat: null,
      item: '',
      plIdList: '',
    }
  },
  computed: {
    entrust_time: function () {
      var text = this.order.entrust_time || ''
      var result = text.substring(5, 7) + "/" + text.substring(8, 10) + " " + text.substring(11, 16)
      // var result = this.order.entrust_time
      return result
    },
    withdraw_time: function () {
      var text = this.order.withdraw_time || ''
      var result = text.substring(5, 7) + "/" + text.substring(8, 10) + " " + text.substring(11, 16)
      // var result = this.order.withdraw_time
      return result
    },
    // degree_user_start: function () {
    //   var a = this.getDistanceFromLatLonInKm(this.keep_start.mapy, this.keep_start.mapx, this.user_lat, this.user_lng)
    //   return a.toFixed(2) // 자릿수 반올림
    // }
  },
  created() {

    console.log('[OrderDetail]', this.order)

    axios.get("/api/location/check?duser_id=" + this.userId).then(res => {

      var location = Object.keys(res.data).map(function (key) {
        return res.data[key];
      });

      this.user_lng = location[0]
      this.user_lat = location[1]
    })


    axios.get("/api/keep/find/" + this.order.keep_start).then(res => {
      this.keep_start = res.data
    }).then(() => {
      axios.get("/api/keep/find/" + this.order.keep_end).then(res => {
        this.keep_end = res.data
      }).then(() => {
        if (window.kakao && window.kakao.maps) {
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

    axios.get("/api/orders/baginfo/" + this.order.ord_id).then(res => {
      this.ord_bag_info = res.data
    })


  },
  mounted() {
    this.trackingDetail()
    this.plId()
  },
  methods: {
    //장소IO 받아옴
    plId() {
      axios.get("/api/plId/")
        .then((res) => {
          this.plIdList = res.data;
          console.log("this.plId")
          console.log(this.plIdList)
        })
    },

    //물품 상세보기
    trackingDetail() {
      axios.get('/api/trackingDetail/' + this.order.ord_id)
        .then((res) => {
          this.item = res.data;
          console.log("this.item")
          console.log(this.item)
        })
    },

    //운송 승인완료 변경
    deliveryCall() {
      console.log("plidList ", this.plIdList)
      console.log("item_keep_start", this.item.keep_start)
      for (var i = 0; i < this.plIdList.length; i++) {
        if (this.plIdList[i] == this.item.keep_start) {
          axios.post('/api/deliveryStartCall/' + this.order.ord_id)
            .then((res) => {
              console.log('출발키퍼 상태완료')
              console.log(res.data)
            })
        } else if (this.plIdList[i] == this.item.keep_end) {
          axios.post('/api/deliveryEndCall/' + this.order.ord_id)
            .then((res) => {
              console.log('도착키퍼 상태완료')
              console.log(res.data)
            })
        }
        this.$router.push('/KeeperTrackingView')
      }

    },

    //보관 보관중으로 변경
    inStorage() {
      axios.post('/api/inStorage/' + this.order.ord_id)
        .then((res) => {
          console.log(res)
        })
      this.$router.push('/KeeperTrackingView');
    },
    //목록으로 돌아가기
    backTrackingView() {
      this.$router.push('/KeeperTrackingView')
    },

    initMap() {
      console.log("[initMap()]")

      const mapContainer = document.getElementById("map");

      var center_x = (this.keep_start.mapx + this.keep_end.mapx) / 2
      var center_y = (this.keep_start.mapy + this.keep_end.mapy) / 2

      console.log(center_x + " " + center_y)
      const mapOptions = {
        center: new kakao.maps.LatLng(center_y, center_x),
        level: 8,
        marker: new kakao.maps.LatLng(center_y, center_x)
      };

      this.map = new kakao.maps.Map(mapContainer, mapOptions)

      var start_marker = new kakao.maps.InfoWindow({
        position: new kakao.maps.LatLng(this.keep_start.mapy, this.keep_start.mapx),
        content: '출발'
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
    getDistanceFromLatLonInKm(lat1, lng1, lat2, lng2) {
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
    },


    requestMatch() {
      axios.get("/api/orders/match/" + this.order.ord_id + "/" + this.userId).then(res => {
        console.log("매칭결과:", res.data)
        alert("매칭결과: " + res.data)
        this.$router.go();
      })
    },

    orderArrival() {
      axios.post("/api/orders/arrival/" + this.order.ord_id).then(res => {
        alert(res.data)
        this.$router.go();
      })
    },
  },
}
</script>

<style scoped>

.btn_type2 {
  display: block;
  overflow: hidden;
  width: 100%;
  height: 54px;
  border-radius: 3px;
  text-align: center;
  border: 1px solid black;
  margin-top: 10px;
  background-color: #1e90cc;
}

#order_detail > tbody > tr > td {
  width: 80%;
}

#order_detail > tbody > tr > th {
  width: 20%;
}

body {
  padding: 1.5em;
  background: #f5f5f5
}

td div {
  display: inline-block;
  width: 50%;
}

table {
  border: 1px #a39485 solid;
  font-size: .9em;
  box-shadow: 0 2px 5px rgba(0, 0, 0, .25);
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
  border-bottom: 1px solid rgba(0, 0, 0, .1);
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
    box-shadow: 0 0 10px rgba(0, 0, 0, .2);
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

.order_time_info {
  font-size: larger;
}

.short_addr {
  font-size: x-large;
}

#orderItem > div {
  float: left;
  width: 25%;
  text-align: center;
}

.order_item_info {
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

