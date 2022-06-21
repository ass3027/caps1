<template>
  <v-app>
    <v-card>
      <v-col>
        <h1>물품조회</h1>
      </v-col>
      <v-row style="width: 300px;">
        <v-col style="width: 150px">
          <v-select label="조회 목록" :items="dropdown_edit" item-value="text" v-model="check"/>
        </v-col>

        <v-col style="width: 150px" v-if="check=='보관'">
          <v-select label="주문상태" :items="storageStatus" item-value="text" v-model="storageStatusCheck" />
        </v-col>

        <v-col style="width: 150px" v-else>
          <v-select label="주문상태" :items="transportStatus" item-value="text" v-model="transportStatusCheck" />
        </v-col>

      </v-row>
    </v-card>

    <v-col>
      <template v-if="check == '보관'">
        <h3>보관조회</h3>
        <v-data-table :headers="storageHeaders" :items="sortedStorageList" :items-per-page="5" >
        </v-data-table>
      </template>

      <template v-else>
        <h3>운송조회</h3>
        <v-data-table
          :headers="transportHeaders"
          :items="sortedTransportList"
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
      storageStatusCheck: '보관요청',
      transportStatusCheck:'운송요청',
      storageStatus: [
        {text: '보관요청', value: '보관요청'},
        {text: '보관중', value: '보관중'},
        {text: '찾아감', value: '찾아감'},
      ],
      transportStatus:[
        {text: '운송요청', value: '운송요청'},
        {text: '운송중', value: '운송중'},
        {text: '운송완료', value: '운송완료'},
      ],

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
        {text: '주문상태', value: 'status'}
      ],
      transportHeaders: [
        {text: '주문번호', align: 'start', sortable: false, value: 'ord_id'},
        {text: '금액', value: 'ord_amount'},
        {text: '출발장소', value: 'keep_start'},
        {text: '도착장소', value: 'keep_end'},
        {text: '출발시간', value: 'entrust_time'},
        {text: '도착시간', value: 'withdraw_time'},
        {text: '주문상태', value: 'status'}
      ],
      methods: {},
      storageList: [],
      transportList: [],
      keys: [],
    }
  },
  mounted() {
    this.a()
    this.b()
  },
  computed:{
    sortedStorageList(){
      return this.storageList.filter( it=> it.status===this.storageStatusCheck)
    },
    sortedTransportList(){
      return this.transportList.filter( it=> it.status===this.transportStatusCheck)
    }
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
