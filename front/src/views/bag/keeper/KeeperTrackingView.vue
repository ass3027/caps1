<template>
  <v-container>
    <v-app>
      <v-col>
        <v-row>
          {{ keeperStorageList.user_id }}
        </v-row>
        <h1>키퍼 회원 전체 물품 현황 조회</h1>
      </v-col>
      <v-row style="width: 300px;">
        <v-col style="width: 150px">
          <v-select
            v-model="check"
            label="조회 목록"
            :items="dropdown_edit"
            item-value="text"
          />
        </v-col>

        <v-col
          v-if="check=='보관'"
          style="width: 150px"
        >
          <v-select
            v-model="storageStatusCheck"
            label="주문상태"
            :items="storageStatus"
            item-value="text"
          />
        </v-col>

        <v-col
          v-else
          style="width: 150px"
        >
          <v-select
            v-model="transportStatusCheck"
            label="주문상태"
            :items="transportStatus"
            item-value="text"
          />
        </v-col>
      </v-row>

      <div>
        <template v-if="check == '보관'">
          <h3>보관조회</h3>
          <v-data-table
            :headers="storageHeaders"
            :items="sortedStorageList"
            :items-per-page="5"
          >
            <template #[`item.detail`]="{ item }">
              <v-btn @click="orderDetail(item)">
                상세보기
              </v-btn>
            </template>
          </v-data-table>
        </template>

        <template v-else>
          <h3>운송조회</h3>
          <v-data-table
            :headers="transportHeaders"
            :items="sortedTransportList"
            :items-per-page="5"
          >
            <template #[`item.detail`]="{ item }">
              <v-btn @click="orderDetail(item)">
                상세보기
              </v-btn>
            </template>
          </v-data-table>
        </template>
      </div>
    </v-app>
  </v-container>
</template>

<script>
import axios from "axios";

export default {
  name: "KeeperTrackingView",
  data() {
    return {
      dialogStorage: 'true',
      storageDialog: '',
      check: '운송',
      storageStatusCheck: '보관요청',
      transportStatusCheck: '승인대기',
      storageStatus: [
        {text: '보관요청', value: '보관요청'},
        {text: '보관중', value: '보관중'},
        {text: '찾아감', value: '찾아감'},
      ],
      transportStatus: [
        {text: '승인대기', value: '승인대기'},
        {text: '승인완료', value: '승인완료'},
        {text: '픽업전', value: '픽업전'},
        {text: '픽업완료', value: '픽업완료'},
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
        {text: '회원', value: 'user_id'},
        {text: '맡긴장소', value: 'keep_start_title'},
        {text: '출발시간', value: 'entrust_time'},
        {text: '도착시간', value: 'withdraw_time'},
        {text: '주문상태', value: 'status'},
        {text: '상세보기', value: 'detail'}
      ],
      transportHeaders: [
        {text: '주문번호', align: 'start', sortable: false, value: 'ord_id'},
        {text: '금액', value: 'ord_amount'},
        {text: '회원', value: 'user_id'},
        {text: '출발장소', value: 'keep_start_title'},
        {text: '도착장소', value: 'keep_end_title'},
        {text: '출발시간', value: 'entrust_time'},
        {text: '도착시간', value: 'withdraw_time'},
        {text: '주문상태', value: 'status'},
        {text: '출발상태', value: 'start_status'},
        {text: '도착상태', value: 'end_status'},
        {text: '상세보기', value: 'detail'}
      ],

      keeperStorageList: [],
      keeperTransportList: [],
      status: ''
    }
  },
  computed: {
    sortedStorageList() {
      return this.keeperStorageList.filter(it => it.status === this.storageStatusCheck)
    },
    sortedTransportList() {
      return this.keeperTransportList.filter(it => it.status === this.transportStatusCheck)
    }
  },
  mounted() {
    this.storageData()
    this.transportData()
  },
  methods: {
    //물품보관 데이터
    storageData() {
      axios.get("/api/keeperStorage")
        .then((res) => {
          this.keeperStorageList = res.data;
          console.log("물품보관 데이터")
          console.log(res.data)
        })
    },
    //물품배송 데이터
    transportData() {
      axios.get("/api/keeperTransport")
        .then((res) => {
          console.log("물품배송 데이터")
          console.log(res.data)
          this.keeperTransportList = res.data;
        })
    },

    //키퍼 물품상세보기 페이지 연결
    orderDetail(item) {
      this.$router.push({name: 'KeeperOrderDetail', params: {order: item}})
    },
  }
}
</script>

<style scoped>

</style>
