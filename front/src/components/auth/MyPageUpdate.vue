<template>
  <div>
    <div class="head_aticle">
      <h1 class="tit">
        회원정보수정
      </h1>
    </div>
    <div>
      <table style="border-top: 2px solid #333; width: 100%">
        <tbody>
        <div style="width: 900px; margin-top: 0px; padding-top: 19px;">
          <v-text-field
            v-model="user_name"
            :counter="7"
            class="name"
            label="이름"
            name="name"
            placeholder="이름"
            required
            type="text"
          />

          <v-text-field
            v-model="user_id"
            :counter="30"
            class="id"
            label="아이디"
            name="userId"
            placeholder="아이디"
            required
            type="text"
          />

          <v-text-field
            v-model="user_pw"
            :counter="100"
            class="pw"
            label="비밀번호"
            name="user pw"
            placeholder="비밀번호를 입력하세요"
            required
            type="password"
          />

          <v-text-field
            v-model="user_phone"
            :counter="20"
            class="phone"
            label="전화번호"
            name="user phone"
            placeholder="전화번호"
            required
            type="text"
          />

          <template
            v-if="this.$store.state.user.userRole === 'delivery' "
          >
            <v-select
              v-model="duser_trans"
              :items="trans"
              class="preference"
              dense
              label="운송할 수단을 선택해 주세요"
            />

            <v-text-field
              v-model="duser_license"
              name="guideLicense"
              type="text"
              class="license"
              placeholder="자격증"
            />
          </template>

          <v-select

            v-else
            v-model="preference"
            :items="items"
            class="preference"
            dense
            label="선호하는 여행방식을 골라주세요"
          />



        </div>
        </tbody>
      </table>
    </div>


    <div
      id="button"
      style="padding: 40px 0;"
    >
      <button
        elevation="3"
        type="button"
        @click="main"
      >
        메인으로 이동
      </button>
      <button
        elevation="3"
        type="button"
        @click="Modify"
      >
        수정완료
      </button>
    </div>
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
      preference: '',
      items: ['사진인증형', '맛집탐방형', '관광형', '휴식형'],
      duser_trans:'', //운송수단
      trans: ['승용차', '자전거', '오토바이', '킥보드', '트럭'],
      duser_license:'', //보유자격증
    };
  },
  mounted() {
    console.log(this.$store.state.user.userRole)
    axios.get('/api/user/data/' + this.$store.state.user.userId)
      .then((res) => {
        this.user_id = res.data.user_id
        // this.user_pw = res.data.user_pw //공백으로 둬야하나?
        this.user_phone = res.data.user_phone
        this.user_name = res.data.user_name
        this.preference = res.data.preference
        this.duser_trans = res.data.duser_trans
        this.duser_license = res.data.duser_license
        // console.log(res.data)

      })
      .catch((err) => {
        console.log(err)

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
      this.$router.push("/")
      console.log("성공")
    },
    Modify() {
      const userForm = new FormData();
      const duserForm = new FormData();
      userForm.append("role", this.$store.state.user.userRole)
      userForm.append("user_id", this.user_id)
      userForm.append("user_name", this.user_name)
      userForm.append("user_pw", this.user_pw)
      userForm.append("user_phone", this.user_phone)

      if(this.$store.state.user.userRole === 'delivery'){
        userForm.append('duser_trans', this.duser_trans);
        userForm.append('duser_license', this.duser_license);
        userForm.append("preference", "")
      } else userForm.append("preference", this.preference)


      axios({
        method: 'post',
        url: '/api/user/dataUpdate',
        data: userForm

      })
        .then((res) => {
          console.log(res.data)
          if (res.data === true) {
            alert('정보수정이 완료되었습니다.');
            this.$router.push('/MyPage')
          } else {
            alert('다시 확인해주세요')
          }
        });

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
    }
  }
}
</script>

<style scoped>
button {
  border: 1px solid black;
  background-color: white;
  color: black;
  display: inline-block;
  width: 120px;
  margin-left: 5px;
  vertical-align: top;
  height: 44px;
  border-radius: 3px;
  font-weight: 700;
  font-size: 14px;
  line-height: 40px;
  text-align: center;
  outline: none;
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

#button {
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
  border: none;
  outline: none;
  color: #636e72;
  font-size: 20px;
  height: 50px;
  background: none;
  text-decoration: cornflowerblue;
  text-underline-position: under;
}

.name {
  width: 100%;
  border: none;
  outline: none;
  color: #636e72;
  font-size: 20px;
  height: 60px;
  background: none;
  text-decoration: #5eaf13;
  text-underline: #5eaf13;
}

.pw {
  width: 100%;
  border: none;
  outline: none;
  color: #636e72;
  font-size: 20px;
  height: 60px;
  background: none;
}

.phone {
  width: 100%;
  border: none;
  outline: none;
  color: #636e72;
  font-size: 20px;
  height: 60px;
  background: none;
}

.preference {
  width: 100%;
  border: none;
  outline: none;
  color: #636e72;
  font-size: 20px;
  height: 60px;
  background: none;
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
