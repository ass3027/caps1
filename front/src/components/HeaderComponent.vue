<template>
  <v-app class="header-layout">
    <div
      v-if="$store.state.user.userId===''"
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
    <div
      v-else
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
    </div>
    <div>
      <div class="menu-Bar">
        <div class="text-center">
          <v-menu offset-y v-for="(menu,index) in menuList" :key="index">
            <template #activator="{ on, attrs }">
              <v-btn
                color="primary"
                dark
                v-bind="attrs"
                v-on="on"
              >
                {{ menu }}
              </v-btn>
            </template>
            <v-list>
              <v-list-item
                v-for="(content, index2) in contents[index]"
                :key="index2"
                router
                :to="content.route"
              >
                <v-list-item-title>{{ content.title }}</v-list-item-title>
              </v-list-item>
            </v-list>
          </v-menu>
        </div>
      </div>
      <v-divider class="divider-padding" />
    </div>
<!--    <img-->
<!--      :src="dd"-->
<!--      alt="실허어엄"-->
<!--      style="width:100px;height:100px"-->
<!--    >-->
  </v-app>
</template>

<script>
import axios from "axios";

export default {

  name: 'HelloWorld',
  data: () => ({
    // dd: `/api/photo/`+"userImage/1648100757821img.jpg",
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
        {title: '일정', route: '/calender'},
        {title: '준비물', route: '/supplies'},
        {title: '플래너 생성', route: '/plan'},
        {title: '플래너 초대', route: '/plInvite'},
        {title: 'plan5', route: '/plan'}
      ],
      [
        {title: '호텔', route: '/hotel'},
        {title: '펜션', route: '/facility'},
        {title: '글램핑', route: '/facility'},
        {title: '리조트', route: 'facility'},
        {title: '게스트하우스', route: 'facility'}
      ],
      [
        {title: '가방예약', route: '/BagReserveView'},
        {title: '이용안내', route: '/UsageGuideView'},
        {title: '요금', route: '/FareView'},
        {title: '후기', route: '/ReviewView'},
        {title: '배송조회', route: '/bag'}
      ],
      [
        {title: 'GuideView', route: '/guideview'},
        {title: 'GuideRegister', route: '/GuideRegister'},
        {title: 'GuideProductReg', route: '/GuideProductReg'},
        {title: 'GuideReserve', route: '/GuideReserve'},
        {title: 'Guide5', route: '/Guide'}
      ],
      [
        {title: 'Community1', route: '/Community'},
        {title: 'Community2', route: '/Community'},
        {title: '공유', route: '/share'},
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
  }),
  methods: {
    logOut(){
      console.log(22)
      axios({
        url:'/api/logout',
        method:'post'
      })
      .then((res)=>{
        console.log(res)
        this.$store.commit('user/updateUserId','')
        this.$router.push("/")
      })
      .catch((err)=>{
        console.error(err)
      })
    }
  }
};
</script>

<style scoped>
.header-layout {
  display: flex;
  height: 20vh;
  flex-direction: column;
  justify-content: space-between;
}

.login-box {
  display: flex;
  justify-content: flex-end;
}

.menu-Bar {
  display: flex;
  justify-content: center;
}

.text-center {
  margin-top: 20px;
  display: flex;
  justify-content: center;
}

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

.divider-padding {
  margin-top: 10px;
}
</style>
