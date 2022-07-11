<template>
  <div>
    <PlannerHeader/>
    <v-card>
      <v-container>
        <v-row>
          <v-col cols="2"></v-col>
          <v-col cols="5">
            <h2>사진첩</h2>
          </v-col>
          <v-col  cols="2">
            <v-row>
              <p class="mt-2">정렬순서 :</p>
              <div v-if="sortStatus=='latest'">
                <v-btn @click="sortStatus='old'" text>
                  오래된순
                </v-btn>
              </div>
              <div v-else>
                <v-btn @click="sortStatus='latest'" text>
                  최신순
                </v-btn>
              </div>

            </v-row>
          </v-col>

          <v-col cols="2">
            <v-btn style="float:right" class="ma-0" @click="uploadDialog=true">
              사진 업로드
            </v-btn>
          </v-col>
        </v-row>

        <div class="mt-5">
          <v-row>
            <v-col cols="2">
              카테고리
              <v-checkbox
                v-model="selected"
                label="사람"
                value="사람"
              ></v-checkbox>
              <v-checkbox
                v-model="selected"
                label="음식"
                value="음식"
              ></v-checkbox>
              <v-checkbox
                v-model="selected"
                label="풍경"
                value="풍경"
              ></v-checkbox>
              <v-checkbox
                v-model="selected"
                label="관광지"
                value="관광지"
              ></v-checkbox>








            </v-col>
            <v-col cols="10">
              <v-row>
                <v-col
                  v-for="photo in paginatedData"
                  :key="photo.pic_name"
                  cols="auto">
                  <v-card
                    width="200px"
                    height="200px"
                    style="cursor:pointer"
                  >
                    <img
                      :src="'/api/photo/'+photo.pic_name"
                      @click="picDetail(photo)"
                      width="200px"
                      height="200px"
                    >
                  </v-card>

                </v-col>

              </v-row>
              <v-row justify="center" style="text-align: center">
                <v-col cols="6" >
<!--                  <v-btn @click="prevPage" text>이전</v-btn>-->
<!--                  <v-div v-for="i in 10" :key="i" @click="movePage(i)">-->
<!--                    <v-btn v-if="pageNum+i<=pageCount">-->
<!--                      {{pageNum+i}}-->
<!--                    </v-btn>-->

<!--                  </v-div>-->
<!--                  <v-btn @click="nextPage" text>다음</v-btn>-->
                  <v-pagination
                    v-model="pageNum"
                    :length="pageCount"
                  >
                  </v-pagination>
                </v-col>
                <v-col cols="1"></v-col>
              </v-row>
            </v-col>
          </v-row>
        </div>
      </v-container>
    </v-card>


    <v-dialog
      v-model="dialog"
      width="500"
    >
      <v-card>
        <v-card-title class="text-h5 grey lighten-2">
          {{ createdTime }}[{{picsCategory}}]
        </v-card-title>
        <v-card-text>
          <img
            :src="'/api/photo/'+selectedPic"
            width="100%"
            height="100%"
          >
        </v-card-text>

        <v-divider/>

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
    <v-dialog
      v-model="uploadDialog"
      width="500"
    >
      <v-card>
        <v-card-title class="text-h5 grey lighten-2">
          <h3>사진 업로드</h3>
        </v-card-title>
        <v-card-text>
          <v-file-input
            ref="refImage"
            v-model="user_photo"
            placeholder="photo"
            @change="imageSet()"
          />
          <div
            id="pictures"
            style="width:400px;height:400px"
          />
          <v-select
            :items="category"
            label="카테고리"
            v-model="selectedCate"
          ></v-select>
        </v-card-text>
        <v-divider/>
        <v-card-actions>
          <v-row justify="end">
            <v-col cols="auto">
              <v-btn
                color="grey"
                text
                @click="uploadDialog = false"
              >
                닫기
              </v-btn>
            </v-col>
            <v-col cols="auto">
              <v-btn text @click="upload">
                Upload
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
import PlannerHeader from "@/components/planner/PlannerHeader";
import axios from "axios";

