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
      <button @click="apply()">
        Apply
      </button>
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
      geocoder     : '',
      marker       : 0,
      startDate    : '',
      endDate      : '',
      startDateC   : new Date(),
      endDateC     : new Date(),
      calendar      : [],
      dateArr : [],
      buttonClicked: false,
      selectedTag  : '',
      infowindow   : {},

    }
  },
  mounted() {
    //this.startDate = new Date("2022-03-01T00:00:00.000Z");
    //this.endDate = new Date("2022-03-11T00:00:00.000Z")
    this.apply();

  },
  methods: {
    apply() {
      this.calendar = []
      this.dateArr = []
      this.buttonClicked = true
      this.startDateC = new Date(this.startDate)
      this.endDateC = new Date(this.endDate)
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



      this.dateArr.forEach( (it) => {
        console.log(it)
        const a = [];
        for (let i = 0; i < 24; i++) {
          a.push(" ")
        }
        this.calendar[it] = a

      })
      console.log(this.calendar)
      this.calendar.forEach((key,data) => {

        console.log(key + ", data:" + data)
        // for (let variable in v) {
        //   console.log(variable + "dd" + v[variable][3])
        // }

      })

      this.$store.commit('calendar/updateCalendar', this.calendar)
      //var tags = []
      //for
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

