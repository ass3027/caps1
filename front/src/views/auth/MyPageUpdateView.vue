<template>
  <div style="width: 1050px; padding-top: 65px">
    <MyPageHeader></MyPageHeader>
    <div class="container" style="width: 820px; float: right; padding: 0">
      <h2>회원정보수정</h2>
      <div>
        <v-text-field
          v-model="user_name"
          type="text"
          name="name"
          class="name"
          placeholder="이름"
          :counter="7"
          label="이름"
          required
        />

        <v-text-field
          v-model="user_id"
          type="text"
          name="userId"
          class="id"
          placeholder="아이디"
          :counter="30"
          label="아이디"
          required
        />

        <v-text-field
          v-model="user_pw"
          type="password"
          name="user pw"
          class="pw"
          placeholder="비밀번호를 입력하세요"
          :counter="100"
          label="비밀번호"
          required
        />

        <v-text-field
          v-model="user_phone"
          type="text"
          name="user phone"
          class="phone"
          placeholder="전화번호"
          :counter="20"
          label="전화번호"
          required
        />

        <v-select
          v-model="preference"
          :items="items"
          label="선호하는 여행방식을 골라주세요"
          dense
          class="preference"
        ></v-select>
      </div>


      <div id="button">
        <v-btn elevation="3" type="button" @click="login">로그인</v-btn>
        <v-btn elevation="3" type="button" @click="main">메인으로 이동</v-btn>
        <v-btn elevation="3" type="button" @click="Modify">수정완료</v-btn>
      </div>
    </div>
  </div>

  <!--  </div>-->
</template>

<script>
import MyPageHeader from "@/components/store/MyPageHeader";
import axios from "axios";
export default {
  components:{
    MyPageHeader
  },
  data(){
    return{
      user_id: '',
      user_pw: '',
      user_phone: '',
      user_name: '',
      user_photo: '',
      preference:'',
      items:['사진인증형','맛집탐방형', '관광형', '휴식형']
    };
  },
  mounted() {
    axios.get('/api/user/data/'+ this.$store.state.user.userId)
      .then( (res)=> {
        this.user_id = res.data.user_id
        // this.user_pw = res.data.user_pw //공백으로 둬야하나?
        this.user_phone = res.data.user_phone
        this.user_name = res.data.user_name
        this.preference = res.data. preference
        // console.log(res.data)

      })
      .catch((err)=>{
      console.log(err)

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
      this.$router.push("/")
      console.log("성공")
    },
    Modify(){
      var sendform = new FormData()
      console.log(this.user_name)
      sendform.append("user_id", this.user_id)
      sendform.append("user_name", this.user_name)
      sendform.append("user_pw", this.user_pw)
      sendform.append("user_phone", this.user_phone)
      sendform.append("preference", this.preference)


      axios({
        method:'post',
        url: '/api/user/dataUpdate',
        headers:{
          "Content-Type" : 'multipart/form-data', //사진같은 다양한 파일을 올릴때
        },
        data:sendform,
      })

      .then((res) => {
        console.log(res.data)
        if (res.data === true) {
          alert('정보수정이 완료되었습니다.');
          this.$router.push('/MyPage')
        }
        else{
          alert('다시 확인해주세요')
        }
      });

    },
    bookmark(){
      this.$router.push("/BookMark")
    },
    pay(){
      this.$router.push("/pay")
    },
    questions(){
      this.$router.push("/Questions")
    },
    mydata(){
      this.$router.push("/MyData")
    }
  }
}
</script>

<style scoped>

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
  text-align: center;

}
/*.textForm {*/
/*  text-align: center;*/
/*  position: center;*/
/*  font-size: 20px;*/
/*  !*text-decoration: underline;*!*/
/*  border-bottom: 2px solid #adadad;*/
/*  margin: 30px;*/
/*  padding: 10px 10px;*/

/*}*/

.id {
  width: 100%;
  border:none;
  outline:none;
  color: #636e72;
  font-size:20px;
  height:50px;
  background: none;
  text-decoration: cornflowerblue;
  text-underline-position: under;
}

.name{
  width: 100%;
  border:none;
  outline:none;
  color: #636e72;
  font-size:20px;
  height:60px;
  background: none;
  text-decoration: #5eaf13;
  text-underline: #5eaf13;
}

.pw{
  width: 100%;
  border:none;
  outline:none;
  color: #636e72;
  font-size:20px;
  height:60px;
  background: none;
}

.phone{
  width: 100%;
  border:none;
  outline:none;
  color: #636e72;
  font-size:20px;
  height:60px;
  background: none;
}
.preference{
  width: 100%;
  border:none;
  outline:none;
  color: #636e72;
  font-size:20px;
  height:60px;
  background: none;
}


</style>
