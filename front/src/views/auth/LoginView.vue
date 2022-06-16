<template>
  <div>

    <div class="login">
      <h3 class="tit_login">로그인</h3>
      <div class="write_form" style="padding-top: 36px">
        <div class="write_view login_view">
          <form @submit.prevent="login()">
            <input
              v-model="id"
              type="text"
              placeholder="아이디를 입력해주세요"
            />
            <input
              v-model="pw"
              type="password"
              placeholder="비밀번호를 입력해주세요"
              style="margin-top: 10px"
            />

            <div style="padding-bottom: 28px; height: 60px">
              <div class="login_search" style="float: right; padding-top: 13px">
                <a class="link"> 아이디 찾기 </a>
                <span class="bar"></span>
                <a class="link"> 비밀번호 찾기 </a>
              </div>
            </div>

            <v-btn class="btn_type2" @click="login()"
                   height="54px" color="white">
              로그인
            </v-btn>
          </form>
          <!--    <button @click="logout()">logout</button>-->

          <div>
            <v-btn class="btn_type2" @click="getId()"
                   height="54px" color="white">
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
