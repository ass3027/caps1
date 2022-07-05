<template>
  <div>
    <!--    //<HelloWorld msg="Welcome to Your Vue.js App"/>-->

    <div class="tit_page">
      <h2 class="tit">
        회원가입
      </h2>
    </div>
    <div class="member_join">
      <form @submit.prevent>
        <p class="page_sub">
          <span class="ico">*</span>필수입력사항
        </p>
        <table style="border-top: 2px solid #333; width: 100%;">
          <tbody>
            <tr class="fst">
              <th style="width: 160px">
                아이디
                <span class="ico">*</span>
              </th>

              <td>
                <input
                  v-model="user_id"
                  type="text"
                  placeholder="ID"
                  clearable
                  @change="submit"
                >


                <a @click="idCheck">중복확인</a>
              </td>
            </tr>


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
            <v-file-input
              ref="refImage"
              v-model="user_photo"
              placeholder="photo"
              @change="imageSet()"
            />

            <div
              id="pictures"
            />

            <v-btn @click="submit()">
              submit
            </v-btn>
          </tbody>
        </table>
      </form>
    </div>
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
        // photoFrame.style = `background : url(${reader.result}); background-size : cover;width:400px;height:400px;`;
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

      axios({
        method: 'post',
        url: '/api/user/join',
        headers: {
          'Content-Type': 'multipart/form-data',
        },
        data: sendform

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
.member_join{
  width: 640px;
  margin: 0 auto;
}

.tit_page {
  overflow: hidden;
  width: 1050px;
  margin: 0 auto;
  padding: 50px 0 51px;
}

.tit_page h2.tit {
  font-weight: 700;
  font-size: 28px;
  color: #333;
  line-height: 35px;
  text-align: center;
  letter-spacing: -1px;
}

.member_join .page_sub {
  padding-bottom: 10px;
  font-size: 12px;
  color: #666;
  line-height: 17px;
  text-align: right;
}

.member_join .page_sub .ico{
  padding-right: 2px;
  color: #ee6a7b;
}

.member_join th .ico {
  color: #ee6a7b;
}

.member_join th {
  width: 159px !important;
  padding: 20px 0 0 20px;
  font-weight: 700;
  font-size: 14px;
  color: #333;
  line-height: 20px;
  vertical-align: top;
  text-align: left;
}

.fst th {
  padding-top: 29px;
}

.fst td {
  padding-top: 19px;
}

.member_join td {
  padding: 10px 0;
  border-top: 0;
  font-size: 14px;
  vertical-align: top;
  text-align: left;
}
</style>
