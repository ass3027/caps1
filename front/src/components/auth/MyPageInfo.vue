<template>
  <div
    id="app"
  >
    <div class="head_aticle">
      <h1 class="tit">
        회원정보
      </h1>
    </div>
    <v-container style="border-top: 2px solid #333">
      <v-row justify="center">
        <v-col
          cols="12"
          sm="6"
        >
          <v-text-field
            v-model="user_name"
            class="box"
            label="이름"
            outlined
            readonly
          />
          <v-text-field
            v-model="user_id"
            class="box"
            label="아이디"
            outlined
            readonly
          />
          <v-text-field
            v-model="gender"
            class="box"
            label="성별"
            outlined
            readonly
          />
          <v-text-field
            v-model="user_phone"
            class="box"
            label="연락처"
            outlined
            readonly
          />
          <v-text-field
            v-model="preference"
            class="box"
            label="내가 선호하는 여행방식"
            outlined
            readonly
          />
        </v-col>
      </v-row>
    </v-container>
  </div>
  <!--  </div>-->
</template>
<script>
import axios from "axios";

export default {
  components: {},

  data() {
    return {
      user_id: '',
      user_pw: '',
      user_phone: '',
      user_name: '',
      user_photo: '',
      gender: '',
      preference: '',
      status: '',

    };
  },
  mounted() {
    axios.get('/api/user/data/' + this.$store.state.user.userId)
      .then(res => {
        this.user_id = res.data.user_id
        this.user_pw = res.data.user_pw //공백으로 둬야하나?
        this.user_phone = res.data.user_phone
        this.user_name = res.data.user_name
        this.gender = res.data.gender
        this.preference = res.data.preference
        console.log(res.data)
        console.log(this.$store.state.user.userId)

      }).catch((error) => {
      console.log(error)
      // console.log(res.data.user_name)
    })
  },
  methods: {
    onsubmit() {
      console.log("성공")
    },
    login() {
      this.$router.push("/login")
      // this.$router.go();
      console.log("성공")
    },
    main() {
      this.$router.push("/BookMark")
      console.log("성공")
    },
    Modify() {
      // alert("정보수정을 하시겠습니까?")
      // alert("수정완료되었습니다")
      axios.post("/api/Mypage")
      this.$router.push("/mydata")
      console.log("성공")
    },
    bookmark() {
      this.$router.push("/BookMark")
    },
    pay() {
      this.$router.push("/pay")
    },
    questions() {
      this.$router.push("/Questions")
    },
    mydata() {
      this.$router.push("/MyData")
    },
    changeStatus(value) {
      this.status = value
    }
  }
}
</script>

<style scoped>

.container {
  text-align: center;
  position: center;

}

header {
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

/** {*/
/*  position: center;*/
/*  margin: 0px;*/
/*  padding: 0px;*/
/*  text-decoration: none;*/
/*  font-family:sans-serif;*/
/*}*/


.box {
  text-align: center;
  position: center;
  font-size: 20px;
  /*border-bottom: 2px solid #adadad;*/
  margin: 20px;
  padding: 0px 10px;
}


.head_aticle {
  padding: 5px 0 34px;
}

.tit {
  height: 36px;
  font-weight: 700;
  font-size: 24px;
  line-height: 36px;
  color: #333;
  letter-spacing: -.5px;
}

</style>
