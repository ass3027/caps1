<template>
  <div
    id="lll"
    style="width: 70%; min-width:1050px;"
  >
    <PlannerHeader/>
    <div class="map">
      <MapComponent
        index="00"
      />
    </div>

    <div
      id="plan"
      style="float:right;width:35%; height: 750px;margin-right: 10%;margin-bottom: 100px;min-width: 600px;"
    >
      <v-row>
        <v-col cols="3">
          <h3>시작 일자</h3>
        </v-col>
        <v-col cols="3">
          <h3>종료 일자</h3>
        </v-col>
      </v-row>
      <v-row>
        <v-col style="width:100%;height: 10%;">
          <input
            v-model="startDate"
            type="date"
            @click.prevent
          >
          <input
            v-model="endDate"
            type="date"
            @click.prevent
          >
        </v-col>
      </v-row>
      <v-text-field
        v-model="schName"
        placeholder="일정이름"
        style="width:30%;height: 10%;"
      />
      <v-row style="width:100%;height: 10%;">
        <v-col>
          <!--            <v-btn @click="create()">-->
          <!--              create-->
          <!--            </v-btn>-->
          <v-btn @click="save()">
            save
          </v-btn>
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
          :cycle=false
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
        <!--          <v-col-->
        <!--            v-for="(date,index) in dateArr "-->
        <!--            :id="index+`s`"-->
        <!--            :key="index"-->
        <!--            style="width:300px;height:600px;float:left;"-->
        <!--            class="overflow-y-auto"-->
        <!--            sm="20"-->
        <!--          >-->
        <!--            <DateComponent-->
        <!--              :date="date"-->
        <!--            />-->
        <!--          </v-col>-->
      </v-row>
    </div>
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
    }
  },
  mounted() {
    //가끔 plan Id 가 업다고 뜸 로직 문제..??
    if (this.$store.state.user.planId === 0) {
      console.log("업서영")
      return;
    }
    axios.get(`/api/planner/Schedule/${this.$store.state.user.planId}`)
      .then((res) => {
        console.log(res)
        const scheduleList = res.data.scheduleList
        const plan = res.data.plan

        console.log(scheduleList)

        this.startDate = plan.plan_start
        this.endDate = plan.plan_end
        this.schName = plan.plan_name

        const tempDate = new Date(plan.plan_start);
        const endDate = new Date(plan.plan_end)
        for (let i = 0; tempDate <= endDate; i++) {
          this.dateArr.push(this.dateFormat(tempDate))//tempDate.format("yyyy-MM-dd")
          tempDate.setDate(tempDate.getDate() + 1)
        }
        console.log(this.dateArr)
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
          calendar.date[it.sch_endTime.substring(0, 10)].set(
            parseInt(it.sch_startTime.substring(11, 13)), partialData)
          //이거 객체화 해서 저장해야하무
        })

        this.$store.commit('calendar/updateCalendar', calendar)
        console.log(1)
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

      console.log(calendar)

      this.$store.commit('calendar/updateCalendar', calendar)

    },

    save() {
      console.log(this.calendar)
      console.log(this.$store.state.calendar.calendar)
      let data = []
      let temp = {}
      for (let a in this.calendar.date) {
        console.log(this.calendar.date[a])
        console.log(a)
        for (let [key, value] of this.calendar.date[a]) {
          let startHour,
            endHour;
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
          temp = {
            gitem_id: value.gitem_id,
            plan_id: this.calendar.planId,
            place: value.address,
            pl_id: value.pl_id,
            mapX: value.mapX,
            mapY: value.mapY,
            sch_name: this.schName,
            sch_startTime: a + ` ${startHour}:00:00`,
            sch_endTime: a + ` ${endHour}:00:00`,
            expect_expenses: value.expect_expenses,
          }
          console.log(a + ` ${key}:00:00`)
          data.push(temp)
        }


      }
      console.log(data)

      axios.post('/api/planner/Schedule', data)
        .then((res) => {
          console.log(res)
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
    }
  }
}
</script>
<style scoped>
* {
  margin: 0 auto;
}

.map {
  width: 53%;
  height: 850px;
  min-width: 600px;
  position: relative;
  overflow: hidden;
  float: left;
  padding-right: 10px;
  margin-bottom: 100px;
  border-radius: 10px;
}
</style>

