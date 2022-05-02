<template>
  <!--  <div id="app">-->
  <div>
  <h1>마이페이지</h1>


    <router-link to="list"></router-link>
    <header>
      <nav>
        <button><span>즐겨찾기</span></button> |
        <button><span>결제수단</span></button> |
        <button><span>1대1문의</span></button>
      </nav>
    </header>

  <div id="container">
    <form @submit.prevent="submitForm">
      <v-container>
        <v-row>
          <v-col
            cols="3"
            md="2">
            <div class="name">
              <v-text-field
                v-model="user_name" :counter="10" label="이름" required/>
              <!--<label for="name">이름: </label>-->
              <!--<input type="text" id="name"  placeholder="NAME"-->
              <!--v-model="user_name" />-->
            </div>
          </v-col>
        </v-row>

      <!--            <div class="age">-->
      <!--                <label for="age">생년월일 : </label>-->
      <!--                <input type="text" id="age" v-model="age" />-->
      <!--            </div>-->

      <v-row>
        <v-col
        cols="3"
        md="2">
          <div class="id">
            <v-text-field
              v-model="user_id" :counter="20" label="아이디" required/>
          </div>
        </v-col>
      </v-row>

<!--      <div class="id">-->
<!--        <label for="id">아이디: </label>-->
<!--        <input-->
<!--          id="id" v-model="user_id" type="text">-->
<!--      </div>-->

        <v-row>
          <v-col
            cols="3"
            md="2">
            <div class="pw">
              <v-text-field
                v-model="user_pw" :counter="20" label="비밀번호" required/>
            </div>
          </v-col>
        </v-row>

<!--      <div class="pw">-->
<!--        <label for="pw">비밀번호 : </label>-->
<!--        <input-->
<!--          id="pw" v-model="user_pw" type="pw">-->
<!--      </div>-->

      <!--            <div class="email">-->
      <!--                <label for="email">email : </label>-->
      <!--                <input type="text" id="email" v-model="email" />-->
      <!--            </div>-->

        <v-row>
          <v-col
            cols="3"
            md="2">
            <div class="phone">
              <v-text-field
                v-model="user_phone" :counter="20" label="연락처"
                type="text" placeholder="핸드폰번호 입력" required/>
            </div>
          </v-col>
        </v-row>



<!--      <div class="phone">-->
<!--        <label for="phone">연락처 : </label>-->
<!--        <input-->
<!--          id="phone" v-model="user_phone" type="text" placeholder="핸드폰번호 입력" maxlength="13">-->
<!--      </div>-->
        <!--              <input type="text" name="phone"/>- -->
        <!--              <input type="text" name="phone"/>- -->
        <!--              <input type="text" name="phone"/>-->

      </v-container>
      <!--            <div class="address">-->
      <!--                <label for="address">주소 : </label>-->
      <!--                <input type="text" id="address" v-model="address">-->
      <!--            </div>-->
      <!--            <button type="submit">로그인</button>-->
      <!--            <button type="submit">메인으로 이동<el/button>-->

      <div id="button">
        <v-btn v-on:click="login" elevation="3">로그인</v-btn>
        <v-btn v-on:click="main" elevation="3">메인으로 이동</v-btn>
        <v-btn v-on:click="Modify" elevation="2">정보 수정하기</v-btn>
      </div>
    </form>
   </div>
  </div>

<!--  </div>-->
</template>

<script>
import axios from "axios";
export default {
  data(){
    return{
      user_id: '',
      user_pw: '',
      user_phone: '',
      user_name: '',
      user_photo: '',
    };
  },
  mounted() {
    axios.get('/api/data/'+ this.$store.state.user.userId)
    .then(res=> {
      this.user_id = res.data.user_id
      // this.user_pw = res.data.user_pw //공백으로 둬야하나?
      this.user_phone = res.data.user_phone
      this.user_name = res.data.user_name
      console.log(res.data)
    }).catch((error)=>{
      this.error=error
      console.log(res.data)
      console.log(res.data.user_id)
      // console.log(res.data.user_name)
    })
  },
  methods:{
    submitForm(){
        console.log("성공")
    },
    login(){
        window.open("/login")
        // this.$router.go();
        console.log("성공")
    },
    main(){
      window.open("/home")
      console.log("성공")
    },
    Modify(){
      alert("정보수정을 하시겠습니까?")
      alert("수정완료되었습니다")
      window.open("/home")
      console.log("성공")
    }
  }
}
</script>

<style scoped>
#container{text-align:center;}
header{
  position: center;
  top: 0;
  left: 0;
  right: 0;

  height: 75px;
  padding: 1rem;
  color: white;
  background: #1e90cc;
  font-weight: bold;
  display: flex;
  justify-content: space-between;
  align-items: center;
}
</style>
