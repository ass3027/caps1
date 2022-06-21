<template>
  <v-app>
    <v-card>
      <v-col>
        <h1>물품조회</h1>
      </v-col>
      <v-col style="width: 150px">
        <v-select label="조회 목록" :items="dropdown_edit" item-value="text" v-model="check"/>
      </v-col>
    </v-card>

    <v-col>
      <template v-if="check == '보관'">
        <h3>보관조회</h3>
        <v-data-table :headers="storageHeaders" :items="storageList" :items-per-page="5"/>
      </template>

      <template v-else>
        <h3>운송조회</h3>
        <v-data-table
          :headers="transportHeaders"
          :items="transportList"
          :items-per-page="5"
        />
      </template>
    </v-col>
  </v-app>

</template>

<script>
import axios from "axios";

export default {
  name: "TrackingView",
  data() {
    return {
      check: '보관',
      dropdown_edit: [
        {text: '보관', value: 'storage'},
        {text: '운송', value: 'transport'},

      ],
      storageHeaders: [
        {text: '주문번호', align: 'start', sortable: false, value: 'ord_id'},
        {text: '금액', value: 'ord_amount'},
        {text: '맡긴장소', value: 'title'},
        {text: '출발시간', value: 'entrust_time'},
        {text: '도착시간', value: 'withdraw_time'},
        {text: '주문종류', value: 'ord_selection'},
      ],
      transportHeaders: [
        {text: '주문번호', align: 'start', sortable: false, value: 'ord_id'},
        {text: '금액', value: 'ord_amount'},
        {text: '출발장소', value: 'keep_start'},
        {text: '도착장소', value: 'keep_end'},
        {text: '출발시간', value: 'entrust_time'},
        {text: '도착시간', value: 'withdraw_time'},
        {text: '주문종류', value: 'ord_selection'},
      ],
      method: {},
      storageList: [],
      transportList: [],
      keys: [],
    }
  },
  mounted() {
    this.a()
    this.b()
  },
  methods: {
    a() {
      axios.get("/api/bagStorage/" + this.$store.state.user.userId)
        .then((res) => {
          this.storageList = res.data;
          console.log('물품보관 데이터:' + res.data)
        })
    },
    b() {
      axios.get("/api/bagTransport/" + this.$store.state.user.userId)
        .then((res) => {
          this.transportList = res.data;
          console.log('물품운송 데이터:' + res.data)
        })
    }
  }
}
</script>

<style scoped>

</style>
