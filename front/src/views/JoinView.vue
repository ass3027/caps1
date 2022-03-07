<template>

  <div>
    <!--    //<HelloWorld msg="Welcome to Your Vue.js App"/>-->
    <div>
      <input type="text" placeholder="ID" v-model="user_id"/>

      <input type="text" placeholder="PASSWD" v-model="user_pw"/>

      <input type="text" placeholder="NAME" v-model="user_name"/>

      <input type="text" placeholder="PHONE" v-model="user_phone"/>

      <input type="date" placeholder="Birth" v-model="user_birth"/>

      <input type="file" ref="refImage" placeholder="photo" />

      <button @click='submit()'>submit</button>

    </div>

  </div>
</template>

<script>
// @ is an alias to /src
// import HelloWorld from '@/components/HelloWorld.vue'
import axios from 'axios'

export default {
  name: 'JoinView',
  data() {
    return{
      user_id   : "",
      user_pw   : "",
      user_phone: "",
      user_name : "",
      user_birth: "",
      user_photo: "",
      role      : "user"
    }
  },
  methods: {
    submit: function(){
      this.user_photo = this.$refs.refImage.files

      var sendform = new FormData()

      sendform.append('user_id',this.user_id)
      sendform.append('user_pw',this.user_pw)
      sendform.append('user_phone',this.user_phone)
      sendform.append('user_name',this.user_name)
      sendform.append('user_birth',this.user_birth)
      sendform.append('user_photo',this.user_photo)
      sendform.append('role',this.role)

      console.log(sendform)

      const csrfToken=/*[[${_csrf.token}]]*/ null;
      const csrfHeader=/*[[${_csrf.headerName}]]*/ null;
      axios({
        method:'post',
        url:'/api/join',
        headers:{
          'Content-Type':'multipart/form-data',

        },
        data : sendform,
        beforeSend: function (xhr) {
          xhr.setRequestHeader(csrfHeader, csrfToken);

        }
      })
      .then((res)=>{
        if(res.data=="ok"){
          alert("ok")
          window.location.href="/";
        }
      })
    }
  }
  // components: {
  //   HelloWorld
  // }
}
</script>