<template>
  <v-app id="app">
    <div class="Bag-order">
      가방 운송 신청서
    </div>
    <v-form v-model="valid">
      <h3>짐 종류와 수량</h3>
      <v-container class="d-flex flex-column mb-6">
        <v-row
          v-for="(bagType,index) in bagType "
          :key="index"
        >
          {{ bagType.title }}
          <v-checkbox
            v-model="checkedName"
            :value="bagType.value"
          />
        </v-row>

        <v-card>
          <h1>가방 합계가격: {{ bagAmount }} 원</h1>
        </v-card>
      </v-container>

      <v-container justify="space-around">
        <v-row>
          <DateTimePicker
            :label="'시작날짜'"
            @date="changeEntrustTime"
          />
          <DateTimePicker
            :label="'종료날짜'"
            @date="changeWithdrawTime"
          />
        </v-row>
        <v-row>
          <v-col>
            <v-card>
              <h3>출발장소</h3>
              <AddressComponent @addressData="startAddress" />
            </v-card>
          </v-col>
          <v-col>
            <v-card>
              <h3>도착장소</h3>
              <AddressComponent @addressData="endAddress" />
            </v-card>
          </v-col>
        </v-row>
      </v-container>
      <br>

      <div>
        <v-btn
          depressed
          color="primary"
          @click="addOrder"
        >
          작성 완료
        </v-btn>
      </div>
    </v-form>

    <router-view />
  </v-app>
</template>

<script>
import AddressComponent from "@/components/AddressComponent"
import DateTimePicker from "@/views/bag/order/DateTimePicker"
import axios from "axios";

export default {
  components: {
    AddressComponent,
    DateTimePicker
  },
  data() {
    return {
      checkedName: [],
      overlay: false,
      valid: '',
      checkBagTime: '',

      //시작날짜
      entrustTime: '',
      //도착날짜
      withdrawTime: '',
      //시작장소
      entrustAddress: '',
      //도착장소
      withdrawAddress: '',

      bagType: [
        {title: '기내용 캐리어(57cm 미만) 11,000원', value: 11000},
        {title: '화물용 캐리어(57cm 이상 67cm 미만) 16,000원', value: 16000},
        {title: '특대형 캐리어(67cm 이상 또는 20kg 이상) 20,000원', value: 20000},
        {title: '백팩 소형(40L 미만 그리고 10kg 미만) 10,000원', value: 10000},
        {title: '백팩 대형(40L 이상 또는 10kg 이상) 15,000원', value: 15000},
        {title: '기타물품 별도문의', value: 30000}
      ],
    }
  },
  computed: {
    bagAmount() {
      var a = 0;
      this.checkedName.forEach(i => {
        a = a + i;
      })
      console.log(a)
      return a;
    },
  },
  watch: {
    overlay(val) {
      val && setTimeout(() => {
        this.overlay = false
      }, 2000)
    },
  },

  methods: {
    changeEntrustTime(date) {
      this.entrustTime = date;
    },
    changeWithdrawTime(date) {
      this.withdrawTime = date;
    },
    addOrder() {
      let transportBag = {
        ord_id: 301,  //주문번호
        ord_amount: this.bagAmount, //금액
        user_id: this.$store.state.user.userId, //userid
        keep_start: this.entrustAddress,  //시작장소
        keep_end: this.withdrawAddress, //도착장소
        entrust_time: this.entrustTime, //시작시간
        withdraw_time: this.withdrawTime, //도착시간
        ord_selection: 'Transport', //물품배송
      }
      axios
        .post('/api/trAddOrder', transportBag)
        .then((res) => {
          console.log(transportBag)
          alert("주문 완료!")
        })
    },
    startAddress(address) {
      this.entrustAddress = address
      console.log(this.entrustAddress)
    },
    endAddress(address) {
      this.withdrawAddress = address
      console.log(this.withdrawAddress)
    },
  },
}
</script>
<style scoped>
.Bag-order {
  margin: 10px;
  font-size: xx-large;
}
</style>
