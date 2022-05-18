<template>
  <div>
    <header>
      <nav>
        <button type="submit" @click="bookmark">즐겨찾기</button> |
        <button type="submit" @click="pay">수익관리</button> |
        <button type="submit" @click="mydata">내정보수정</button> |
        <button type="submit" @click="questions">1대1문의</button>
      </nav>
    </header>
    <h2>게시판 목록</h2>
    <div class="searchWrap">
      <input
        v-model="keyword"
        type="text"
        @keyup.enter="Search"
      >
      <a
        href="javascript:;"
        class="btnSearch btn"
        @click="Search"
      >검색</a>
    </div>

    <v-simple-table
      skyblue
      dense
    >
      <thead>
        <tr>
          <th class="text-center" style="font-size:20px;">
            게시글번호
          </th>
          <th class="text-center" style="font-size:20px;">
            제목
          </th>
          <th class="text-center" style="font-size:20px;">
            날짜
          </th>
          <th class="text-center" style="font-size:20px;">
            아이디
          </th>
          <th class="text-center" style="font-size:20px;">
            조회수
          </th>
        </tr>
      </thead>
      <tbody>
        <tr
          v-for="(post,index) in post_list"
          :key="index"
          @click="detail(post.inq_id)"
        >
          <th class="text-center" style="font-size:20px;">
            {{ post.inq_id }}
          </th>
          <th class="text-center" style="font-size:20px;">
            {{ post.inq_title }}
          </th>
          <th class="text-center" style="font-size:20px;">
            {{ post.inq_time }}
          </th>
          <th class="text-center" style="font-size:20px;">
            {{ post.user_id }}
          </th>
          <th class="text-center" style="font-size:20px;">
            {{ post.inq_count }}
          </th>
        </tr>
      </tbody>
      <!--       <router-link to="/DetailPage"></router-link>-->
    </v-simple-table>
    {{ user_id }}
    {{ inq_title }}
    <div id="button">
      <v-btn
        type="button"
        color="pink"
        @click="check"
      >
        등록
      </v-btn>
      <v-btn
        type="button"
        @click="cancel"
      >
        취소
      </v-btn>
      <v-btn
        type="button"
        @click="write"
      >
        글쓰기
      </v-btn>
    </div>
  </div>
</template>

<script>

import axios from "axios";
import inquiryView from "@/views/bag/InquiryView";
import DetailPageView from "@/views/auth/DetailPageView";

export default {
  name: "QuestionsView",
  data() {
    return {
      inq_id: '',
      inq_title: '',
      inq_time: '',
      user_id: '',
      inq_count: '',
      post_list:[],
      // tableList:[]
    }
  },
  mounted() {
    axios.get("/api/inquiry/Questions/" )
    .then(res=>{
      console.log(res.data)
      this.post_list = res.data;

    }).catch((error)=>{
      this.error = error
    })
  },
  methods: {
    check() {
      console.log('성공')
    },
    cancel() {
    },
    bookmark() {
      this.$router.push('/BookMark')
    },
    pay() {
    },
    mydata() {
      this.$router.push('/MyData')
    },
    questions() {
      this.$router.push('/Questions')
    },
    write() {
      if (this.$store.state.user.userId) {
        this.$router.push({name: 'Writing'})
      } else {
        alert("로그인해주세요")
      }
      // this.$router.push('/Writing')
    },
   Search() { //검색
    this.paging.page = 1;
    this.fnGetList();
  },
    detail(index){
      this.$router.push({
        name: 'DetailPage', params: {
            id:index
        }
      })
    }
  }
}
</script>

<style scoped>
#button{
  position: center;
  text-align: center;
  padding: 100px;
}
.searchWrap{border:1px solid #888; border-radius:5px; text-align:center; padding:20px 0; margin-bottom:40px;}

</style>
