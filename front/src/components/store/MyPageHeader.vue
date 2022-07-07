<template>
  <div
    id="snb"
    class="snb_my"
  >
    <h2 class="tit_snb">
      마이페이지
    </h2>

    <div class="inner_snb">
      <ul
        class="list_menu"
        style="padding-left: 0"
      >
        <li id="MyPage">
          <a @click="change('MyPageInfo')">회원 정보</a>
        </li>
        <li id="Bookmark">
          <a @click="change('Bookmark')">즐겨찾기</a>
        </li>
        <li id="MyPageUpdate">
          <a @click="change('MyPageUpdate')">개인 정보 수정</a>
        </li>
        <li id="MyPaymentList">
          <a @click="change('MyReview')">상품 후기</a>
        </li>
        <li id="Questions">
          <a @click="change('Questions')">문의사항</a>
        </li>
        <li id="Review">
          <a>상품후기</a>
        </li>
      </ul>
    </div>
    <a class="link_inquire">
      <span class="emph">도움이 필요하신가요 ?</span>
      1:1 문의하기

    </a>
    <!-- <v-tab button type="submit" @click="MyPage">회원정보</v-tab>-->
    <!-- <v-tab button type="submit" @click="BookMark">즐겨찾기</v-tab>-->
    <!-- <v-tab button type="submit" @click="UserUpdate">회원정보수정</v-tab>-->
    <!-- <v-tab button type="submit" @click="questions">문의사항</v-tab>-->
  </div>
</template>

<script>
export default {
  data() {
    return {}
  },
  mounted() {
    var currentPath = document.location.pathname.slice(1)
    var current_li = document.getElementById(currentPath + '')
    current_li.classList.add('on')
  },
  methods: {
    change(value) {
      this.$parent.changeStatus(value)
    },
    MyPage() {
      this.change("MyPageInfo")
    },
    BookMark() {
      this.change("Bookmark")

    },
    UserUpdate() {
      this.change("MyPageUpdate")

    },
    questions() {
      this.change("Questions")

    },

  }
}
</script>

<style scoped>


.tit_snb {
  padding: 5px 0 35px 1px;
  font-weight: 700;
  font-size: 28px;
  line-height: 35px;
  color: #333;
  letter-spacing: -1px;
}

#snb {
  float: left;
  width: 200px;
}

#snb .inner_snb {
  border: 1px solid #f2f2f2;
  border-bottom: 0
}

#snb .tit_snb {
  padding: 5px 0 35px 1px;
  font-weight: 700;
  font-size: 28px;
  line-height: 35px;
  color: #333;
  letter-spacing: -1px
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

#snb .link_inquire {
  display: block;
  overflow: hidden;
  width: 100%;
  height: 60px;
  margin-top: 20px;
  padding: 9px 0 0 22px;
  border-radius: 0 100px 100px 0;
  background: #fafafa url(https://res.kurly.com/pc/ico/2008/ico_arrow_6x11_on.svg) no-repeat 175px 25px;
  background-size: 6px 11px;
  font-size: 12px;
  color: #333;
  line-height: 20px
}

#snb .link_inquire .emph {
  display: block;
  padding-bottom: 1px;
  font-weight: 700;
  font-size: 14px
}

li {
  list-style: none;
}

</style>
