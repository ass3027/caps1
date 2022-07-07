<template>
  <div
    id="lll"
    class="calendar"
  >
    <PlannerHeader/>
    <v-row>
      <div class="map">
        <MapComponent
          index="00"
        />
      </div>

      <div
        id="plan"
        style="width:30%; height: 750px;margin-bottom: 100px;display: inline-block"
      >
        <v-row>
          <v-col cols="6" style="padding: 0">
            <h2>시작 일자</h2>
          </v-col>
          <v-col cols="6" style="padding: 0">
            <h2>종료 일자</h2>
          </v-col>
        </v-row>
        <v-row>
          <v-col style="padding-top: 0">
            <input
              v-model="startDate"
              type="date"
              class="calendarDate"
              @click.prevent
            >
          </v-col>
          <!--          <v-col style="width: 5%;">-->
          <!--            <p style="font-size: 34px;">~</p>-->
          <!--          </v-col>-->
          <v-col style="padding-top: 0">
            <input
              v-model="endDate"
              type="date"
              class="calendarDate"
              @click.prevent
            >
          </v-col>
        </v-row>
        <v-row style="text-align:center">
          <v-col cols="4">
            <h2
              placeholder="일정이름"
              style="height: 10%;font-size: 25px;padding: 10px"
            > 일정 이름 :</h2>
          </v-col>
          <v-col cols="8">
            <input
              v-model="schName"
              style="font-size: 25px;padding: 3px;border: 3px solid;
               border-radius: 4px;"
            >
          </v-col>

        </v-row>
        <v-row style="width:100%;height: 10%;">
          <v-col cols="6">
            <v-btn
              @click="save()"

            >
              저장
            </v-btn>
          </v-col>
          <v-col cols="6">
            <v-dialog
              width="50%"
              v-model="bookDialog"
            >
              <template v-slot:activator="{on}">
                <v-btn

                  style="float:right"
                  v-bind="on"
                  @click="bookDialog=true"
                >
                  예약내역
                </v-btn>
              </template>
              <v-card>
                <v-card-title>
                  <v-select
                    v-model="bookCategory"
                    @change="gd"
                    style="width: 50%"
                    label="숙소/가이드"
                    :items="['가이드','숙소']"
                  />
                </v-card-title>
                <v-card-text>
                  <template v-if="bookCategory === '가이드'">
                    <v-simple-table class="text-center">
                      <thead>
                      <tr>
                        <!--                          <th>pay_id</th>-->
                        <th>호텔명</th>
                        <th>상품명</th>
                        <th>호실</th>
                        <th class="text-center">체크인 시간</th>
                        <th>체크아웃시간</th>
                        <th></th>

                      </tr>

                      </thead>
                      <tbody>
                      <tr
                        v-for="(book,index) in books.productBook"
                        :key="index"
                      >
                        <!--                          <td>{{book.PAY_ID}}</td>-->
                        <td>{{ book.TITLE }}</td>
                        <td>{{ book.PD_NAME }}</td>
                        <td>{{ book.ROOM_NUM }}</td>
                        <td>{{ book.START_DATE }}</td>
                        <td>{{ book.END_DATE }}</td>
                        <td>
                          <v-btn @click="addSchProduct(book)">일정 추가</v-btn>
                        </td>
                      </tr>

                      </tbody>
                    </v-simple-table>
                  </template>
                  <template v-else>
                    <v-simple-table class="text-center">
                      <thead>
                      <tr>
                        <th>가이드상품명</th>
                        <th>날짜</th>
                        <th>시작시각</th>
                        <th>종료시각</th>
                        <!--                          <th class="text-center">start_date</th>-->
                        <!--                          <th>end_date</th>-->
                        <th></th>

                      </tr>

                      </thead>
                      <tbody>
                      <tr
                        v-for="(book,index) in books.guideBook"
                        :key="index"
                      >
                        <td>{{ book.GNAME }}</td>
                        <td>{{ book.GDATE }}</td>
                        <td>{{ book.ST_TIME }}</td>
                        <td>{{ book.END_TIME }}</td>
                        <td>
                          <v-btn @click="addSchGuide(book)">일정 추가</v-btn>
                        </td>
                      </tr>

                      </tbody>
                    </v-simple-table>
                  </template>
                </v-card-text>
                <v-card-actions>
                  <v-btn @click="bookDialog=false">닫기</v-btn>
                </v-card-actions>
              </v-card>
            </v-dialog>
          </v-col>
        </v-row>
        <!--        <v-row>-->
        <!--          <v-col-->
        <!--            v-for="(data,key) in 3"-->
        <!--            :key="key"-->
        <!--          >-->
        <!--            <v-card>-->
        <!--              <p>dd</p>-->
        <!--            </v-card>-->
        <!--          </v-col>-->
        <!--        </v-row>-->
        <v-row
          class="overflow-x-auto"
          style="display: grid;grid-auto-flow: column;height:70%;min-width: 400px"
        >
          <V-carousel
            style="width:100%;height:100%;float:left;"
            :cycle="false"
            :continuous="false"
            justify="center"
          >
            <!--            <v-row justify="center" style="width: 100%" >-->
            <v-carousel-item
              v-for="(date,index) in dateArr "
              :id="index+`s`"
              :key="index"
            >
              <DateComponent
                style="width: 80%"
                :date="date"
              />
            </v-carousel-item>
            <!--            </v-row>-->
          </V-carousel>
        </v-row>
      </div>
    </v-row>


  </div>
