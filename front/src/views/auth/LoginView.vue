<template>
  <div>
    <form @submit.prevent="login()">
      id<input v-model="id">
      pw<input v-model="pw">
      <button>login</button>
    </form>
    <!--    <button @click="logout()">logout</button>-->

    <div>
      <button @click="getId()">
        getId
      </button>
    </div>
  </div>
</template>

<script>
import axios from 'axios'

export default {
  name: "LoginView.vue",
  beforeRouteEnter(to,from,next){
    console.log(to)
    console.log(from)
    next()
  },
  data() {
    return {
      id: '',
      pw: ''
    }
  },
  methods: {
    login() {
      if (this.id == '') {
        alert("id입력해라")
        return
      } else if (this.pw == '') {
        alert("비번입력해라")
        return
      }
      var loginData = new FormData();
      loginData.append('username', this.id)
      loginData.append('password', this.pw)
      axios({
        url   : '/api/login',
        method: 'post',
        auth : {
          username:this.id,
          password:this.pw
        },
        data  : loginData
      })
          .then((res) => {
            console.log(res)
            //console.log(res.data)
            if (res.headers.gg == "ss") {
              alert("fail")
            }
            else {
              console.log(this.username)
              this.$store.commit('user/updateUserId',this.id)
              console.log(this.$store.state.user)
              console.log(this.$store.state.id);
              this.$router.push("/")
            }
          })
          .catch((err) => {
            console.log(err)
          })
    },
    getId() {
      axios({
        url   : '/api/exper',
        method: 'get'
      })
          .then((res) => {
            console.log(res)
            console.log(res.data)
          })
          .catch((err) => {
            console.log(err)
          })
    }
  }
}
</script>

<style scoped>
  input{
    border-style: solid;
  }
</style>