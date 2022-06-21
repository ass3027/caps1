<template>
  <v-app id="app" style="width: 1050px; padding-top: 65px">
    <div style="width: 80%; margin: 0 auto; padding-bottom: 34px; border-bottom: 2px solid black">
      <h2 class="tit">가방 운송 신청서</h2>
    </div>
    <v-form v-model="valid" style="padding-top: 50px">
      <div style="width: 80%; margin: 0 auto; padding-bottom: 20px">
        <div style="width: 50%; display: inline-block">

          <h3>출발장소</h3>
          <search-place @childEvent="startAddress"/>
          <br>
          <div>출발장소: {{ startLodging.title }}</div>
          <v-img :src="startLodging.firstimage2" width="200px" height="150px" class="ma-2" alt=""/>
          <br>
        </div>
        <div style="width: 50%; display: inline-block">
          <h3>도착장소</h3>
          <search-place @childEvent="endAddress"/>
          <br>
          <div>도착장소: {{ endLodging.title }}</div>
          <v-img :src="endLodging.firstimage2" width="200px" height="150px" class="ma-2" alt=""/>
          <br>
        </div>
      </div>

      <div style="width: 80%; margin: 0 auto; padding-bottom: 20px">
        <table style="width: 100%; border-bottom: 2px solid #dddfe1;">

          <h3>짐 종류와 수량</h3>
          <br>

          <v-card v-for="(item, index) in bagType" :key="index" style="margin-top: 10px">

            <v-card-text>
              <v-row align="center">
                {{ item.title }}
                <v-spacer/>
                {{ item.value }}원
                <v-checkbox
                  v-model="checkedName"
                  :value="item.value"
                />
              </v-row>
            </v-card-text>
          </v-card>

          <v-card style="margin: 20px 0">
            <v-card-text>
              가방 합계가격: {{ bagAmount }} 원
            </v-card-text>
          </v-card>
          <br>
        </table>
      </div>

      <div style="width: 80%; margin: 0 auto; padding-bottom: 20px">
        <table style="width: 100%; border-bottom: 2px solid #dddfe1;">
          <h3>시간</h3>
          <br>
          <div style="padding-left: 15%">
            <div style="width: 50%; display: inline-block" >
              <DateTimePicker @child="resultDate" :label="'시작날짜'"/>
            </div>
            <div style="width: 50%; display: inline-block">
              <DateTimePicker @child="resultDate" :label="'종료날짜'"/>
            </div>
          </div>
          <br>

        </table>
      </div>

      <div style="width: 80%; margin: 0 auto; padding-bottom: 20px">
        <table style="width: 100%; border-bottom: 2px solid black;">
          <h3>요청사항</h3>
          <br>
          <v-textarea style="padding-bottom: 10px"
                      name="input-7-1"
                      label="요청사항을 입력해주세요(255글자 내)"
                      v-model="ordRequest"
                      hint="Hint text"
          ></v-textarea>
        </table>
      </div>




      <!--      <div style="width: 80%;margin: 0 auto; padding-top: 20px">-->
      <!--        <v-btn style="float: right;" @click="addOrder">작성 완료</v-btn>-->
      <!--      </div>-->
      <div style="width: 80%;margin: 0 auto; padding-top: 20px">
        <button style="float: right;" @click="addOrder">작성 완료</button>
      </div>
    </v-form>
    <router-view/>
  </v-app>
</template>

<script>
import DateTimePicker from "@/views/bag/order/DateTimePicker"
import axios from "axios";
import SearchPlace from "@/components/SearchPlace";

export default {
  components: {
    SearchPlace,
    // MapComponent
    DateTimePicker
  },
  data() {
    return {
      sDate: '',
      panel: [0, 1],
      disabled: false,
      readonly: true,
      startLodging: '',
      endLodging: '',
      checkedName: [],
      overlay: false,
      valid: '',
      checkBagTime: '',
      //날짜 데이터
      dateTimePicker: '',
      //시작날짜
      entrustTime: '',
      //종료날짜
      withdrawTime: '',
      //시작장소
      entrustAddress: '',
      //도착장소
      withdrawAddress: '',
      //요청사항
      ordRequest: '',
      //이미지
      image2: '',

      bagType: [
        {title: '기내용 캐리어(57cm 미만)', value: 11000},
        {title: '화물용 캐리어(57cm 이상 67cm 미만)', value: 16000},
        {title: '특대형 캐리어(67cm 이상 또는 20kg 이상)', value: 20000},
        {title: '백팩 소형(40L 미만 그리고 10kg 미만)', value: 10000},
        {title: '백팩 대형(40L 이상 또는 10kg 이상)', value: 15000},
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
    resultDate(sDate) {
      this.sDate = sDate
      return this.sDate
    },

    startAddress: function (lodging) {
      this.startLodging = lodging
      console.log("시작장소데이터" + lodging)
    },

    endAddress: function (lodging) {
      this.endLodging = lodging
      console.log("도착장소데이터" + lodging)
    },

    addOrder() {
      let transportBag = {
        ord_id: '',
        ord_amount: this.bagAmount, //금액
        user_id: this.$store.state.user.userId, // UserId
        keep_start: this.startLodging.pl_id, //시작장소
        keep_end: this.endLodging.pl_id, //도착장소
        entrust_time: this.sDate,  //맡길시간
        withdraw_time: this.sDate,  //찾을시간
        ord_selection: '물품운송', //물품보관
        ord_request: this.ordRequest,
        status:'운송요청',
      }
      axios
        .post('/api/transportAddOrder', transportBag)
        .then((res) => {
          console.log(transportBag)
          alert('주문완료!')
        })
    },

  },
}
</script>

<style scoped>
.Bag-order {
  margin: 10px;
  font-size: xx-large;
}

.tit{
  font-weight: 700;
  font-size: 28px;
  line-height: 35px;
  color: #333;
  letter-spacing: -1px;
}
button {
  border: 1px solid black;
  background-color: white;
  color: black;
  display: inline-block;
  width: 120px;
  vertical-align: top;
  height: 44px;
  border-radius: 3px;
  font-weight: 700;
  font-size: 14px;
  line-height: 40px;
  text-align: center;
  outline: none;
}

</style>
