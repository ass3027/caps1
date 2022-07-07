<template>
  <div>
    <!--    //<HelloWorld msg="Welcome to Your Vue.js App"/>-->
    <div class="member-Join">
      <p class="text-center">일반회원가입</p>
    </div>

    <form @submit.prevent>
      <v-container>
        <v-row>
          <v-col>
            <v-text-field
              v-model="user_id"
              type="text"
              placeholder="ID"
              clearable
              @change="submit"
            >
              ss
            </v-text-field>
          </v-col>

          <v-btn
            style="margin: 25px"
            @click="idCheck"
          >
            아이디 중복 확인하기
          </v-btn>
        </v-row>

        <v-row>
          <v-text-field
            v-model="user_pw"
            type="text"
            placeholder="PASSWD"
            clearable
          />
        </v-row>
        <v-row>
          <v-text-field
            v-model="user_name"
            type="text"
            placeholder="NAME"
          />
        </v-row>

        <v-radio-group
          v-model="gender"
          row
        >
          <v-row>
            <v-radio
              label="남자"
              value="M"
            />
            <v-radio
              style="margin: 5px"
              label="여자"
              value="F"
            />
          </v-row>
        </v-radio-group>

        <v-row>
          <v-text-field
            v-model="user_phone"
            type="text"
            placeholder="PHONE"
          />
        </v-row>

        <v-row>
          <v-select
            v-model="user_area"
            :items="area"
            label="거주하는 지역을 선택해 주세요"
            dense
          ></v-select>
        </v-row>


        <v-row>
          <v-select
            v-model="preference"
            :items="items"
            label="선호하는 여행방식을 골라주세요"
            dense
          />
        </v-row>

        <!--          <v-overflow-btn-->
        <!--            v-model="reference"-->
        <!--            class="my-2"-->
        <!--            :items="items"-->
        <!--            label="선호하는 여행방식을 골라주세요"-->
        <!--            dense-->
        <!--          ></v-overflow-btn>-->

        <v-row>
          <v-date-picker
            v-model="user_birth"
            placeholder="Birth"
          />
        </v-row>
      </v-container>
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

      <v-btn @click="submit()">
        submit
      </v-btn>
    </form>
  </div>
</template>
<script>
// @ is an alias to /src
// import HelloWorld from '@/components/HeaderComponent.vue'
import axios from 'axios';

export default {
  name: 'JoinView',
  data() {
    return {
      user_id: '',
      user_pw: '',
      user_phone: '',
      user_name: '',
      user_birth: '',
      user_photo: {},
      role: 'user',
      checked:false,
      gender:'',
      preference:'',
      items:['사진인증형','맛집탐방형', '관광형', '휴식형'],
      area:['서울','인천', '대전', '대구', '광주', '부산', '울산', '세종',
      '경기도', '강원도', '충청남도', '충청북도', '경상남도', '경상북도', '전라도', '전라도', '제주도'],
      user_area:'', //지역
      business_num:'', //사업자번호
      userId:false //
    };
  },
  methods: {
    imageSet: function () {
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
      };
    },
    submit: function () {
      var sendform = new FormData();

      if (this.checked === false){
        alert("아이디중복 버튼을 눌러주세요")
        return
      }
      // else if (this.checked === false){
      //   alert("아이디중복 버튼을 다시 눌러주세요")
      //   return;
      // }


      console.log(this.gender)
      sendform.append('user_id', this.user_id);
      sendform.append('user_pw', this.user_pw);
      sendform.append('user_phone', this.user_phone);
      sendform.append('user_name', this.user_name);
      sendform.append('user_birth', this.user_birth);
      sendform.append('user_photo', this.user_photo);
      sendform.append('role', this.role);
      sendform.append('gender', this.gender);
      sendform.append('preference', this.preference);
      sendform.append('business_num', this.business_num);
      sendform.append('user_area', this.user_area);

      console.log(77)
      axios({
        method: 'post',
        url: '/api/user/join/일반',
        headers: {
          'Content-Type': 'multipart/form-data',
        },
        data: sendform,
      }).then((res) => {
        console.log(res.data)
         if (res.data) {
           alert('회원가입 성공');
           this.$router.push("/")
        } else  {
           alert('회원가입 실패');
        }
      });
    },
    idCheck(){
      axios.get('/api/user/userid',{ //객체
        params:{
          user_id:this.user_id
        }
      })
      .then((res) =>{
        console.log(res)
        // console.log(res.data.user_id)
        console.log(res.data)
        if(res.data === false){
           alert("중복된 아이디 입니다")
         }else {
          this.checked = true
           alert("사용가능한 아이디입니다.")
         }
      })
    },
  },
};
// components: {
//   HelloWorld
// }
</script>
<style scoped>
.member-Join{
  margin: 10px;
  font-size: 40px;
}
</style>
