<template>
  <div>
    <div class="login">
      <h3 class="tit_login">
        로그인
      </h3>
      <div
        class="write_form"
        style="padding-top: 36px"
      >
        <div class="write_view login_view">
          <form @submit.prevent="login()">
            <input
              v-model="id"
              type="text"
              placeholder="아이디를 입력해주세요"
            >
            <input
              v-model="pw"
              type="password"
              placeholder="비밀번호를 입력해주세요"
              style="margin-top: 10px"
            >

            <div style="padding-bottom: 28px; height: 60px">
              <div
                class="login_search"
                style="float: right; padding-top: 13px"
              >
                <a class="link"> 아이디 찾기 </a>
                <span class="bar" />
                <a class="link"> 비밀번호 찾기 </a>
              </div>
            </div>

            <v-btn
              class="btn_type2"
              height="54px"
              color="white"
              @click="login()"
            >
              로그인
            </v-btn>
          </form>
          <!--    <button @click="logout()">logout</button>-->

          <div>
            <v-btn
              class="btn_type2"
              height="54px"
              color="white"
              @click="getId()"
            >
              getId
            </v-btn>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import axios from 'axios'
import {EventBus} from "@/eventBus/eventBus";

export default {
  name: "LoginView",
  data() {
    return {
      id: '',
      pw: ''
    }
  },
  methods: {
    async login() {
      if (this.id === '') {
        alert("id를 입력하세요")
        return
      } else if (this.pw === '') {
        alert("비번을 입력하세요")
        return
      }
      const loginData = new FormData();
      loginData.append('username', this.id)
      loginData.append('password', this.pw)


      const {headers} = await axios({
        url   : '/api/login',
        method: 'post',
        auth : {
          username:this.id,
          password:this.pw
        },
        data  : loginData
      })
      console.log(headers)

      if (headers.gg === "ss") {
        alert("fail")
      }
      else {
        await this.updateUser()
      }

    },
    async updateUser(){
      const { data } = await axios.get("/api/user/find")
      console.log(data)
      const user = {}
      user.id = data.user_id
      user.role = data.role
      await this.$store.dispatch('user/setUser', user)
      await this.$router.push("/")
      EventBus.$emit("updateId")
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
  .btn_type2 {
    display: block;
    overflow: hidden;
    width: 100%;
    height: 54px;
    border-radius: 3px;
    text-align: center;
    border: 1px solid black;
    color: #333;
    margin-top: 10px;
  }
  .login{
    font-weight: 800;
    font-size: 20px;
    line-height: 20px;
    text-align: center;
    width: 340px;
    margin: 0 auto;
    padding-top: 90px;
    letter-spacing: -.6px;
  }
  .tit_login{
    font-weight: 800;
    font-size: 20px;
    line-height: 20px;
    text-align: center;
  }

  input{
    width: 100%;
    height: 54px;
    padding: 0 19px;
    border: 1px solid #ccc;
    border-radius: 3px;
    background-color: #fff;
    font-size: 14px;
    line-height: 20px;
    outline: none;
  }
  .link{
    font-size: 13px;
    float: left;
    text-decoration: none;
    color: rgb(51, 51, 51);
  }
  .bar{
    background-color: rgb(51,51,51);
    box-sizing: border-box;
    color: rgb(51,51,51);
    display: block;
    float: left;
    height: 10px;
    width: 1px;
    margin: 3px 6px 0;
    font-size: 12px;
    letter-spacing: -.6px;
  }

</style>
