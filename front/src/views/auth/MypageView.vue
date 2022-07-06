<template>
  <div
    id="app"
    style="width: 1050px; padding-top: 65px"
  >
    <div>

      <MyPageHeader/>
      <div style="width: 820px; float: right;">
        <MyPageInfo v-if="status === 'MyPageInfo'"/>
        <BookMarkView v-else-if="status === 'Bookmark'"/>
        <MyPageUpdateView v-else-if="status === 'MyPageUpdate'"/>
        <QuestionsView v-else-if="status === 'Questions'"/>
        <MyPaymentList v-else-if="status === 'MyPaymentList'"/>
      </div>
    </div>
  </div>
  <!--  </div>-->
</template>
<script>
import MyPageHeader from "@/components/store/MyPageHeader";

import axios from "axios";
import MyPageInfo from "@/components/auth/MyPageInfo";
import BookMarkView from "@/components/auth/BookMarkView";
import MyPageUpdateView from "@/components/auth/MyPageUpdateView";
import QuestionsView from "@/components/auth/QuestionsView";
import MyPaymentList from "@/components/auth/MyPaymentList";

export default {
  components: {
    MyPageHeader,
    MyPageInfo,
    BookMarkView,
    MyPageUpdateView,
    QuestionsView,
    MyPaymentList
  },
  props: {
    type: {
      type: String,
      default: 'MyPageInfo'
    }
  },
  data() {
    return {
      user_id: '',
      user_pw: '',
      user_phone: '',
      user_name: '',
      user_birth: '',
      user_photo: '',
      gender: '',
      preference: '',
      user_area: true,
      duser_trans: '',
      duser_license: '',
      business_num: '',
      status: '',

    };
  },
  created() {
    this.status = this.type
  },
  mounted() {

    axios.get('/api/user/data/' + this.$store.state.user.userId)
      .then(res => {
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

li {
  list-style: none;
}

</style>
