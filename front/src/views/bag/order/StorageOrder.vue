<template>
  <v-app id="app" style="max-width: 1050px; margin: 0 auto">
    <div class="Bag-order">
      <p class="text-center">
        가방 보관 신청서
      </p>
    </div>
    <v-form v-model="valid">
      <div>
        <h3>맡길장소</h3>

        <v-dialog v-model="dialog">
          <search-place @childEvent="getEmitData"/>
        </v-dialog>

        <v-btn class="btn_type2" @click="dialog = true">
          장소검색
        </v-btn>

        <div style="margin-top: 10px">맡길장소: {{ lodging }}</div>
        <h3>짐 종류와 수량</h3>
        <div id="selection_bag">
            <v-card v-for="(item, index) in bagType" :key="index">
              <v-card-title>
                <v-row align="center">
                  <v-toolbar-title>
                    {{item.title }}
                  </v-toolbar-title>
                  <v-spacer/>
                  {{ item.value }}원
                  <v-checkbox
                    v-model="checkedName"
                    :value="item.value"
                  />
                </v-row>
              </v-card-title>
            </v-card>
          </div>

        <v-card>
          <v-card-title>가방 합계가격: {{ bagAmount }} 원</v-card-title>
        </v-card>
      </div>

      <v-card>
        <v-row>
          <DateTimePicker
            :label="'맡길날짜'"
            @date="changeEntrustTime"
          />
          <DateTimePicker
            :label="'찾을날짜'"
            @date="changeWithdrawTime"
          />
        </v-row>
      </v-card>

      <v-row>
        <v-col>
          <v-card>
            <h3>맡길장소</h3>
            <AddressComponent @addressData="startAddress"/>
          </v-card>
        </v-col>
      </v-row>

      <v-btn
        depressed
        color="primary"
        @click="addOrder"
      >
        작성 완료
      </v-btn>
    </v-form>
    <router-view />
  </v-app>
</template>

<script>
import AddressComponent from "@/components/AddressComponent"
import DateTimePicker from "@/views/bag/order/DateTimePicker"
import axios from "axios";
import SearchPlace from "@/components/SearchPlace";

export default {
  components: {
    SearchPlace,
    // MapComponent
    AddressComponent,
    DateTimePicker
  },
  data() {
    return {
      lodging: '',
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
        {title: '기내용 캐리어(57cm 미만)', value: 11000},
        {title: '화물용 캐리어(57cm 이상 67cm 미만)', value: 16000},
        {title: '특대형 캐리어(67cm 이상 또는 20kg 이상)', value: 20000},
        {title: '백팩 소형(40L 미만 그리고 10kg 미만)', value: 10000},
        {title: '백팩 대형(40L 이상 또는 10kg 이상)', value: 15000},
        {title: '기타물품 별도문의', value: 30000}
      ],
      dialog: false,
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
    getEmitData: function (lodging) {
      this.lodging = lodging
      console.log("받은데이터" + lodging)
    },
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
        withdraw_time: this.withdrawTime,  //찾을시간
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
}
</script>

<style scoped>
.Bag-order {
  margin: 10px;
  font-size: xx-large;
}

.btn_type2 {
  display: block;
  overflow: hidden;
  height: 54px;
  border-radius: 3px;
  text-align: center;
  border: 1px solid black;
  color: #333;
  margin-top: 10px;
}


</style>
