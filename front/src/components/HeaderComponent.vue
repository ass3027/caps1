<template>
  <v-container class="header-layout">

    <div id="userMenu">
      <ul class="list_menu">
        <!----> <!---->
        <li
          v-if="isLogin"
          class="menu menu_user"
          @click="MyPage"
        >
          <a
            class="link_menu grade_comm"
            style="font-size:24px"
          >
            <span
              class="ico_grade grade0"
              style="padding: 0; margin: 0 10px 0 0;"
            >{{ user_role }}</span>
            <span class="txt">
              <span class="name">{{ user_name }}</span>
              <span class="sir">님</span>
            </span> <!---->
          </a>
          <ul class="sub">
            <li><a>주문 내역</a></li>
            <li><a>선물 내역</a></li>
            <li><a type="button" @click="bookmark">찜한 상품</a></li>
            <li><a>배송지 관리</a></li>
            <li><a>상품 후기</a></li>
            <li><a>상품 문의</a></li>
            <li><a>적립금</a><!----></li>
            <li><a>쿠폰</a><!----></li>
            <li><a type="button" @click="update">개인 정보 수정</a></li>
            <li><a @click="logOut">로그아웃</a></li>
          </ul>
        </li>
        <template v-else>
          <li

            class="menu none_sub menu_join"
          >
            <a
              class="link_menu"
              href="/join"
            >회원가입</a>
          </li>
          <li

            class="menu none_sub menu_login"
          >
            <a
              class="link_menu"
              href="/login"
            >로그인</a> <!---->
          </li>
        </template>


        <li class="menu lst">
          <a
            href="/shop/board/list.php?id=notice"
            class="link_menu"
            style="font-size:24px"
          >고객센터</a>
          <ul class="sub">
            <li>
              <a
                href="#none"
                onclick="KurlyTrackerLink('/shop/board/list.php?id=notice', 'select_my_kurly_notice_list')"
              >공지사항</a>
            </li>
            <li>
              <a
                href="#none"
                onclick="KurlyTrackerLink('/shop/service/faq.php', 'select_my_kurly_frequently_qna')"
              >자주하는
                질문</a>
            </li>
            <li>
              <a
                href="#none"
                onclick="KurlyTrackerLink('/mypage/inquiry/list', 'select_my_kurly_personal_inquiry_history')"
              >1:1
                문의</a>
            </li>
            <li>
              <a
                href="#none"
                onclick="KurlyTrackerLink('/shop/main/html.php?htmid=mypage/bulk_order.htm', 'select_my_kurly_bulk_order')"
              >대량주문
                문의</a>
            </li>
            <li>
              <a
                href="#none"
                onclick="KurlyTrackerLink('/shop/mypage/offer.php', 'select_my_kurly_product_offer')"
              >상품
                제안</a>
            </li>
            <li>
              <a
                href="#none"
                onclick="KurlyTrackerLink('/shop/mypage/echo_packing.php', 'select_my_kurly_eco_packing_feedback')"
              >에코포장
                피드백</a>
            </li>
          </ul>
        </li>
      </ul>
    </div>
    <div id="headerLogo">
      <h1
        class="logo"
        style="text-align: center"
      >
        <a
          href="/"
          style="display: inline-block"
        >
          <img
            alt="PacklessTravel 로고"
            src="/api/photo/altImage/packless_travel_logo.png"
            style="display: block;height: 100px;"
          >
        </a>
      </h1>
    </div>

    <!--    일반회원 헤더 -->
    <div v-if="user_role===`일반회원` || user_role=== undefined">
      <div
        class="menu-Bar"
        style="width: 1050px"
      >
        <v-menu
          v-for="(menu,index) in gMenuList"
          :key="index"
          offset-y
        >
          <template #activator="{ on, attrs }">
            <div
              style="display: flex; justify-content: center; width: 30%; text-align: center; height: 55px"
            >
              <span
                v-bind="attrs"
                style="padding-top: 15px"
                v-on="on"
              >
                {{ menu }}
              </span>
            </div>
          </template>

          <v-list>
            <v-list-item
              v-for="(content, index2) in gContents[index]"
              :key="index2"
              router
              :to="content.route"
              style="display: flex; justify-content: center"
            >
              <span>{{ content.title }}</span>
            </v-list-item>
          </v-list>
        </v-menu>

        <v-divider style="margin-top: 10px"/>
      </div>
    </div>

    <!--    키퍼회원 헤더 -->
    <div v-else-if="user_role===`키퍼회원`">
      <div
        class="menu-Bar"
        style="width: 1050px"
      >
        <v-menu
          v-for="(menu,index) in kMenuList"
          :key="index"
          offset-y
        >
          <template #activator="{ on, attrs }">
            <div
              style="display: flex; justify-content: center; width: 30%; text-align: center; height: 55px"
            >
              <span
                v-bind="attrs"
                style="padding-top: 15px"
                v-on="on"
              >
                {{ menu }}
              </span>
            </div>
          </template>

          <v-list>
            <v-list-item
              v-for="(content, index2) in kContents[index]"
              :key="index2"
              router
              :to="content.route"
              style="display: flex; justify-content: center"
            >
              <span>{{ content.title }}</span>
            </v-list-item>
          </v-list>
        </v-menu>

        <v-divider style="margin-top: 10px"/>
      </div>
    </div>

  </v-container>
