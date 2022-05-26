<template>
  <div>
    <form @submit.prevent="login()">
      <div style="width:400px">
        <v-text-field
          v-model="id"
          placeholder="ID"
        />
        <v-text-field
          v-model="pw"
          type="password"
          placeholder="PW"
        />
        <v-btn @click="login()">
          login
        </v-btn>
      </div>
    </form>
    <!--    <button @click="logout()">logout</button>-->

    <div>
      <br>
      <v-btn @click="getId()">
        getId
      </v-btn>
    </div>
  </div>
</template>

<script>
import axios from 'axios'

export default {
  name: "LoginView",
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
      if (this.id === '') {
        alert("id를 입력하세요")
        return
      } else if (this.pw === '') {
        alert("비번을 입력하세요")
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
            if (res.headers.gg === "ss") {
              alert("fail")
            }
            else {
              //this.$store.commit('user/updateUserId',this.id)
              this.$store.dispatch('user/setUser',this.id)
              //res.headers.gg를 header컴포넌트로 보내야하는딩
              console.log(res.headers.gg)
              this.$router.push("/")
              //location.reload()
            }
          })
          .catch((err) => {
            console.log(err)
          })
    },
    getId() {
      axios({
        url   : '/api/user/exper',
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
