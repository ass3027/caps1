<template>
  <div>
    <!--    //<HelloWorld msg="Welcome to Your Vue.js App"/>-->

    <form action="">
      <input
        v-model="user_id"
        type="text"
        placeholder="ID"
      >

      <input
        v-model="user_pw"
        type="text"
        placeholder="PASSWD"
      >

      <input
        v-model="user_name"
        type="text"
        placeholder="NAME"
      >

      <input
        v-model="user_phone"
        type="text"
        placeholder="PHONE"
      >

      <input
        v-model="user_birth"
        type="date"
        placeholder="Birth"
      >

      <input
        ref="refImage"
        type="file"
        placeholder="photo"
        @change="imageSet()"
      >

      <div id="pictures" />

      <button @click="submit()">
        submit
      </button>
    </form>
  </div>
</template>
<style>
.photoFrame {
	width: 500px;
	height: 500px;
}
.photoFrame:hover {
	cursor: pointer;
}
</style>
<script>
// @ is an alias to /src
// import HelloWorld from '@/components/HelloWorld.vue'
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

      this.user_photo = this.$refs.refImage.files[0];
      var reader = new FileReader();
      reader.readAsDataURL(this.user_photo);

      reader.onload = function () {
        var photoFrame = document.createElement('div');
        photoFrame.style = `background : url(${reader.result}); background-size : cover`;
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

      console.log(sendform.get('user_id'));

      // const csrfToken=/*[[${_csrf.token}]]*/ null;
      // const csrfHeader=/*[[${_csrf.headerName}]]*/ null;

      // try {
      //   const result = await axios.post('/api/join', {
      //     data: sendform,
      //     headers: {
      //       'Content-Type': 'multipart/form-data'
      //     }
      //   });
      //   if (result.status === 200) {
      //     this.loginSuccess = true
      //   }
      // } catch (err) {
      //   this.loginError = true;
      //   throw new Error(err)
      // }

      axios({
        method: 'post',
        url: '/api/join',
        headers: {
          'Content-Type': 'multipart/form-data',
        },
        data: sendform,
      }).then((res) => {
        if (res.data == 'ok') {
          alert('ok');
          window.location.href = '/';
        }
      });
    },
  },
};
// components: {
//   HelloWorld
// }
</script>