export default {
  name: 'PlannerPicView',
  components: {
    PlannerHeader,
  },
  data() {
    return {
      user_photo: '',
      originPhotos:[],
      dialog: false,
      uploadDialog: false,
      selectedPic: '',
      picsUser: '',
      picsCategory:'',
      createdTime: '',
      pageNum: 0,
      pageSize:12,
      sortStatus:'latest',
      selected: ['사람','음식','풍경','관광지'],
      category:['사람','음식','풍경','관광지'],
      selectedCate:''

    }
  },
  mounted() {
    this.getPlanPic();
  },
  methods: {
    imageSet() {
      var picture = document.getElementById('pictures');
      while (picture.hasChildNodes()) {
        picture.removeChild(picture.firstChild);
      }

      // this.user_photo = this.$refs.refImage.files[0];

      var reader = new FileReader();
      reader.readAsDataURL(this.user_photo);

      reader.onload = function () {
        var photoFrame = document.createElement('div');
        photoFrame.style = `background : url(${reader.result}); background-size : cover;width:400px;height:400px;`;
        photoFrame.className = 'photoFrame';
        document.getElementById('pictures').appendChild(photoFrame);
        //e.target.value = "";

        // photoFrame.addEventListener("click",function(){
        //   document.getElementById("pictures").removeChild(photoFrame);
        // })
      }
    },

    getPlanPic() {
      axios.get('/api/planner/getPlanPic', {
        params: {
          plan_id: this.$store.state.user.planId,
        }
      })
        .then(res => {
          console.log("?")
          console.log(res.data)
          this.originPhotos = res.data;
        })
        .catch((err) => {
          alert(err)
        })

    },
    upload() {
      // this.user_photo = this.$refs.refImage.files[0];
      console.log(this.user_photo)
      if (!this.user_photo) {
        alert("사진을 넣어주세요")
        return
      }
      if(this.selectedCate==''){
        alert("카테고리를 선택해주세요")
        return
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
      sendform.append('category', this.selectedCate);

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
      this.picsCategory = photo.category;
      var time = photo.pic_name.substring(10, 23) //파일이름에 타임스탬프들어가는거를
      var time2 = new Date()
      time2.setTime(time) //타임으로 변환해서 시간정보 가져옴
      this.createdTime = time2.getFullYear() + "년" + (time2.getMonth() + 1) + "월" + time2.getDate() + "일 " + time2.getHours() + "시" + time2.getMinutes() + "분"
      this.dialog = true;
    },
    deletePic() {
      if (!confirm("정말로 삭제하시겠습니까?")) return
      axios.delete('/api/photo/' + this.selectedPic)
        .then((res) => {
          console.log(res.data)
          this.getPlanPic()
          this.dialog = false
        })
    },

  //  페이징 메소드
    nextPage () {
      this.pageNum += 1;
    },
    prevPage () {
      this.pageNum -= 1;
    },

  },

  computed: {
    photos(){
      const filterResult = this.originPhotos.filter(photo=>
        this.selected.includes(photo.category)
      )
      var sortResult;
      if(this.sortStatus=='latest'){
        sortResult = filterResult.sort((a,b)=>{
          return(Number(a.pic_name.substring(10, 23))-Number(b.pic_name.substring(10, 23)))
        })
      }
      else{
        sortResult=filterResult.sort((a,b)=>{
          return(Number(b.pic_name.substring(10, 23))-Number(a.pic_name.substring(10, 23)))
        })
      }
      return sortResult;

    },
    pageCount () {
      let listLeng = this.photos.length,
        listSize = this.pageSize,
        page = Math.floor(listLeng / listSize);
      if (listLeng % listSize > 0) page += 1;

      /*
      아니면 page = Math.floor((listLeng - 1) / listSize) + 1;
      이런식으로 if 문 없이 고칠 수도 있다!
      */
      return page;
    },
    paginatedData () {
      const start = this.pageNum * this.pageSize,
        end = start + this.pageSize;
      return this.photos.slice(start, end);
    },

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
