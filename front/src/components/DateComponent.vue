<template>
  <div>
    <v-row style="height:400px">
      <v-col>
        <h3 class="text-center">{{ date }}</h3>
        <v-card
          v-for="(key,index) in plan.keys()"
          :id="index"
          :key="index"
          :class="{dd:true,selecting:selectedTime===key}"
          @click="select(key)"
        >
          <div>{{ key }}시</div>
          <div>
            {{ plan.get(key) }}
          </div>
        </v-card>
      </v-col>
    </v-row>
    <v-row>
      <v-dialog
        v-model="dialog"
        width="500"
      >
        <template v-slot:activator="{ on, attrs}">
          <v-btn
            color="red lighten-2"
            dark
            v-bind="attrs"
            v-on="on"
          >
            일정추가
          </v-btn>
        </template>

        <v-card>
          <v-card-title>
            시간선택
          </v-card-title>

          <v-card-text>
            <v-select
              :items="availableTimeList"
              v-model="addTime"
            />
          </v-card-text>

          <v-divider/>
          <v-card-actions>
            <v-btn
              color="primary"
              text
              @click="dialog = false"
            >
              닫기
            </v-btn>
            <v-btn
              color="primary"
              text
              @click="add"
            >
              추가
            </v-btn>
          </v-card-actions>

        </v-card>
      </v-dialog>
      <v-btn
        @click="deleting()"
      >

        일정삭제
      </v-btn>
    </v-row>
  </div>
</template>

<script>
export default {
  name: "DateComponent",
  props: {
    date: {
      type: String,
      required: true
    },
  },
  created() {
    this.updatePlan()
    this.updateAvailableTimeList()

  },
  data() {
    return {
      height: 500,
      location: '',
      selectedTime: 30,
      selectedTime2: '',
      selectedArr: [],
      length: 0,
      selectedTag: '',
      style: "width:100,height:2,border:2,top:selected",
      dialog: false,
      addTime:0,
      availableTimeList:[0,1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23],
      plan:{},
    }
  },
  methods: {
    updatePlan: function () {
      if(this.$store.state.calendar.calendar.date !==undefined) this.plan = this.$store.state.calendar.calendar.date[this.date]
      console.log(this.plan)
    },
    select(index) {
      //console.log(e.target.id);
      const a = {
        "date": this.date,
        "time": index
      }
      this.$store.commit('calendar/updateSelect', a)
      console.log(this.$store.state.calendar.selectTime)
      console.log(this.$store.state.calendar.selectDate)
      console.log(this.plan)
      this.selectedTime = index
    },
    updateAvailableTimeList: function () {
      console.log(this.plan)
      if(this.plan === {}) return;
      const arr = []
      for (let key of this.plan.keys()) {
        arr.push(key)
      }
      let available = []
      for (let key = 0; key < 24; key++) {
        available.push(key)
      }
      available = available.filter((it) => {
        return !(arr.includes(it));
      })
      available.forEach((it) => it + "시")

      this.availableTimeList =  available;
    },
    add(){
      const a = {
        "date": this.date,
        "time": this.addTime
      }
      this.$store.commit('calendar/updateSelect', a)
      this.$store.commit('calendar/updateCalendarDate','')
      this.updateAvailableTimeList()
      this.updatePlan();
      this.dialog = false
    },
    deleting(){
      this.$store.commit("calendar/deleteCalendarDate")
      //this.$store.commit("calendar/reload")
      //const a = this.plan
    }
  }
}
</script>

<style scoped>
.dd {
  border-style: solid;
  height: 8vh;
  margin-bottom: 5px;

}

.selecting {
  color: #81ecec;
}
</style>
