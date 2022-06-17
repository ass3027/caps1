<template>
  <v-container :class="{border:$store.state.calendar.selectDate===date}">
    <v-row  >
      <v-col width="300" align="center">
        <h2 class="text-center">
          {{ date }}
        </h2>
        <template
          v-if="plan.size!==undefined"

        >
          <div style="overflow-y:scroll;width:400px;height: 300px">
          <v-dialog
            v-for="(key,index) in planKeys"
            :id="index"
            :key="index"
            v-model="detailDialog[index]"
            persistent
            :retain-focus="false"
            style="background: black"
          >
            <template
              #activator="{ attrs}"
            >
              <v-card
                v-bind="attrs"
                :class="{dd:true,selecting:selectedTime===key}"
                width="300"
                @click="select(key,index)"
              >
                <div><h3>{{ key }}시</h3></div>
                <div>
<!--                  {{detailDialog[index]}}-->
<!--                  {{ plan.get(key).mapX }}-->
<!--                  {{ plan.get(key).mapY }}-->
                  {{ plan.get(key).address }}
                </div>
              </v-card>
            </template>
            <template>
              <v-card>
                <v-card-title>
                  일정 상세보기
                </v-card-title>

                <v-card-text>
                  <p>{{ plan.get(key) }}</p>
                  <p>주소 : {{plan.get(key).address}}</p>
                  <p>장소 : {{plan.get(key).pl_name}}</p>
                  <SearchPlace
                    @childEvent="selectPlace"
                  />
                  <p>관광 상품 : {{plan.get(key).gitem_id}}</p>
                  <p>예상 요금 :
                    <v-text-field style="width:50%" v-model="plan.get(key).expect_expenses"/>
                  </p>
                </v-card-text>

                <v-divider />
                <v-card-actions>
                  <v-btn
                    color="primary"
                    text
                    @click="closs(index)"
                  >
                    닫기
                  </v-btn>
                  <v-btn
                    color="primary"
                    text
                    @click="apply(index)"
                  >
                    적용
                  </v-btn>
                </v-card-actions>
              </v-card>
            </template>
          </v-dialog>
          </div>
        </template>
      </v-col>
    </v-row>
    <v-row justify="center">
      <v-col justify="center" align="center"> <!--이런게 있긴함  -->
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
                v-model="addTime"
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
      </v-col>
    </v-row>
  </v-container>
</template>

<script>
import {EventBus} from "@/eventBus/eventBus";
import SearchPlace from "@/components/SearchPlace";

export default {
  name: "DateComponent",
  components: {SearchPlace},
  props: {
    date: {

      type: String,
      required: true
    },
  },
  data() {
    return {
      clicked : null,
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
      detailDialog:[],
      placeDialog:false,
      planKeys:[],
      eachDistance:[]
    }
  },
  created() {
    this.updatePlan()
    this.updateAvailableTimeList()
    EventBus.$on('updateCalendar', ()=>{
      this.updatePlan()
      this.updateAvailableTimeList()
    })
    EventBus.$on("passDistance",(eachDistance)=>{
      this.eachDistance = eachDistance
      console.log(this.eachDistance)
    })
  },
  methods: {
    closs(index){
      this.detailDialog.splice(index,1,false)
      this.updatePlan()
      //this.$nextTick( ()=>{})
    },
    updatePlan: function () {
      //이게 맞나?
      if(this.$store.state.calendar.calendar.date !==undefined) {
        this.plan = this.$store.state.calendar.calendar.date[this.date]
        console.log(this.plan)
        this.detailDialog = []
        for(let i of this.plan){
          this.detailDialog.push(false)
        }
        this.updatePlanKeys()
      }
    },
    updatePlanKeys(){
      this.planKeys.splice(0,this.planKeys.length)
      for(let i of this.plan.keys()){
        this.planKeys.push(i)
      }
      this.planKeys = this.planKeys.sort()
      //console.log(this.planKeys)
    },
    select(key,index) {
      const selected = this.$store.state.calendar
      this.updatePlanKeys()
      if( this.date === selected.selectDate && key === selected.selectTime){
        this.detailDialog.splice(index,1,true)
        return;
      }

      const a = {
        "date": this.date,
        "time": key
      }
      this.$store.commit('calendar/updateSelect', a)
      this.selectedTime = key
      EventBus.$emit('updateDate',this.date) // 경로

    },
    updateAvailableTimeList: function () {

      if(this.plan.size === undefined) return;
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
      this.$store.commit('calendar/updateCalendarDate', undefined)

      this.updateAvailableTimeList()
      this.updatePlan();
      this.dialog = false
    },
    deleting(){
      this.$store.commit("calendar/deleteCalendarDate")
      //this.$store.commit("calendar/reload")
      //const a = this.plan
    },
    apply(index){
        console.log(this.plan)
        console.log(this.plan.get(this.selectedTime))
        this.$store.commit('calendar/updateCalendarDetail',this.plan.get(this.selectedTime))
        this.detailDialog.splice(index,1,false)
    },
    selectPlace(place){
      console.log(place)
      const temp = this.plan.get(this.selectedTime)
      temp.pl_id = place.pl_id
      temp.pl_name = place.title
      temp.mapX= place.mapy
      temp.mapY= place.mapx
      temp.address = place.addr1
      this.plan.delete(this.selectedTime)
      this.updatePlanKeys()
      this.plan.set(this.selectedTime,temp)

      console.log(this.plan.get(this.selectedTime))
      this.updatePlanKeys()
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

.border {
  border: solid #5eaf13;
  background: #5eaf13;
}

.selecting {
  color: #81ecec;
}
.selecting h3{
  transform :scale(1.5);
}
</style>
