<template>
  <v-app>
    <h1>보관조회</h1>
    <v-data-table
      :headers="headers"
      :items="orderList"
      :items-per-page="5"
      class="elevation-1"
    />
  </v-app>
</template>

<script>
import axios from "axios";

export default {
  name: "TrackingView",
  data() {
    return {
      headers: [
        {text: '주문번호', align: 'start', sortable: false, value:'ord_id'},
        {text: '금액', value:'ord_amount'},
        {text: '맡긴장소', value: 'keep_start'},
        {text: '출발시간', value: 'entrust_time'},
        {text: '도착시간', value: 'withdraw_time'},
      ],
      // trackingList: [
      //   {
      //     orderNum: this.orderList.ord_id,
      //     amount: this.orderList.ord_amount,
      //     startKeep: this.orderList.keep_start,
      //     endKeep: this.orderList.keep_end,
      //   },
      // ],
      orderList: [],
      keys: [],
    }
  },
  mounted() {
    this.a()
  },
  methods: {
    a() {
      axios.get("/api/bag/" + this.$store.state.user.userId)
        .then((res) => {
          this.orderList = res.data;
          console.log(res.data)

        })
    }
  }

}
</script>

<style scoped>

</style>
