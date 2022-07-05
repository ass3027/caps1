<template>
  <div>
    <v-card class="ma-5 pa-5">
      <h2>
        {{ share.share_title }}[{{ $route.params.id }}]
      </h2>
      <p>
        #{{ share.share_place }} #{{ preference }}
      </p>
      <p>
        {{ share.share_contents }}
      </p>
      <div>
        <img
          v-for="photo in pictures"
          :key="photo.pic_name"
          :src="'/api/photo/'+photo.pic_name"
        >
      </div>

      <ul>
        <!--      <li-->
        <!--        v-for="schedule in schedules"-->
        <!--        :key="schedule.sch_number"-->
        <!--      >-->
        <!--        &lt;!&ndash;@todo4 cheack&ndash;&gt;-->
        <!--        {{ schedule }}-->
        <!--      </li>-->
        <li>
          <v-row
            v-for="(schedule,index) in calendar.date"
            :key="index"
          >
            <div
              v-if="schedule.size!==0"
              style="width:899px;height:750px;position:relative;overflow:hidden;float:left;border: solid 10px"
            >
              <MapComponent

                :schedule="schedule"
                :index="index"
              />
            </div>
            <h3
              v-for="(s,i) in schedule"
              :key="i"
            >
              {{ s[1].pl_name }}->
            </h3>
          </v-row>
        </li>
      </ul>


      <div v-if="$store.state.user.userId==share.user_id">
        <v-btn @click="edit">
          수정
        </v-btn>
        <v-btn @click="del">
          삭제
        </v-btn>
      </div>
      <v-btn @click="copyPlanner">
        일정 복제하기
      </v-btn>

      <h2>공유된 횟수:{{ share.share_count }}</h2>
    </v-card>
    {{ calendarX }}

    <v-btn @click="calendarX.push(1)&&calendarX.splice(0,1)">
      지도에서보기
    </v-btn>
  </div>
</template>

<script>
import axios from 'axios';
import MapComponent from "@/components/MapComponent";

export default {
  name: "PlannerShareDetailsView",
  components: {MapComponent},
  data() {
    return {
      share: {},
      schedules: [],
      pictures: [],
      calendar:{},
      calendarX:[],
      preference:''

    }
  },
  mounted() {
    axios.get('/api/getSharePostDetails', {
      params: {
        share_id: this.$route.params.id
      }
    })
      .then((res) => {
        this.tableSets = res.data;
        console.log(res.data);
        this.share = res.data[0]
        this.schedules = res.data[1]
        this.pictures = res.data[2]

        axios.get("/api/getPreference",{params:{user_id:this.share.user_id}})
          .then((res)=>{
            this.preference=res.data
          })


        axios.get(`/api/planner/Schedule/${this.share.plan_id}`)
          .then( (res)=>{
            console.log('DF')
            const scheduleList=res.data.scheduleList
            this.calendar["expectExpenses"]=1000
            this.calendar["date"]= {}

            scheduleList.forEach( (it)=>{
              const partialData = {
                gitem_id:it.gitem_id,
                pl_id:it.pl_id,
                expect_expenses: it.expect_expenses,
                mapX:it.mapX,
                mapY:it.mapY,
                pl_name:it.pl_name,
                address:it.place
              }
              if(this.calendar.date[it.sch_endTime.substring(0,10)]===undefined){
                this.calendar.date[it.sch_endTime.substring(0,10)] = new Map()
              }
              this.calendar.date[it.sch_endTime.substring(0,10)].set(
                parseInt( it.sch_startTime.substring(11,13) ), partialData)
              //이거 객체화 해서 저장해야하무
            })
            // console.log('test', this.calendar.date['2022-04-05'].get('2'))


          })

      })

  },
  methods: {
    copyPlanner() {
      console.log("여기요")
      console.log(this.$store.state.user.userId)
      if (!this.$store.state.user.userId) {
        alert("로그인을 해주세요");
        return;
      }
      axios.get('/api/copyPlanner', {
        params: {
          share_id: this.share.share_id,
          plan_id: this.share.plan_id,
          user_id: this.$store.state.user.userId,
          share_title: this.share.share_title
        }
      })
        .then((res) => {
          console.log(res)
          alert("복제성공")
        })
      .catch((err)=>{
        console.log(err)
      })
    },
    edit() {
      this.$router.push({
        name: 'shareEdit', params: {
          share: this.share,
          schedules: this.schedules,
          pictures: this.pictures

        }
      })
    },
    del() {
      alert("delete")
      axios.delete('/api/delSharePlan', {
        params: {
          share_id: this.share.share_id
        }
      })
        .then(res => {
          alert(res.data)
          this.$router.push({name: 'share'})
        })
        .catch((err) => {
          console.log(err)
        })
    }
  }
}
</script>

<style scoped>
img {
  border: 1px solid;
  width: 200px;
  height: 200px;
}

</style>
