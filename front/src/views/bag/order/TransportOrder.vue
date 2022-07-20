<template>
  <v-container style="padding-top: 65px; width: 1050px; margin-left:auto; margin-right: auto;" id="app">

    <div style="width: 80%; margin: 0 auto; padding-bottom: 34px; border-bottom: 2px solid black">
      <h2
        class="tit"
        style="padding-bottom: 34px"
        align="center"
      >
        가방 운송 신청서
      </h2>
    </div>
    <v-form
      v-model="valid"
      style="padding-top: 50px"
    >
      <div style="width: 80%; margin: 0 auto; padding-bottom: 20px">
        <div style="width: 50%; display: inline-block">
          <v-card>
            <v-col>
              <v-row>
                <h1 style="padding-top: 30px; padding-left: 30px">
                  출발장소 :
                </h1>
                <search-place
                  style="padding-top:35px; padding-left: 30px"
                  @childEvent="startAddress"
                />
              </v-row>
            </v-col>
            <br>
            <v-card
              class="mx-auto"
              width="350"
              height="350"
            >
              <div v-if="sIndex==true">
                <v-img
                  :src="startLodging.firstimage2"
                  height="200px"
                />
                <v-card-title>
                  {{ startLodging.title }}
                </v-card-title>

                <v-card-subtitle>
                  주소:{{ startLodging.addr1 }}
                </v-card-subtitle>
              </div>
            </v-card>
            <br>
          </v-card>
        </div>


        <div style="width: 50%; display: inline-block">
          <v-card>
            <v-col>
              <v-row>
                <h1 style="padding-top: 30px; padding-left: 30px">
                  도착장소 :
                </h1>
                <search-place
                  style="padding-top:35px; padding-left: 30px"
                  @childEvent="endAddress"
                />
              </v-row>
            </v-col>
            <br>
            <v-card

              class="mx-auto"
              width="350"
              height="350"
            >
              <div v-if="eIndex==true">
                <v-img
                  :src="endLodging.firstimage2"
                  height="200px"
                />
                <v-card-title>
                  {{ endLodging.title }}
                </v-card-title>

                <v-card-subtitle>
                  주소:{{ endLodging.addr1 }}
                </v-card-subtitle>
              </div>
            </v-card>
            <br>
          </v-card>
        </div>
      </div>

      <div style="width: 80%; margin: 0 auto; padding-bottom: 20px">

        <table style="width: 100%; border-bottom: 2px solid #dddfe1;">
          <h2>짐 종류와 수량</h2>
          <hr style="width: 20%; ">
          <br>

          <v-card
            v-for="(item, index) in bagType"
            :key="index"
            style="margin-top: 10px"
          >
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
          <h2>시간</h2>
          <br>
          <div style="padding-left: 15%">
            <div style="width: 50%; display: inline-block">
              <DateTimePicker
                :label="'시작날짜'"
                @child="startDate"
              />
            </div>
            <div style="width: 50%; display: inline-block">
              <DateTimePicker
                :label="'종료날짜'"
                @child="endDate"
              />
            </div>
          </div>
          <br>
        </table>
      </div>

      <div style="width: 80%; margin: 0 auto; padding-bottom: 20px">
        <table style="width: 100%; border-bottom: 2px solid black;">
          <h2>요청사항</h2>
          <br>
          <v-textarea
            v-model="ordRequest"
            style="padding-bottom: 10px"
            name="input-7-1"
            label="요청사항을 입력해주세요(255글자 내)"
            hint="Hint text"
          />
        </table>
      </div>


      <!--      <div style="width: 80%;margin: 0 auto; padding-top: 20px">-->
      <!--        <v-btn style="float: right;" @click="addOrder">작성 완료</v-btn>-->
      <!--      </div>-->
      <div style="width: 80%;margin: 0 auto; padding-top: 20px">
        <v-btn
          style="float: right;"
          @click="reserve"
        >
          작성 완료
        </v-btn>
      </div>
    </v-form>
    <router-view/>
  </v-container>
</template>

<script>
import DateTimePicker from "@/views/bag/order/DateTimePicker"
import axios from "axios";
import SearchPlace from "@/components/search/SearchPlace";

export default {
  components: {
    SearchPlace,
    // MapComponent
    DateTimePicker
  },
  data() {
    return {
      sIndex: false,
      eIndex: false,
      sDate: '',
      eDate: '',
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
        {title: '기내용 캐리어(57cm 미만)', value: 500},
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
  mounted() {
    const script = document.createElement("script")
    const script2 = document.createElement("script")
    script2.src = "https://code.jquery.com/jquery-3.6.0.min.js";
    script2.integrity = "sha256-/xUj+3OJU5yExlq6GSYGSHk7tPXikynS7ogEvDej/m4=";
    script2.setAttribute("crossOrigin", "anonymous");

    document.head.appendChild(script2);

    script.src = "https://cdn.iamport.kr/js/iamport.payment-1.2.0.js";
    script.type = "text/javascript"

    document.head.appendChild(script);
  },
  methods: {
    reserve() {
      var IMP = window.IMP;
      IMP.init('imp19569487');
      console.log(this.lists)
      IMP.request_pay({
        pg: "html5_inics",
        pay_method: "card",
        merchant_uid: "iamport_test_id" + new Date().getTime(),
        name: '운송결제',
        amount: this.bagAmount,
        buyer_email: "testiamport@naver.com",
        buyer_name: this.$store.state.user.userId,
        buyer_tel: "01012341234"
      }, rsp => {
        console.log(rsp);
        if (rsp.success) {
          console.log(rsp)
          this.addOrder()
          var imp = {
            user_id: this.$store.state.user.userId,
            ord_id: '',
            pay_price: rsp.paid_amount,
          }
          axios.post('/api/transportPay', imp)
            .then((res) => {
              console.log(res)

            })
        } else {
          alert("실패")
        }
      })
    },
    startDate(sDate) {
      this.sDate = sDate
      return this.sDate
    },
    endDate(eDate) {
      this.eDate = eDate
      console.log('도착날짜', this.eDate)
      return this.eDate
    },

    startAddress: function (lodging) {
      this.startLodging = lodging
      this.sIndex = true
      console.log("시작장소데이터")
      console.log(this.startLodging);

    },

    endAddress: function (lodging) {
      this.endLodging = lodging
      this.eIndex = true
      console.log("도착장소데이터")
      console.log(lodging)
    },

    addOrder() {
      let transportBag = {
        ord_id: '',
        ord_amount: this.bagAmount, //금액
        user_id: this.$store.state.user.userId, // UserId
        keep_start: this.startLodging.pl_id, //시작장소
        keep_end: this.endLodging.pl_id, //도착장소
        entrust_time: this.sDate,  //맡길시간
        withdraw_time: this.eDate,  //찾을시간
        ord_selection: '물품운송', //물품보관
        ord_request: this.ordRequest,
        status: '승인대기',
      }
      axios.post('/api/transportAddOrder', transportBag)
        .then((res) => {
          console.log(transportBag)

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

.tit {
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
