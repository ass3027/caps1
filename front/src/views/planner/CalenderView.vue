<template>
  <div id="이재영시발럼">
    <PlannerHeader/>
    <div style="width:40%;height:100%;position:relative;overflow:hidden;float:left">
      <MapComponent
        @mapDataTransfer="applyMapData"
      />
    </div>

    <div
      id="plan"
      style="float:right;width:60%;height:100%;overflow-x:auto;"
    >
      <input
        v-model="startDate"
        type="date"
      >
      <input
        v-model="endDate"
        type="date"
      >
      <v-text-field v-model="schName" placeholder="일정이름" style="width:30%"></v-text-field>
      <v-btn @click="apply()">
        Apply
      </v-btn>
      <v-btn @click="save()">
        save
      </v-btn>
      <div
        v-if="buttonClicked"
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


  name      : 'CalenderView',
  components: {
    DateComponent,
    PlannerHeader,
    MapComponent
  },
  data() {
    return {
      schName: '',
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

    }
  },
  mounted() {
    //this.startDate = new Date("2022-03-01T00:00:00.000Z");
    //this.endDate = new Date("2022-03-11T00:00:00.000Z")
    //this.apply();

  },
  computed:{
    calendar() {
      return this.$store.state.calendar.calendar
    }
  },
  methods: {
    apply() {
      let calendar = []
      this.dateArr = []
      this.buttonClicked = true
      this.startDateC = new Date(this.startDate)
      this.endDateC = new Date(this.endDate)
      if(this.schName === '') {
        alert("이름이 없습니다")
        return;
      }

      if (this.startDateC > this.endDateC) {
        alert("잘못된 날짜 설정 입니다");
        return;
      }

      if (this.startDateC >= this.endDateC) return;

      const tempDate = this.startDateC;
      for (let i = 0; tempDate <= this.endDateC; i++) {
        this.dateArr.push( this.dateFormat(tempDate))//tempDate.format("yyyy-MM-dd")
        tempDate.setDate(tempDate.getDate() + 1)
      }


      calendar["planId"] = this.$store.state.user.planId
      calendar["SchName"] = this.schName
      calendar["expectExpenses"] = 1000
      calendar["date"]=[]
      this.dateArr.forEach( (it) => {
        calendar.date[it] = new Map();
      })
      console.log(calendar)
      this.calendar.forEach((key,data) => {

        console.log(key + ", data:" + data)
        // for (let variable in v) {
        //   console.log(variable + "dd" + v[variable][3])
        // }

      })

      this.$store.commit('calendar/updateCalendar', calendar)

    },

    applyMapData(mapData) {
      console.log("calendar: " + mapData)
      this.$store.dispatch('calendar/changeCalendarDate',mapData)
    },

    save() {
      console.log(this.calendar)
      let data = []
      let temp = {}
      for ( let a in this.calendar.date) {
        console.log(this.calendar.date[a])
        for ( let [key,value] of this.calendar.date[a]){
          console.log("11")
          temp = {
            gitem_id : null,
            plan_id : this.calendar.planId,
            place : value,
            sch_name : this.schName,
            sch_startTime: parseInt(key),
            sch_endTime: parseInt(key)+1,
            expect_expenses : this.calendar.expectExpenses,
          }
          data.push(temp)
        }


      }
      console.log(data)

      axios.post('/api/schedule',data)
      .then ((res) => {
        console.log(res)
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