</template>

<script>
import axios from "axios";
import {EventBus} from "@/eventBus/eventBus";


export default {

  name: 'HelloWorld',
  data() {
    return {
      photo: '',

      gMenuList: [
        "운송원",
        "여행계획",
        "시설",
        "가방관리",
        "현지인 가이드",
        "커뮤니티",
        "고객센터",
        "통계"
      ],

      gContents: [
        [
          {title: '운송원', route: '/DuserOrders'},
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
          {title: '호텔', route: '/place/hotel'},
          {title: '모텔', route: '/place/motel'},
          {title: '펜션', route: '/place/pension'},
          {title: '게스트하우스', route: '/place/home'},
          {title: '예약 조회', route: '/productBookView'},
          {title: '매출 조회', route: '/chart'}
        ],
        [
          {title: '가방예약', route: '/SelectionOrder'},
          {title: '물품조회', route: '/GTrackingView'},
          {title: '키퍼물품조회', route: '/KeeperTrackingView'}
        ],
        [
          {title: '가이드 등록', route: '/GuideRegister'},
          {title: '가이드 리스트', route: '/guideview'},
          {title: '가이드 상품 등록', route: '/GuideProductReg'},
          {title: '가이드 상품', route: '/GuideProduct'},
          {title: '매출 관리 ', route: '/GuideReserve'},
          {title: '예약 정보', route: '/GuideReserveInfo'},
        ],
        [
          {title: '공유게시판', route: '/share'},
        ],
        [
          {title: '문의사항 ', route: '/Questions'},
          {title: '게시글 등록', route: '/Writing'},
          {title: 'Customer3', route: '/Customer'},
          {title: 'Customer4', route: '/Customer'},
          {title: 'Customer5', route: '/Customer'}
        ],
        [
          {title: '연령별 가입추이 ', route: '/AgeChart'},
          {title: '지역별 가입추이', route: '/AreaChart'},
          {title: '', route: ''},
          {title: '', route: ''},
          {title: '', route: ''}
        ]
      ],
      kMenuList: [
        "시설",
        "가방관리",
        "커뮤니티",
        "고객센터",
      ],

      kContents: [
        [
          {title: '장소등록', route: '/placeAdd'},
          {title: '내등록숙박조회', route: '/placeCheck'},
        ],
        [
          {title: '키퍼물품조회', route: '/KeeperTrackingView'}
        ],
        [
          {title: '공유게시판', route: '/share'},
        ],
        [
          {title: '문의사항 ', route: '/Questions'},
          {title: '게시글 등록', route: '/Writing'},
        ],

      ],
      scroll: null,
      user_name: '',
      user_role: null,
    }
  },
  computed: {
    isLogin() {
      return this.$store.getters['user/isLogin']
    },


  },
  created() {
    this.updateUser()
    EventBus.$on("photoUpdate", (photo) => {
      this.photo = "/api/photo/" + decodeURI(photo)
    })

    EventBus.$on("updateId", () => {
      this.updateUser()
    })
  },
  methods: {
    async updateUser() {
      const {data} = await axios.get("/api/user/find")
      // const user
      console.log('유저정보', data)
      this.user_name = data.user_name
      if (data.role === 'keeper') this.user_role = '키퍼회원'
      else if (data.role === 'delivery') this.user_role = '운송회원'
      else this.user_role = '일반회원'

      const temp = {id: data.user_id, role: data.role}
      await this.$store.dispatch("user/setUser", temp)

    },
    logOut() {
      console.log(22)
      axios({
        url: '/api/logout',
        method: 'post'
      })
        .then((res) => {
          console.log(res)
          this.$store.dispatch('user/setUser', {user_id: 'anonymousUser', user_role: null},)
          // this.$router.push("/")
          location.href = "/"
        })
        .catch((err) => {
          console.error(err)
        })
    },
    // store(a){
    //   this.$router.push({path:a.route, params:{'value':a.title}})
    // }
    MyPage() {
      this.$router.push("/MyPage")
    },
    update() {
      this.$router.push("/MyPage/MyPageUpdate")
    },
    bookmark() {
      this.$router.push("/MyPage/BookMark")
    }
  }
};
</script>

