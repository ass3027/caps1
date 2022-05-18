<template>
  <v-app id="app">
    <div class='Bag-order'>
      가방 보관 신청서
    </div>
    <v-form v-model="valid">
      <v-container>
        <h3>짐 종류와 수량</h3>
        <v-container class="d-flex flex-column mb-6">
          <v-row v-for="(bagType,index) in bagType " :key="index">
            {{ bagType.title }}
            <v-checkbox v-model="checkedName" :value="bagType.value"/>
          </v-row>

          <v-card>
            <h1>가방 합계가격: {{ bagAmount }} 원</h1>
          </v-card>
        </v-container>
      </v-container>

      <v-container justify="space-around">
        <v-row>
          <DateTimePicker :label="'맡길날짜'" @date="changeEntrustTime"></DateTimePicker>
          <DateTimePicker :label="'찾을날짜'" @date="changeWithdrawTime"></DateTimePicker>
        </v-row>
        <v-row>
          <v-col>
            <v-card>
              <h3>맡길장소</h3>
              <AddressComponent @addressData="startAddress"></AddressComponent>
            </v-card>
          </v-col>

        </v-row>
      </v-container>
      <v-btn depressed color="primary" @click="addOrder">
        작성 완료
      </v-btn>
    </v-form>
    <router-view/>
  </v-app>

</template>

<script>
import AddressComponent from "@/components/AddressComponent"
import DateTimePicker from "@/views/bag/order/DateTimePicker"
import axios from "axios";

export default {
  components: {
    // MapComponent
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

  methods: {
    changeEntrustTime(date) {
      this.entrustTime = date;
    },
    changeWithdrawTime(date) {
      this.withdrawTime = date;
    },
    addOrder() {
      let storageBag = {
        ord_id: 301,
        ord_amount: this.bagAmount, //금액
        user_id: this.$store.state.user.userId, // UserId
        keep_start: this.entrustAddress, //맡길장소
        entrust_time: this.entrustTime, //맡길시간
        withdraw_time:this.withdrawTime,  //찾을시간
        ord_selection: 'storage', //물품보관
      }
      axios
        .post('/api/stAddOrder', storageBag)
        .then((res) => {
          console.log(storageBag)
          alert('주문완료!')
        })
    },
    startAddress(address) {
      this.entrustAddress = address
      console.log(this.entrustAddress)
    },

  },
  watch: {
    overlay(val) {
      val && setTimeout(() => {
        this.overlay = false
      }, 2000)
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
