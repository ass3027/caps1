<template>
  <v-container>
    <v-row justify="center">
      <v-col cols="8">
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

          <v-card
            v-for="(schedule,index,ii) in calendar.date"
            :key="index"
            class="pa-5">
            <v-row
            >
              <v-col cols="12">
                <div
                  v-if="schedule.size!==0"
                  style="width:899px;height:750px;position:relative;overflow:hidden;float:left;border: solid 10px"
                >
                  <MapComponent

                    :schedule="schedule"
                    :index="index"
                  />
                </div>
              </v-col>

            </v-row>
            <v-row>
              <v-col cols="5">
                <h2>
                  {{ ii + 1 }}번째 날
                </h2>
                <h3
                  v-for="(s,i) in schedule"
                  :key="i"
                >
                  {{ s[1].pl_name }}->
                  <!--              map데이터형식이라서 s[0]은키 s[1]값 가져오는거 거기서 pl_name-->
                </h3>

              </v-col>

            </v-row>
          </v-card>


          <div v-if="$store.state.user.userId==share.user_id">
            <v-btn @click="edit">
              수정
            </v-btn>
            <v-btn @click="del">
              삭제
            </v-btn>
          </div>
          <h2 class="mt-5">복제된 횟수 : {{ share.share_count }}</h2>
          <v-btn @click="copyPlanner">
            일정 복제하기
          </v-btn>

          <h2>추천 수 : {{ recommends }}</h2>
          <v-btn @click="recommend">추천하기</v-btn>
        </v-card>

        <!-- 댓글-->
        <v-card class="ma-5 pa-5">
          댓글 작성
          <v-text-field v-model="comment" @keyup.enter="postComment" placeholder="댓글을 써보세요"></v-text-field>
          <v-btn @click="postComment">작성</v-btn>
        </v-card>
        <v-card v-for="(comment,i) in comments" :key="i" class="ma-5 pa-5">
          <v-row justify="" class="mt-2">
            <v-col cols="10" align-self="center">
              <p>{{ comment.comment_contents }}</p>

            </v-col>
            <v-col cols="2">
              <v-row>
                <p>{{ comment.created_time }}</p>
              </v-row>

              <v-row>
                <v-col cols="6">
                  <p>{{ comment.user_id }}</p>
                </v-col>

                <v-col cols="6">
                  <v-btn
                    v-if="comment.user_id==$store.state.user.userId"
                    @click="delComment(comment)"
                    class="mb-5"
                  >
                    삭제
                  </v-btn>
                </v-col>

              </v-row>


            </v-col>
          </v-row>
        </v-card>

      </v-col>
    </v-row>
  </v-container>
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
      calendar: {},
      preference: '',
      temp: '',
      recommends: '',
      comment: '',
      comments: []
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

        this.getRecommends()
        this.getComments()

        axios.get("/api/getPreference", {params: {user_id: this.share.user_id}})
          .then((res) => {
            this.preference = res.data
          })


        axios.get(`/api/planner/Schedule/${this.share.plan_id}`)
          .then((res) => {
            console.log('DF')
            const scheduleList = res.data.scheduleList
            this.calendar["expectExpenses"] = 1000
            this.calendar["date"] = {}

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
              if (this.calendar.date[it.sch_endTime.substring(0, 10)] === undefined) {
                this.calendar.date[it.sch_endTime.substring(0, 10)] = new Map()
              }
              this.calendar.date[it.sch_endTime.substring(0, 10)].set(
                parseInt(it.sch_startTime.substring(11, 13)), partialData)
              //이거 객체화 해서 저장해야하무
            })
            // console.log('test', this.calendar.date['2022-04-05'].get('2'))
            this.temp = this.share.share_title;
            this.share.share_title = null;
            this.share.share_title = this.temp;

          })

      })

  },
  methods: {
    copyPlanner() {
      console.log("여기요")
      console.log(this.$store.state.user.userId)
      if(this.$store.state.user.userId=='anonymousUser'||this.$store.state.user.userId==''){
        alert('로그인 후 이용가능합니다')
        return
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
        .catch((err) => {
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
          this.$router.push({name: 'share'})
        })
        .catch((err) => {
          console.log(err)
        })
    },
    recommend() {
      if(this.$store.state.user.userId=='anonymousUser'||this.$store.state.user.userId==''){
        alert('로그인 후 이용가능합니다')
        return
      }

      axios.post('/api/recShare', {}, {params: {share_id: this.share.share_id, user_id: this.$store.state.user.userId}})
        .then((res) => {
          if(res.data!="") alert(res.data)
          this.getRecommends()
        })
        .catch(() => {
          alert("이미 추천을 하였습니다.")
        })
    },
    getRecommends() {
      axios.get('/api/getShareRec', {params: {share_id: this.share.share_id}})
        .then((res) => {
          this.recommends = res.data;
        })
    },
    postComment() {
      if(this.$store.state.user.userId=='anonymousUser'||this.$store.state.user.userId==''){
        alert('로그인 후 이용가능합니다')
        return
      }

      const comment = {
        share_id: this.share.share_id,
        user_id: this.$store.state.user.userId,
        comment_contents: this.comment
      }
      axios.post('/api/postComment', comment)
        .then(() => {
          this.getComments()
          this.comment=''
        })
    },
    getComments() {
      axios.get('/api/getComments', {params: {share_id: this.share.share_id}})
        .then((res) => {

          //날짜변환
          let now = new Date()
          let today = new Date(now.toLocaleDateString());
          res.data.forEach(i => {
            var thisDate = new Date(i.created_time)
            if (today > thisDate) {
              i.created_time = i.created_time.substring(0, 10)
            } else {
              i.created_time = thisDate.toString().substring(16, 21)
            }
          })
          this.comments = res.data;
          console.log(this.comments)

        })
    },
    delComment(comment){
      axios.delete('/api/delShareComment',{params:{comment_id:comment.comment_id}})
      .then(()=>{
        alert("삭제성공")
        this.getComments()
      })
      .catch(()=>{
        alert("삭제실패")
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
