<template>
  <div>
    <v-row>
      <v-col>
        <h4>{{ date }}</h4>
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
        <template #activator="{ on, attrs}">
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
            />
          </v-card-text>

          <v-divider />
          <v-card-actions>
            <v-btn
              color="primary"
              text
              @click="dialog = false"
            >
              닫기
            </v-btn>
          </v-card-actions>
        </v-card>
      </v-dialog>
      <v-btn>
        일정추가
      </v-btn>
      <v-btn
        @click="exper"
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
      dialog:false,
    }
  },
  computed: {
    plan: function () {
      return this.$store.state.calendar.calendar.date[this.date]
    },
    availableTimeList: function(){
      const arr = []
      for(let key of this.plan.keys()){
        arr.push(key)
      }
      const available = []
      for(let key in 24){
        available.push(key)
      }
      console.log(available)
      available.filter( (it)=>{
        return arr.find(it) === undefined;

      })
      console.log(available)
      return available
    }

  },
  methods: {

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
    exper: function(){
      const arr = []
      for(let key of this.plan.keys()){
        arr.push(key)
      }
      const available = []
      for(let key=0;key<24;key++){
        available.push(key)
      }
      console.log(available)
      const result = available.filter( (it)=>{
        return arr.find(it) === undefined;

      })
      console.log(result)
      return result
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
