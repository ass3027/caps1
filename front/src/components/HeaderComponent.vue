<template>
  <!---->
  <!---->
  <!---->
  <!---->
  <!---->
  <v-app
    class="header-layout"
    style="min-width: 650px"
  >
    <div id="userMenu">
      <!--로그인 박스(로그인 O)-->
      <div
        v-if="isLogin"
        class="login-box"
      >
        <v-btn text>
          {{ $store.state.user.userId }}
        </v-btn>

        <v-btn
          text
          @click="logOut"
        >
          logout
        </v-btn>
        <img
          :src="photo"
          style="width:150px;height:150px"
          alt=""
        >
      </div>
      <!--로그인 박스(로그인 X)-->
      <div
        v-else
        class="login-box"
      >
        <v-btn
          text
          router
          to="/login"
        >
          login
        </v-btn>
        <v-btn
          text
          router
          to="/join"
        >
          join
        </v-btn>
      </div>
    </div>

    <div id="headerLogo">
      <h1 class="logo" style="text-align: center">
        <a href="/" style="display: inline-block">
          <img src="https://res.kurly.com/images/marketkurly/logo/logo_x2.png" alt="마켓컬리 로고" style="display: block; width: 103px; height: 79px">
        </a>
      </h1>
    </div>

    <div>
      <div
        class="menu-Bar"
        style="width: 1050px"
      >
        <v-menu
          v-for="(menu,index) in menuList"
          :key="index"
          offset-y
        >
          <template #activator="{ on, attrs }">
            <div
              style="display: flex; justify-content: center; width: 30%; text-align: center; height: 55px"
            >
            <span
              v-bind="attrs"
              v-on="on"
              style="padding-top: 15px"
            >
              {{ menu }}
            </span>
            </div>
          </template>

          <v-list>
            <v-list-item
              v-for="(content, index2) in contents[index]"
              :key="index2"
              router
              :to="content.route"
              style="display: flex; justify-content: center"
            >
              <span>{{ content.title }}</span>
            </v-list-item>
          </v-list>

        </v-menu>

        <v-divider style="margin-top: 10px" />
      </div>
    </div>

  </v-app>
</template>

<script>
import axios from "axios";
import {EventBus} from "@/eventBus/eventBus";





export default {

  name: 'HelloWorld',
  data: () => ({
    photo: '',
    menuList:[
      "여행지",
      "여행계획",
      "시설",
      "가방관리",
      "현지인 가이드",
      "커뮤니티",
      "고객센터"
    ],
    contents: [
      [
        {title: 'travel1', route: '/travel'},
        {title: 'travel2', route: '/travel'},
        {title: 'travel3', route: '/travel'},
        {title: 'travel4', route: '/travel'},
        {title: 'travel5', route: '/travel'}
      ],
      [
        {title: '플래너 생성', route: '/plan'},
        {title: '플래너 초대', route: '/plInvite'},
        {title: '일정', route: '/calender'},
        {title: '준비물', route: '/supplies'},
        {title: '플래너 사진', route: '/planPic'}
      ],
      [
        {title: '호텔', route: '/hotel'},
        {title: '모텔', route: '/store'},
        {title: '펜션', route: '/store'},
        {title: '글램핑', route: '/store'},
        {title: '리조트', route: '/store'},
        {title: '게스트하우스', route: '/store'}
      ],
      [
        {title: '가방예약', route: '/SelectionOrder'},
        {title: '이용안내', route: '/UsageGuideView'},
        {title: '요금', route: '/FareView'},
        {title: '후기', route: '/ReviewView'},
        {title: '배송조회', route: '/TrackingView'},

      ],
      [
        {title: '가이드 리스트', route: '/guideview'},
        {title: '가이드 등록', route: '/GuideRegister'},
        {title: '가이드 예약 ', route: '/GuideReserve'},
        {title: '가이드 상품 등록', route: '/GuideProductReg'},
        {title: '가이드 상품', route: '/GuideProduct'}
      ],
      [
        {title: '공유', route: '/share'},
        {title: 'Community1', route: '/Community'},
        {title: 'Community2', route: '/Community'},
        {title: 'Community4', route: '/Community'},
        {title: 'Community5', route: '/Community'}
      ],
      [
        {title: 'Customer1 ', route: '/Customer'},
        {title: 'Customer2', route: '/Customer'},
        {title: 'Customer3', route: '/Customer'},
        {title: 'Customer4', route: '/Customer'},
        {title: 'Customer5', route: '/Customer'}
      ]
    ],
    scroll: null,
  }),
  computed:{

    isLogin() {
      console.log(this.$store.state.user.userId)
      console.log(this.$store.getters['user/isLogin'])
      return this.$store.getters['user/isLogin']
    },
  },
  created(){
    EventBus.$on("photoUpdate",(photo)=>{
      console.log(11)
      console.log(decodeURI(photo))
      this.photo = "/api/photo/"+decodeURI(photo)
    })
  },
  mounted() {
    // TODO document why this method 'mounted' is empty



  },
  methods: {
    logOut(){
      console.log(22)
      axios({
        url:'/api/logout',
        method:'post'
      })
      .then((res)=>{
        console.log(res)
        this.$store.dispatch('user/setUser','anonymousUser')
        this.$router.push("/")
      })
      .catch((err)=>{
        console.error(err)
      })
    },
    // store(a){
    //   this.$router.push({path:a.route, params:{'value':a.title}})
    // }
  }
};
</script>

<style>
/*#gnb {*/
/*  display: flex;*/
/*  justify-content: center;*/
/*}*/
/*.gnb_stop {*/
/*  z-index: 300;*/
/*  position: fixed;*/
/*  top: 0;*/
/*  left: 0;*/
/*  width: 100%;*/
/*  height: 67px;*/
/*  background-color: white;*/
/*}*/

.login-box {
  display: block;
  /*justify-content: flex-end;*/
  float: right;
}

.menu-Bar {
  display: flex;
  justify-content: center;
  margin: 0 auto;
  width: 100%;
}

/*.text-center {*/
/*  margin-top: 20px;*/
/*  display: flex;*/
/*  justify-content: center;*/
/*}*/

/*.hotel-list-form {*/
/*  display: flex;*/
/*  align-items: center;*/
/*}*/

/*.select-box {*/
/*  padding: 0 2%;*/
/*}*/

/*.select-size {*/
/*  width: 50%;*/
/*}*/

/*.divider-padding {*/
/*  margin-top: 10px;*/
/*}*/
</style>

