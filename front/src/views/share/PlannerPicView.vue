<template>
  <div>
    <PlannerHeader />
    <v-card>
      <v-container>
        <h2>사진첩</h2>
        <div>
          <v-hover
            v-slot="{ hover }"
          >
            <v-card
              :elevation="hover ? 10 : 2"
            >
              <img
                v-for="photo in photos"
                :key="photo.pic_name"
                :src="'/api/photo/'+photo.pic_name"
                width="200px"
                height="200px"
                @click="picDetail(photo)"
              >
            </v-card>
          </v-hover>
        </div>
        <div class="ma-5">
          <h3>사진 업로드</h3>
          <input
            ref="refImage"
            type="file"
            placeholder="photo"
          >
          <v-btn @click="upload">
            Upload
          </v-btn>

        </div>
      </v-container>
    </v-card>


    <v-dialog
      v-model="dialog"
      width="500"
    >
      <v-card>
        <v-card-title class="text-h5 grey lighten-2">
          {{ createdTime }}
        </v-card-title>
        <v-card-text>
          <img
            :src="'/api/photo/'+selectedPic"
            width="100%"
            height="100%"
          >
        </v-card-text>

        <v-divider />

        <v-card-actions>
          <v-row justify="end">
            <v-col>올린사람:{{ picsUser }}</v-col>
            <v-col cols="auto">
              <v-btn
                color="primary"
                text
                @click="deletePic()"
              >
                삭제하기
              </v-btn>
            </v-col>
            <v-col cols="auto">
              <v-btn
                color="grey"
                text
                @click="dialog = false"
              >
                닫기
              </v-btn>
            </v-col>
          </v-row>
        </v-card-actions>
      </v-card>
    </v-dialog>
  </div>
</template>

<script>

// import axios from 'axios'
import PlannerHeader from "@/components/PlannerHeader";
import axios from "axios";

export default {
  name: 'PlannerPicView',
  components: {
    PlannerHeader,
  },
  data() {
    return {
      user_photo: '',
      photos: '',
      dialog: false,
      selectedPic: '',
      picsUser: '',
      createdTime: '',
    }
  },
  mounted() {
    this.getPlanPic();
  },
  methods: {
    getPlanPic() {
      axios.get('/api/planner/getPlanPic', {
        params: {
          plan_id: this.$store.state.user.planId,
        }
      })
        .then(res => {
          console.log("?")
          console.log(res.data)
          this.photos = res.data;
        })
        .catch((err) => {
          alert(err)
        })

    },
    upload() {
      this.user_photo = this.$refs.refImage.files[0];
      console.log(this.user_photo)
      if (!this.user_photo) {
        alert("사진을 넣어주세요")
      }
      var data = {
        pic_name: this.user_photo,
        plan_id: this.$store.state.user.planId,
        user_id: this.$store.state.user.userId
      }

      var sendform = new FormData();

      sendform.append('pic_name', this.user_photo);
      sendform.append('plan_id', this.$store.state.user.planId);
      sendform.append('user_id', this.$store.state.user.userId);

      axios({
        method: 'post',
        url: '/api/planner/addPlanPic',
        headers: {
          'Content-Type': 'multipart/form-data',
        },
        data: sendform,
      }).then((res) => {
        console.log(res.data)
        if (res.data === true) {

          alert('ok');
          location.reload();
        }
      });


    },
    picDetail(photo) {
      console.log(photo.pic_name)
      this.selectedPic = photo.pic_name
      this.picsUser = photo.user_id;
      var time = photo.pic_name.substring(10, 23)
      var time2 = new Date()
      time2.setTime(time)
      this.createdTime = time2.getFullYear()+"년"+(time2.getMonth()+1)+"월"+time2.getDate()+"일 "+time2.getHours()+"시"+time2.getMinutes()+"분"
      this.dialog = true;
    },
    deletePic() {
      if(!confirm("정말로 삭제하시겠습니까?"))return
      axios.delete('/api/photo/' + this.selectedPic)
        .then((res) => {
          console.log(res.data)
          this.getPlanPic()
          this.dialog = false
        })
    }
  }
}

</script>

<style scoped>
/*img {*/
/*  border: 1px solid;*/
/*  width: 200px;*/
/*  height: 200px;*/
/*}*/
</style>
