<template>
  <div
    id="app"
    style="width: 1050px; padding-top: 65px"
  >
    <div>
      <MyPageHeader />
      <div style="width: 820px; float: right;">

        <MyPageInfo v-if="selected === 'MyPageInfo'"/>
        <MyBookMark v-else-if="selected === 'MyBookMark'"/>
        <MyPageUpdate v-else-if="selected === 'MyPageUpdate'"/>
        <MyReview v-else-if="selected === 'MyReview'"/>
        <Questions v-else-if="selected === 'Questions'"/>
        <PriceChart v-else-if="selected === 'PriceChart'"/>
      </div>
    </div>
  </div>
</template>
<script>
  import MyPageHeader from "@/components/store/MyPageHeader";
  import axios from "axios";
  import MyBookMark from "@/components/auth/MyBookMark";
  import MyPageUpdate from "@/components/auth/MyPageUpdate";
  import MyReview from "@/components/auth/MyReview";
  import Questions from "@/components/auth/Questions";
  import MyPageInfo from "@/components/auth/MyPageInfo";
  import PriceChart from "@/components/auth/PriceChart";

  export default {
    components:{
      MyPageHeader,
      MyPageInfo,
      MyBookMark,
      MyPageUpdate,
      MyReview,
      Questions,
      PriceChart
    },
    props: {
      select: String
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
        business_num:'',

        selected: 'MyPageInfo',
      };
    },
    mounted() {
      axios.get('/api/user/data/'+ this.$store.state.user.userId).then(res=> {
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

      if(this.select !== undefined) {
        console.log('select', this.select)
        this.selected = this.select
      }

    },
    methods:{
      changeSelected(value) {
        this.selected = value
      },
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
