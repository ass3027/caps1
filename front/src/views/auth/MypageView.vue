<template>
  <div id="app">
  <div>
<!--    <router-link to="list"></router-link>-->
    <h1>마이페이지</h1>
    <header>
      <nav>
        <button @click="bookmark" type="submit">즐겨찾기</button> |
        <button @click="pay" type="submit">수익관리</button> |
        <button @click="board" type="submit">1대1문의</button> |
        <button @click="mydata" type="submit">내정보수정 </button>
      </nav>
    </header>

<!--  <div id="container">-->
<!--    <v-container>-->


    <form class = "container" @submit.prevent="onsubmit">
      <h1>회원정보</h1>

      <div class="textForm">
        <p class="a">이름</p>
        <input
          type="text"
          name="name"
          class="name"
          placeholder="이름"
          v-model="user_name"
        >
      </div>

<!--  <div class="textForm">-->
<!--      <v-text-field-->
<!--        type="text"-->
<!--        name="name"-->
<!--        class="name"-->
<!--        placeholder="이름"-->
<!--        v-model="user_name"-->
<!--        :counter="20" label="이름" required-->
<!--      ></v-text-field>-->


<!--  <div class="textForm">-->
<!--        <v-text-field-->
<!--          type="text"-->
<!--          name="userId"-->
<!--          class="id"-->
<!--          placeholder="아이디"-->
<!--          v-model="user_id"-->
<!--          :counter="20" label="아이디" required-->
<!--        ></v-text-field>-->
<!--  </div>-->
<!--  </div>-->


<!--  <div class="textForm">-->
<!--        <v-text-field-->
<!--          type="text"-->
<!--          name="user pw"-->
<!--          class="pw"-->
<!--          placeholder="비밀번호를 입력하세요"-->
<!--          v-model="user_pw"-->
<!--          :counter="20" label="비밀번호" required-->
<!--        ></v-text-field>-->
<!--  </div>-->

<!--  <div class="textForm">-->
<!--        <v-text-field-->
<!--          type="text"-->
<!--          name="user phone"-->
<!--          class="phone"-->
<!--          placeholder="전화번호"-->
<!--          v-model="user_phone"-->
<!--          :counter="20" label="전화번호" required-->
<!--        ></v-text-field>-->
<!--  </div>-->



  <div class="textForm">
        <p class="a">아이디</p>
        <input
          type="text"
          name="userId"
          class="id"
          placeholder="아이디"
          v-model="user_id"
        >
  </div>

      <div class ="textForm">
        <p class="a">비밀번호</p>
        <input
          type="text"
          name="user pw"
          class="pw"
          placeholder="비밀번호를 입력하세요"
          v-model="user_pw" />
      </div>

      <div class ="textForm">
        <p class="a">연락처</p>
        <input
          type="text"
          name="user phone"
          class="phone"
          placeholder="휴대폰"
          v-model="user_phone"/>
      </div>


<!--      <v-row>-->
<!--        <v-col-->
<!--        cols="10"-->
<!--        md="3">-->
<!--          <div class="aa">-->
<!--            <v-text-field-->
<!--              v-model="user_id" :counter="20" label="아이디" required/>-->
<!--          </div>-->
<!--        </v-col>-->
<!--      </v-row>-->


      <!--            <div class="email">-->
      <!--                <label for="email">email : </label>-->
      <!--                <input type="text" id="email" v-model="email" />-->
      <!--            </div>-->



<!--      </v-container>-->
      <!--            <div class="address">-->
      <!--                <label for="address">주소 : </label>-->
      <!--                <input type="text" id="address" v-model="address">-->
      <!--            </div>-->


      <div id="button">
        <v-btn v-on:click="login" elevation="3">로그인</v-btn>
        <v-btn v-on:click="main" elevation="3">메인으로 이동</v-btn>
        <v-btn v-on:click="Modify" elevation="3" type="button">정보 수정하기</v-btn>
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
      this.user_pw = res.data.user_pw //공백으로 둬야하나?
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
    onsubmit(){
        console.log("성공")
    },
    login(){
        this.$router.push("/login")
        // this.$router.go();
        console.log("성공")
    },
    main(){
      this.$router.push("/BookMark")
      console.log("성공")
    },
    Modify(){
      // alert("정보수정을 하시겠습니까?")
      // alert("수정완료되었습니다")
      axios.post("/api/Mypage")
      this.$router.push("/mydata")
      console.log("성공")
    },
    bookmark(){
      this.$router.push("/BookMark")
    },
    pay(){
      this.$router.push("/pay")
    },
    board(){
      this.$router.push("./board")
    },
    mydata(){
      this.$router.push("/mydata")
    }
  }
}
</script>

<style scoped>

.container{
  text-align: center;
  position: center;

}
header{
  position: center;
  top: 0;
  left: 0;
  right: 0;
  height: 75px;
  padding: 1rem;
  color: white;
  background: #147ed0;
  font-weight: bold;
  display: flex;
  justify-content: space-between;
  align-items: center;
}
#button{
  position: center;

}
.textForm {
  text-align: center;
  position: center;
  font-size: 25px;
  text-decoration: underline;
  border-bottom: 2px solid #adadad;
  margin: 30px;
  padding: 10px 10px;

}

.id {
  width: 100%;
  border:none;
  outline:none;
  color: #636e72;
  font-size:16px;
  height:25px;
  background: none;
  font-size: 25px;
  text-decoration: cornflowerblue;
  text-underline-position: under;

}

.name{
  width: 100%;
  border:none;
  outline:none;
  color: #636e72;
  font-size:16px;
  height:25px;
  background: none;
  font-size: 25px;
  text-decoration: #5eaf13;
  text-underline: #5eaf13;

}
.pw{
  width: 100%;
  border:none;
  outline:none;
  color: #636e72;
  font-size:16px;
  height:25px;
  background: none;
  font-size: 25px;
}
.phone{
  width: 100%;
  border:none;
  outline:none;
  color: #636e72;
  font-size:16px;
  height:25px;
  background: none;
  font-size: 25px;
}
.a {
  text-align:left;
  text-decoration: none;
  /*display: inline;*/
  /*box-shadow: inset 0 -10px 0 hsla(53, 90%, 83%, 0.93);*/
}


</style>
