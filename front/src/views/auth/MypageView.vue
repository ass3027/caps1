<template>
  <div
    id="app"
    style="width: 1050px; padding-top: 65px"
  >
    <div>
      <MyPageHeader />
      <div style="width: 820px; float: right;">
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
                label="이름"
                class="box"
                outlined
                readonly
              />
              <v-text-field
                v-model="user_id"
                label="아이디"
                class="box"
                outlined
                readonly
              />
              <v-text-field
                v-model="user_birth"
                label="생일"
                class="box"
                outlined
                readonly
              />
              <v-text-field
                v-model="gender"
                label="성별"
                class="box"
                outlined
                readonly
              />
              <v-text-field
                v-model="user_phone"
                label="연락처"
                class="box"
                outlined
                readonly
              />
              <v-text-field
                v-if="preference"
                v-model="preference"
                label="내가 선호하는 여행방식"
                class="box"
                outlined
                readonly
              />
              <v-text-field
                v-if="user_area"
                v-model="user_area"
                label="지역"
                class="box"
                outlined
                readonly
              />
              <v-text-field
                v-if="duser_trans"
                v-model="duser_trans"
                label="운송수단"
                class="box"
                outlined
                readonly
              />
              <v-text-field
                v-if="duser_license"
                v-model="duser_license"
                label="보유 자격증"
                class="box"
                outlined
                readonly
              />
              <v-text-field
                v-if=" business_num"
                v-model=" business_num"
                label="사업자번호"
      class="box"
      outlined
      readonly
      />
      </v-col>
    </v-row>
    </v-container>
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
        user_birth:'',
        user_photo: '',
        gender: '',
        preference:'',
        user_area:true,
        duser_trans:'',
        duser_license:'',
        business_num:''

      };
      },
        mounted() {
        axios.get('/api/user/data/'+ this.$store.state.user.userId)
        .then(res=> {
        this.user_id = res.data.user_id
        this.user_pw = res.data.user_pw //공백으로 둬야하나?
        this.user_phone = res.data.user_phone
        this.user_name = res.data.user_name
        this.user_birth = res.data.user_birth
        this.gender = res.data.gender
        this.preference = res.data.preference
        this.user_area = res.data.user_area
        this.duser_trans = res.data.duser_trans
        this.duser_license = res.data.duser_license
        this.business_num = res.data.business_num
      console.log(res.data)
      console.log(this.$store.state.user.userId)

    }).catch((error)=>{
      console.log(error)
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
/** {*/
/*  position: center;*/
/*  margin: 0px;*/
/*  padding: 0px;*/
/*  text-decoration: none;*/
/*  font-family:sans-serif;*/
/*}*/


.box{
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
