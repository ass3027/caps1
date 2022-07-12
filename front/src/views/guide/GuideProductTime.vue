<template>
  <v-container
    class="joinGItem"
    style="text-align: center; width: 70%"
  >
    <v-row>
      <v-col align="center">
        <v-card style="width: 60%; margin: 50px;padding: 50px">
          <h2>가이드 시간 등록</h2>
          <h2>{{ gitem_id }}</h2>
          <div class="textForm">
            <v-text-field
              v-model="st_time"
              name="guideIntro"
              type="time"
              class="intro"
              placeholder="시작시간"
              style="width: 60%; margin: 10px"
            />
          </div>
          <div class="textForm">
            <v-text-field
              v-model="end_time"
              name="guideIntro"
              type="time"
              class="intro"
              data-placeholder="종료시간"
              required
              aria-required="true"
              style="width: 60%; margin: 10px"
            />
          </div>
          <div class="textForm">
            <v-text-field
              v-model="gdate"
              name="guideIntro"
              type="date"
              class="intro"
              data-placeholder="날짜"
              required
              aria-required="true"
              style="width: 60%; margin: 10px"
            />
          </div>

          <v-btn
            class="btn"
            style="width: 60%; margin:10px"
            @click="timeRegister()"
          >
            JOIN
          </v-btn>
        </v-card>
      </v-col>
    </v-row>
  </v-container>
</template>

<script>
import axios from "axios";

export default {
  name:'GuideProductTime',
  props: {
    gid:{
      type: String
    }
  },
  data(){
    return{
      gitem_id: this.$route.params.gitem_id,
      st_time:'',
      end_time:'',
      book_whether:'',
      gdate:''
    }
  },
  mounted() {
    console.log("GID", this.$route.params.gitem_id)

  },
  methods:{

    timeRegister(){


      var sendform= new FormData();

      sendform.append('gitem_id',this.gitem_id);
      sendform.append('st_time', this.st_time);
      sendform.append('end_time', this.end_time);
      sendform.append('gdate', this.gdate)


      axios({
        method:'post',
        url:'/api/gitjoin',
        headers:{
          'Content-Type': 'multipart/form-data'
        },
        data: sendform,
      }).then((res)=>{
        console.log(res)
        this.refreshAll()
      })

    },
    refreshAll(){
      this.$router.go()
    }
  }
}
</script>
<style scoped>
* {
  text-decoration: none;
  font-family: sans-serif;

}
.btn {
  position: relative;
  margin-bottom: 40px;
  height: 40px;
  background: linear-gradient(125deg, #81ecec, #6c5ce7, #81ecec);
  background-size: 200%;
  color: white;
  font-weight: bold;
  border: none;
  cursor: pointer;
  transition: 0.4s;
  display: inline;
}
.btn:hover {
  background-position: right;
}

</style>
