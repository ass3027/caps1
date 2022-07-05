<template>
  <v-container>
<!--    물품운송 상세보기-->
    <v-app v-if="item.ord_selection==`물품운송`">
      <div>
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
        시작장소:{{ item.keep_start }}
      </div>
      <div>
        도착장소:{{ item.keep_end }}
      </div>
      <div>
        요청사항:{{ item.ord_request }}
      </div>

      <div>
        <v-btn @click="backTrackingView()">목록</v-btn>
      </div>
    </v-app>


<!--    물품보관 상세보기-->
    <v-app v-else>
      <div>
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
        <v-btn @click="backTrackingView()">목록</v-btn>
      </div>

    </v-app>
  </v-container>
</template>

<script>
import axios from "axios";

export default {
  name: "GTrackingDetail",
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
  },
  methods: {
    trackingDetail() {
      axios.get('/api/trackingDetail/' + this.ord_id)
        .then((res) => {
          this.item = res.data;
          console.log(this.item)
        })
    },
    // //운송원 요청
    // deliveryCall() {
    //   axios.post('/api/deliveryCall/' + this.ord_id)
    //     .then((res) => {
    //       console.log(res.data)
    //     })
    //   this.$router.push('/trackingView')
    // },

    //보관중으로 변경
    storageCall() {
      axios.post('/api/storageCall/' + this.ord_id)
        .then((res) => {
          console.log(res.data)
        })
      this.$router.push('/GTrackingView')
    },

    //찾아감으로 변경
    visitCall() {
      axios.post('/api/visitCall/' + this.ord_id)
        .then((res) => {
          console.log(res.data)
        })
      this.$router.push('/GTrackingView')
    },

    //목록으로 돌아가기
    backTrackingView() {
      this.$router.push('/GTrackingView')
    }
  }
}
</script>

<style scoped>


.tit {
  font-weight: 700;
  font-size: 28px;
  line-height: 35px;
  color: #333;
  letter-spacing: -1px;
}
</style>
