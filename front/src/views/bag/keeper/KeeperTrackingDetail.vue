<template>
  <v-container>
    <!--    <v-app>-->
    <!--      <div>-->
    <!--        주문번호:{{ item.ord_id }}-->
    <!--      </div>-->
    <!--      <div>-->
    <!--        금액:{{ item.ord_amount }}-->
    <!--      </div>-->
    <!--      <div>-->
    <!--        시작시간:{{ item.entrust_time }}-->
    <!--      </div>-->
    <!--      <div>-->
    <!--        도착시간:{{ item.withdraw_time }}-->
    <!--      </div>-->
    <!--      <div>-->
    <!--        시작장소:{{ item.keep_start }}-->
    <!--      </div>-->
    <!--      <div>-->
    <!--        도착장소:{{ item.keep_end }}-->
    <!--      </div>-->
    <!--      <div>-->
    <!--        요청사항:{{ item.ord_request }}-->
    <!--      </div>-->

    <!--      <div>-->
    <!--        <v-btn @click="backTrackingView()">목록</v-btn>-->
    <!--      </div>-->
    <!--    </v-app>-->


    <v-app>
      <div>
        {{ item }}
        주문번호:{{ item.ord_id }}
      </div>
      <div>
        금액:{{ item.ord_amount }}
      </div>
      <div>
        시작시간:{{ item.entrust_time }}
      </div>
      <div>
        도착시간:{{ item.withdraw_time }}
      </div>
      <div>
        맡긴장소:{{ item.keep_start }}
      </div>
      <div>
        요청사항:{{ item.ord_request }}
      </div>
      <div>
        <v-btn @click="deliveryCall()">승인완료</v-btn>
      </div>
      <div>
        <v-btn @click="backTrackingView()">목록</v-btn>
      </div>

    </v-app>
  </v-container>
</template>

<script>
import axios from "axios";

export default {
  name: "KeeperTrackDetail",
  data() {
    return {
      item: '',

    }
  },
  props: [
    'ord_id'
  ],

  mounted() {
    this.trackingDetail()
    this.plId()
  },
  methods: {
    //물품 상세보기
    trackingDetail() {
      axios.get('/api/trackingDetail/' + this.ord_id)
        .then((res) => {
          this.item = res.data;
          console.log(this.item)
        })
    },
    //키퍼회원 운송원 요청
    deliveryCall() {
      if (this.plId == this.item.keep_start) {
        axios.post('/api/deliveryStartCall/' + this.ord_id)
          .then((res) => {
            console.log('출발키퍼 상태완료')
            console.log(res.data)
          })
      } else {
        axios.post('/api/deliveryEndCall/' + this.ord_id)
          .then((res) => {
            console.log('도착키퍼 상태완료')
            console.log(res.data)
          })
      }
      this.$router.push('/KeeperTrackingView')
    },

    //보관중으로 변경
    storageCall() {
      axios.post('/api/storageCall/' + this.ord_id)
        .then((res) => {
          console.log(res.data)
        })
      this.$router.push('/trackingView')
    },

    // 장소ID 받아옴
    plId() {
      axios.get("/api/plId")
        .then((res) => {
          this.plId = res.data;
        })
    },
    //목록으로 돌아가기
    backTrackingView() {
      this.$router.push('/KeeperTrackingView')
    }


  }
}

</script>

<style scoped>

</style>