<style scoped>
.menu-Bar {
  display: flex;
  justify-content: center;
  margin: 0 auto;
  width: 100%;
}

#userMenu {
  width: 1050px;
  margin: 0 auto;
  font-size: 20px;
}

#userMenu * {
  font-weight: 400;
  letter-spacing: -.3px
}

#userMenu:after {
  content: "";
  display: block;
  overflow: hidden;
  width: 100%;
  height: 0;
  font-size: 0;
  text-indent: -9999px
}

#userMenu .list_menu {
  float: right;
}

#userMenu .menu {
  position: relative;
  z-index: 400;
  float: left;
  font-size: 14px;
}

#userMenu .menu:before {
  content: "";
  position: absolute;
  right: 13px;
  top: 16px;
  width: 8px;
  height: 5px;
  background: url(https://res.kurly.com/pc/ico/1908/ico_down_8x5.png) no-repeat 0 0
}

#userMenu .menu:after {
  content: "";
  float: right;
  width: 1px;
  height: 13px;
  margin-top: 12px;
  background-color: #d8d8d8
}

#userMenu .link_menu {
  float: left;
  height: 37px;
  padding: 0 12px;
  color: #333;
  line-height: 35px;
  white-space: nowrap
}

#userMenu .txt {
  float: left;
  padding-left: 4px
}

#userMenu .name {
  overflow: hidden;
  float: left;
  text-align: right;
  white-space: nowrap;
  text-overflow: ellipsis;

}

#userMenu .sir {
  float: left;
  padding: 0 1px 0 2px
}

#userMenu .ico_new {
  width: 10px;
  height: 10px;
  margin: 13px 0 0 2px;
  vertical-align: top
}

#userMenu .ico_grade {
  float: left;
  margin-top: 10px
}

#userMenu .menu_user .link_menu {
  padding-right: 24px
}

#userMenu .menu_user .sub {
  left: 11px;
}

#userMenu .sub {
  display: none;
  position: absolute;
  left: 0;
  top: 34px;
  width: 102px;
  padding: 3px 9px;
  border: 1px solid #ddd;
  background-color: #fff
}

#userMenu .lst .sub {
  left: auto;
  right: 0
}

#userMenu .menu:hover .sub {
  display: block
}

#userMenu .none_sub:hover .sub {
  display: none
}

#userMenu .sub a {
  font-size: 12px;
  color: #404040;
  line-height: 24px;
  white-space: nowrap;
  cursor: pointer
}

#userMenu .sub .ico_new {
  margin: 7px 0 0 1px
}

#userMenu .none_sub:before, #userMenu .lst:after {
  display: none
}

#userMenu .menu_join .link_menu {
  color: #5f0080
}

#userMenu .menu.lst:before {
  right: 0
}

#userMenu .menu.lst .link_menu {
  padding-right: 13px
}

#userMenu .menu .sub.on {
  display: block
}

#snb .list_menu li {
  border-bottom: 1px solid #f2f2f2
}

#snb .list_menu li a {
  display: block;
  overflow: hidden;
  padding: 14px 0 16px 20px;
  background: #fff url(https://res.kurly.com/pc/ico/2008/ico_arrow_6x11.svg) no-repeat 174px 52%;
  background-size: 6px 11px;
  font-size: 14px;
  color: #666;
  line-height: 20px;
  letter-spacing: -.3px
}

#snb .list_menu li.on a, #snb .list_menu li a:hover {
  background: #fafafa url(https://res.kurly.com/pc/ico/2008/ico_arrow_6x11_on.svg) no-repeat 174px 52%;
  background-size: 6px 11px;
  font-weight: 700;
  color: #5f0080
}

ol, ul {
  list-style-type: none;
}

#userMenu .none_sub:hover .sub {
  display: none
}

#userMenu .none_sub:before, #userMenu .lst:after {
  display: none
}


</style>

