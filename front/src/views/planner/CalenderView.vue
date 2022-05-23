<template>
  <div id="이재영시발럼">
    <PlannerHeader />
    <v-container style="padding-left:10px">
      <div style="width:40%;height:100%;position:relative;overflow:hidden;float:left">
        <MapComponent
          @mapDataTransfer="applyMapData"
        />
      </div>

      <div
        id="plan"
        style="float:right;width:60%;height:100%;overflow-x:auto;"
      >
        <v-col>
          <input
            v-model="startDate"
            type="date"
          >
          <input
            v-model="endDate"
            type="date"
          >
          <v-btn>
            날짜 변경
          </v-btn>
        </v-col>

        <v-text-field
          v-model="schName"
          placeholder="일정이름"
          style="width:30%"
        />
        <v-btn @click="create()">
          create
        </v-btn>
        <v-btn @click="save()">
          save
        </v-btn>
        <div
          :style="{width:'calc(100%)',height:'100%',overflowX:'auto'}"
        >
          <DateComponent
            v-for="(date,index) in dateArr "
            :id="index+`s`"
            :key="index"
            :date="date"
            :style="{width:'30%',float:'left' }"
          />
        </div>
      </div>
    </v-container>
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
      geocoder     : '',
      marker       : 0,
      startDate    : '',
      endDate      : '',
      startDateC   : new Date(),
      endDateC     : new Date(),
      dateArr : [],
      buttonClicked: false,
      selectedTag  : '',
      infowindow   : {},
      scheduleList : {},


    }
  },
  mounted() {
    //가끔 plan Id 가 업다고 뜸 로직 문제..??
    console.log(this.$store.state.user.planId)
    axios.get(`/api/planner/Schedule/${this.$store.state.user.planId}`)
      .then( (res)=>{
        console.log(res)
        const scheduleList=res.data.scheduleList
        const plan = res.data.plan

        console.log(scheduleList)

        this.startDate = plan.plan_start
        this.endDate = plan.plan_end
        this.schName = plan.plan_name

        const tempDate = new Date(plan.plan_start);
        const endDate = new Date(plan.plan_end)
        for (let i = 0; tempDate <= endDate; i++) {
          this.dateArr.push( this.dateFormat(tempDate))//tempDate.format("yyyy-MM-dd")
          tempDate.setDate(tempDate.getDate() + 1)
        }
        console.log(this.dateArr)
        //없으면 만들기
        if(scheduleList.length === 0) {
          this.create()
          return;
        }
        const calendar = {};
        calendar["planId"] = plan.plan_id
        calendar["SchName"] = plan.plan_name
        calendar["expectExpenses"] = 1000
        calendar["date"]=[]

        this.dateArr.forEach( (it)=>{
          calendar.date[it]= new Map();
        })
        //여기서 부터
        scheduleList.forEach( (it)=>{

          console.log(it.sch_endTime.substring(0,10))
            calendar.date[it.sch_endTime.substring(0,10)].set(
              parseInt( it.sch_startTime.substring(12,13) ),it.place)
        })

        this.$store.commit('calendar/updateCalendar',calendar)
        console.log(1)
      })
      .catch( (err)=>{
        console.error(err)
      })
  },

  computed:{
    calendar() {
      return this.$store.state.calendar.calendar
    },
  },
  methods: {

    create() {
      let calendar = []

      //플래너 생성때 쓸내용
      // if(this.schName === '') {
      //   alert("이름이 없습니다")
      //   return;
      // }
      //
      // if (this.startDateC > this.endDateC) {
      //   alert("잘못된 날짜 설정 입니다");
      //   return;
      // }

      calendar["planId"] = this.$store.state.user.planId
      calendar["SchName"] = this.schName
      calendar["expectExpenses"] = 1000
      calendar["date"]=[]

      this.dateArr.forEach( (it) => {
        console.log(it)
        const a = [];
        for (let i = 0; i < 24; i++) {
          a.push(" ")
        }
        this.calendar[it] = a

      })

      console.log(calendar)
      // this.calendar.forEach((key,data) => {
      //   console.log(key + ", data:" + data)
      // })

      this.$store.commit('calendar/updateCalendar', calendar)

    },
    // selecting(tag) {
    //   const check = this.sameCheck(tag);
    //   if (check) this.selectedTag = tag;
    //   console.log(tag);
    //   return check
    // },
    // sameCheck(tag) {
    //   return this.selectedTag !== tag;
    // },

    applyMapData(mapData) {
      console.log("calendar: " + mapData)
      this.$store.commit('calendar/updateCalendarDate',mapData)
    },

    save() {
      console.log(this.calendar)
      let data = []
      let temp = {}
      for ( let a in this.calendar.date) {
        console.log(this.calendar.date[a])
        console.log(a)
        for ( let [key,value] of this.calendar.date[a]){
          let startHour
          let endHour;
          if(parseInt(key)<9){
            startHour= "0"+key
            endHour = "0"+(parseInt(key)+1)

          }else if(parseInt(key)===9){
            startHour= "09"
            endHour = "10"
          }else {
            startHour = key;
            endHour = (parseInt(key)+1)
          }
          temp = {
            gitem_id : null,
            plan_id : this.calendar.planId,
            place : value,
            sch_name : this.schName,
            sch_startTime: a + ` ${startHour}:00:00`,
            sch_endTime: a + ` ${endHour}:00:00`,
            expect_expenses : this.calendar.expectExpenses,
          }
          console.log(a + ` ${key}:00:00`)
          data.push(temp)
        }


      }
      console.log(data)

      axios.post('/api/planner/Schedule',data)
      .then ((res) => {
        console.log(res)
        alert("success")
      })
      .catch ( (err)=> {
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
<style>

</style>