</template>

<script>
/* eslint-disable */
import DateComponent from '@/components/DateComponent'
import PlannerHeader from "@/components/PlannerHeader";
import MapComponent from "@/components/MapComponent";
import axios from "axios";
/* eslint-disable */

export default {
  name: 'CalenderView',
  components: {
    DateComponent,
    PlannerHeader,
    MapComponent
  },
  data() {
    return {
      schName: 'gg',
      geocoder: '',
      marker: 0,
      startDate: '',
      endDate: '',
      startDateC: new Date(),
      endDateC: new Date(),
      dateArr: [],
      buttonClicked: false,
      selectedTag: '',
      infowindow: {},
      scheduleList: {},
      bookDialog: false,
      books: {},
      bookCategory: "숙소"
    }
  },
  mounted() {
    //가끔 plan Id 가 업다고 뜸 로직 문제..??
    if (this.$store.state.user.planId === 0) {
      console.log("업서영")
      return;
    }

    this.getBook()

    axios.get(`/api/planner/Schedule/${this.$store.state.user.planId}`)
      .then((res) => {
        const scheduleList = res.data.scheduleList
        const plan = res.data.plan


        this.startDate = plan.plan_start
        this.endDate = plan.plan_end
        this.schName = plan.plan_name

        const tempDate = new Date(plan.plan_start);
        const endDate = new Date(plan.plan_end)
        for (let i = 0; tempDate <= endDate; i++) {
          this.dateArr.push(this.dateFormat(tempDate))//tempDate.format("yyyy-MM-dd")
          tempDate.setDate(tempDate.getDate() + 1)
        }
        //없으면 만들기
        const calendar = {};
        calendar["planId"] = plan.plan_id
        calendar["SchName"] = plan.plan_name
        calendar["expectExpenses"] = 1000
        calendar["date"] = {}

        this.dateArr.forEach((it) => {
          calendar.date[it] = new Map();
        })

        //여기서 부터

        let dbDate;
        let dbTime;
        scheduleList.sort((a, b) => parseInt(a.sch_startTime.substring(11, 13)) - parseInt(b.sch_startTime.substring(11, 13)))

        scheduleList.forEach((it) => {
          const partialData = {
            gitem_id: it.gitem_id,
            pl_id: it.pl_id,
            expect_expenses: it.expect_expenses,
            mapX: it.mapX,
            mapY: it.mapY,
            pl_name: it.pl_name,
            address: it.place
          }
          dbDate = it.sch_endTime.substring(0, 10)
          if (it.sch_startTime.substring(14, 16) === "59") dbTime = 100
          else dbTime = parseInt(it.sch_startTime.substring(11, 13))
          calendar.date[dbDate].set(dbTime, partialData)
          //이거 객체화 해서 저장해야하무
        })

        this.$store.commit('calendar/updateCalendar', calendar)
        console.log(calendar)
      })
      .catch((err) => {
        console.error(err)
      })
  },

  computed: {
    calendar() {
      return this.$store.state.calendar.calendar
    },
  },
  methods: {
    gd() {
      console.log(this.bookCategory)
    },

    create() {
      let calendar = []

      calendar["planId"] = this.$store.state.user.planId
      calendar["SchName"] = this.schName
      calendar["expectExpenses"] = 1000
      calendar["date"] = []

      this.dateArr.forEach((it) => {
        const a = [];
        for (let i = 0; i < 24; i++) {
          a.push(" ")
        }
        this.calendar[it] = a

      })
      this.$store.commit('calendar/updateCalendar', calendar)
    },

    save() {
      let data = []
      let temp = {}
      for (let a in this.calendar.date) {
        console.log(this.calendar.date[a])
        console.log(a)
        for (let [key, value] of this.calendar.date[a]) {
          let startHour,
            endHour;

          if (parseInt(key) === 100) {
            startHour = 23
            endHour = 23
            startHour += (":59:00")
            endHour += (":59:00")
          } else {
            if (parseInt(key) < 9) {
              startHour = "0" + key
              endHour = "0" + (parseInt(key) + 1)

            } else if (parseInt(key) === 9) {
              startHour = "09"
              endHour = "10"
            } else {
              startHour = key;
              endHour = (parseInt(key) + 1)
            }
            startHour += (":00:00")
            endHour += (":00:00")
          }

          temp = {
            gitem_id: value.gitem_id,
            plan_id: this.calendar.planId,
            place: value.address,
            pl_id: value.pl_id,
            mapX: value.mapX,
            mapY: value.mapY,
            sch_name: this.schName,
            sch_startTime: a + ` ${startHour}`,
            sch_endTime: a + ` ${endHour}`,
            expect_expenses: value.expect_expenses,
          }
          data.push(temp)
        }


      }

      axios.post('/api/planner/Schedule', data)
        .then(() => {
          alert("success")
        })
        .catch((err) => {
          console.log(err)
        })
    },
    dateFormat(date) {
      let month = date.getMonth() + 1;
      let day = date.getDate();
      let hour = date.getHours();
      let minute = date.getMinutes();
      let second = date.getSeconds();

      month = month >= 10 ? month : '0' + month;
      day = day >= 10 ? day : '0' + day;
      hour = hour >= 10 ? hour : '0' + hour;
      minute = minute >= 10 ? minute : '0' + minute;
      second = second >= 10 ? second : '0' + second;

      return date.getFullYear() + '-' + month + '-' + day;
    },

    async getBook() {
      //일정 기간 범위에 맞는 예약 데이터를 불러와야함 방주 만들때
      const {data} = await axios.get("/api/payment/bookList")
      console.log(data)
      //숙소 처리
      data.productBook.forEach((it) => {
        it.START_DATE = it.START_DATE.substring(0, 10) + " " + it.START_DATE.substring(11, 16)
        it.END_DATE = it.END_DATE.substring(0, 10) + " " + it.END_DATE.substring(11, 16)
      })
      this.books.productBook = data.productBook
      this.books.guideBook = data.guideBook
      console.log(this.books)
      //가이드 처리


    },

    addSchProduct(book) {

      console.log(book)
      const calendar = this.$store.state.calendar.calendar.date

      for (let it in calendar) {
        for (let a of calendar[it]) {
          if (a[1].pl_id === book.PL_ID) console.log(`${it} ${a[0]}`)
        }
      }
      // return;

      const end_date = new Date(book.END_DATE)
      let tempDate = new Date(book.START_DATE)
      const tempDateArr = []
      for (; tempDate < end_date;) {
        tempDateArr.push(this.dateFormat(tempDate))
        tempDate.setDate(tempDate.getDate() + 1);
      }

      //calendar의 selectTime
      const selectTime = {date: "", time: 100}
      console.log(tempDateArr)

      tempDateArr.forEach(it => {
        selectTime.date = it
        this.$store.commit("calendar/updateSelect", selectTime)
        const data = {
          //db에서 값받아올땐 교차해서 넣어햐함... 내가 잘못했나바
          mapX: book.MAPY,
          mapY: book.MAPX,
          pl_id: book.PL_ID,
          pl_name: book.TITLE,
          address: book.ADDR1
        }
        this.$store.commit("calendar/updateCalendarDate", data)
      })
      selectTime.date = ""
      selectTime.time = 30
      //해제
      this.$store.commit("calendar/updateSelect", selectTime)
      alert("추가 되었습니다")


      // this.$store.commit('calendar/updateCalendarDate', )
    },
    addSchGuide(book) {

      const calendar = this.$store.state.calendar.calendar.date

      for (let it in calendar) {
        for (let a of calendar[it]) {
          if (a[1].pl_id === book.PL_ID) console.log(`${it} ${a[0]}`)
        }
      }

      const selectTime = {date: book.GDATE, time: parseInt(book.ST_TIME.substring(0, 2))}
      this.$store.commit("calendar/updateSelect", selectTime)

      const data = {
        mapX: book.MAPY,
        mapY: book.MAPX,
        pl_id: book.PL_ID,
        pl_name: book.TITLE,
        address: book.ADDR1
      }
      this.$store.commit("calendar/updateCalendarDate", data)

      selectTime.date = ""
      selectTime.time = 30
      //해제
      this.$store.commit("calendar/updateSelect", selectTime)
      alert("추가 되었습니다")
    }
  }
}
</script>
<style scoped>
* {
  margin: 0 auto;
}

.map {
  width: calc(70% - 45px);
  height: 850px;
  /*min-width: 600px;*/
  position: relative;
  overflow: hidden;
  margin-right: 35px;
  margin-bottom: 100px;
  border-radius: 10px;
}

.calendar {
  width: 80%;
  overflow: hidden;
  padding-left: 10%;
  display: inline-block;
  /*text-align: center;*/
  /*padding: 50px;*/
}

.calendarDate {
  font-size: 25px;
  border: 3px solid;
  border-radius: 4px;
  margin-right: 5px
}

.col {
  text-align: center;
}
</style>

