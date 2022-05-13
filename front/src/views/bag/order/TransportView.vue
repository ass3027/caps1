<template>
  <v-app id="app">
    <v-form v-model="valid">
      <v-container>
        <h3>짐 종류와 수량</h3>
        <v-container class="d-flex flex-column mb-6">
          <v-row v-for="(bagType,index) in bagType " :key="index">
            {{ bagType.title }}
            <v-checkbox v-model="checkedName" :value="bagType.value"/>
          </v-row>

          <v-card>
            <h1>가방 합계가격: {{ bagTypeChoose }} 원</h1>
          </v-card>
        </v-container>
      </v-container>

      <v-container justify="space-around">
        <v-row md="6">
          <v-col>
            <v-card>
              <h3>맡길 날짜</h3>
              <v-date-picker v-model="entrustPicker" @addressData="startPicker"></v-date-picker>
            </v-card>
          </v-col>

          <v-col>
            <v-card>
              <h3> 맡길 시간</h3>
              <div>{{ entrustTime }}</div>
              <v-time-picker v-model="entrustTime" @addressData="startTime"></v-time-picker>
            </v-card>
          </v-col>
          <v-card>
            <h3>출발장소</h3>
            <AddressComponent @addressData="startAddress"></AddressComponent>
          </v-card>
        </v-row>
      </v-container>
      <br>
      <v-container justify="space-around">
        <v-row md="6">
          <v-col>
            <v-card>
              <h3>찾을 날짜</h3>
              <div> {{ withdrawPicker }}</div>
              <v-date-picker v-model="withdrawPicker" @addressData="endPicker"></v-date-picker>
            </v-card>
          </v-col>

          <v-col>
            <v-card>
              <h3> 찾을 시간</h3>
              <div>{{ withdrawTime }}</div>
              <v-time-picker v-model="withdrawTime" @addressData="endTime"></v-time-picker>
            </v-card>
          </v-col>
          <v-card>
            <h3>도착장소</h3>
            <AddressComponent @addressData="endAddress"></AddressComponent>
          </v-card>
        </v-row>
      </v-container>


      <div>
        <v-btn depressed color="primary" @click="addOrder">
          작성 완료
        </v-btn>
      </div>
    </v-form>

    <router-view/>
  </v-app>
</template>
<script>


import AddressComponent from "@/components/AddressComponent"

import axios from "axios";

export default {
  components: {
    AddressComponent,

  },
  data() {
    return {
      checkedName: [],
      overlay: false,
      valid: '',
      checkBagTime: '',
      pickUpTime: '',
      //시작날짜
      entrustPicker: (new Date(Date.now() - (new Date()).getTimezoneOffset() * 60000)).toISOString().substr(0, 10),
      //도착날짜
      withdrawPicker: (new Date(Date.now() - (new Date()).getTimezoneOffset() * 60000)).toISOString().substr(0, 10),
      //시작장소
      entrustAddress: '',
      //도착장소
      withdrawAddress: '',
      //맡길시간
      entrustTime: '',
      //찾을시간
      withdrawTime: '',

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
    bagTypeChoose() {
      var a = 0;
      this.checkedName.forEach(i => {
        a = a + i;
      })
      console.log(a)
      return a;
    },
  },
  methods: {
    addOrder() {
      const bag = {
        ord_id: 301,
        user_id: this.$store.state.user.userId,
        keep_start: this.entrustAddress,
        keep_end: this.withdrawAddress,
        order_amount: this.bagTypeChoose,
        entrust_picker: this.entrustPicker,
        entrust_time: this.entrustTime,
        withdraw_picker: this.withdrawPicker,
        withdraw_time: this.withdrawTime,
      }
      console.log(bag);
      axios
        .post('/api/addOrder', bag)
        .then((res) => {
          alert("주문 완료!")
        })
    },
    startPicker(address) {
      console.log(address)
      this.entrustPicker = address
      console.log(this.entrustPicker)
    },
    startTime(address) {
      this.entrustTime = address
      console.log(this.entrustTime)
    },
    startAddress(address) {
      this.entrustAddress = address
      console.log(this.entrustAddress)
    },
    endPicker(address){
      this.withdrawPicker = address
      console.log(this.withdrawPicker)
    },
    endTime(address){
      this.withdrawTime = address
    },
    endAddress(address) {
      this.withdrawAddress = address
      console.log(this.withdrawAddress)
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
