<template>
  <v-app id="app">
    <div class="Bag-order">
      <p class="text-center">
        가방 운송 신청서
      </p>
    </div>
    <v-form v-model="valid">
      <v-row>
        <v-col>
          <v-container justify="space-around">
            <h3>출발장소</h3>
            <search-place @childEvent="startAddress"/>
            <div>출발장소: {{ startLodging.title }}</div>
            <v-img :src="startLodging.firstimage2" width="200px" height="150px" class="ma-2" alt=""/>
          </v-container>
        </v-col>
        <v-col>
          <v-container justify="space-around">
            <h3>도착장소</h3>
            <search-place @childEvent="endAddress"/>
            <div>도착장소: {{ endLodging.title }}</div>
            <v-img :src="endLodging.firstimage2" width="200px" height="150px" class="ma-2" alt=""/>
          </v-container>
        </v-col>
      </v-row>

      <v-container>
        <v-expansion-panels v-model="panel" :disabled="disabled" multiple>
          <v-expansion-panel>
            <v-expansion-panel-header><h3>짐 종류와 수량</h3></v-expansion-panel-header>
            <v-expansion-panel-content>
              <v-card v-for="(item, index) in bagType" :key="index">
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

              <v-card>
                <v-card-text>
                  가방 합계가격: {{ bagAmount }} 원
                </v-card-text>
              </v-card>
            </v-expansion-panel-content>
          </v-expansion-panel>
        </v-expansion-panels>
      </v-container>
      <v-col></v-col>

      <v-card>
        <div>
          <div style="width: 50%; display: inline-block">
            <div style="margin: 0 auto">
              <DateTimePicker @child="resultDate" :label="'시작날짜'"/>
            </div>
          </div>
          <div style="width: 50%; display: inline-block">
            <div style="margin: 0 auto">
              <DateTimePicker @child="resultDate" :label="'종료날짜'"/>
            </div>
          </div>
        </div>
      </v-card>

      <v-col></v-col>
      <v-card>
        <v-col>
          <v-row>
            <v-card>
              <h1>요청사항</h1>
            </v-card>
          </v-row>
          <br>
          <v-textarea
            name="input-7-1"
            label="요청사항을 입력해주세요(255글자 내)"
            v-model="ordRequest"
          ></v-textarea>
        </v-col>
      </v-card>


      <v-btn depressed color="primary" @click="addOrder">
        작성 완료
      </v-btn>
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
        status:'1',
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

</style>
