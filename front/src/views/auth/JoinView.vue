<template>
  <div>
    <!--    //<HelloWorld msg="Welcome to Your Vue.js App"/>-->

    <form @submit.prevent>
      <v-container>
        <v-row>
          <v-col>
            <v-text-field
              v-model="user_id"
              type="text"
              placeholder="ID"
              append-icon="mdi-close"
            >
              ss
            </v-text-field>
          </v-col>
        </v-row>
        <v-row>
          <v-text-field
            v-model="user_pw"
            type="text"
            placeholder="PASSWD"
            append-icon="mdi-close"
          />
        </v-row>
        <v-row>
          <v-text-field
            v-model="user_name"
            type="text"
            placeholder="NAME"
          />
        </v-row>
        <v-row>
          <v-text-field
            v-model="user_phone"

            type="text"
            placeholder="PHONE"
          />
        </v-row>
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
      user_photo: '',
      role: 'user',
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

      sendform.append('user_id', this.user_id);
      sendform.append('user_pw', this.user_pw);
      sendform.append('user_phone', this.user_phone);
      sendform.append('user_name', this.user_name);
      sendform.append('user_birth', this.user_birth);
      sendform.append('user_photo', this.user_photo);
      sendform.append('role', this.role);


      axios({
        method: 'post',
        url: '/api/user/join',
        headers: {
          'Content-Type': 'multipart/form-data',
        },
        data: sendform,
      }).then((res) => {
        console.log(res.data)
        if (res.data === true) {

          alert('회원가입 성공');
          this.$router.push('/')
        }
        else{
          alert('회원가입 실패')
        }
      });
    },
  },
};
// components: {
//   HelloWorld
// }
</script>
<style>

</style>
