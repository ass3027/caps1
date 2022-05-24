<template>
  <div>
    <header>
      <nav>
        <button type="submit" @click="bookmark">즐겨찾기</button> |
        <button type="submit" @click="pay">수익관리</button> |
        <button type="submit" @click="myData">내정보수정</button> |
        <button type="submit" @click="questions">1대1문의</button>
      </nav>
    </header>
    <h2>게시판 목록</h2>

    <v-text-field
      class="mx-4"
      flat
      hide-details
      label="Search"
      prepend-inner-icon="mdi-magnify"
      solo-inverted
      v-model="keyword"
    ></v-text-field>

<!--    <div class="searchWrap">-->
<!--      <input-->
<!--        v-model="keyword"-->
<!--        type="text"-->
<!--        @keyup.enter="Search"-->
<!--      >-->
<!--      <a-->
<!--        href="javascript:;"-->
<!--        class="btnSearch btn"-->
<!--        @click="Search"-->
<!--      >검색</a>-->
<!--    </div>-->

    <v-simple-table
      skyblue
      dense
    >
      <thead>
        <tr>
          <th
            class="text-center"
            style="font-size:20px;"
          >
            게시글번호
          </th>
          <th
            class="text-center"
            style="font-size:20px;"
          >
            제목
          </th>
          <th
            class="text-center"
            style="font-size:20px;"
          >
            날짜
          </th>
          <th
            class="text-center"
            style="font-size:20px;"
          >
            아이디
          </th>
          <th
            class="text-center"
            style="font-size:20px;"
          >
            조회수
          </th>
        </tr>
      </thead>
      <tbody>
        <tr
          v-for="(post,index) in post_list"
          :key="index"
          @click="listPage(post.inq_id)"
        >
          <th
            class="text-center"
            style="font-size:20px;"
          >
            {{ post.inq_id }}
          </th>
          <th
            class="text-center"
            style="font-size:20px;"
          >
            {{ post.inq_title }}
          </th>
          <th
            class="text-center"
            style="font-size:20px;"
          >
            {{ post.inq_time }}
          </th>
          <th
            class="text-center"
            style="font-size:20px;"
          >
            {{ post.user_id }}
          </th>
          <th
            class="text-center"
            style="font-size:20px;"
          >
            {{ post.inq_count }}
          </th>
        </tr>
      </tbody>
      <v-btn :disabled="pageNum === 0" v-on:click="prevPage" class="page-btn">이전</v-btn>
      <span class="page-count">{{pageNum + 1}} / {{pageCount}} 페이지</span>
      <v-btn :disabled="pageNum >= pageCount-1" v-on:click="nextPage" class="page-btn">다음</v-btn>

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
      pageNum : 0,
      inq_id: '',
      inq_title: '',
      inq_time: '',
      user_id: '',
      inq_count: 0,
      post_list:[],
      // paged_post_list:[]
      // tableList:[]
    }
  },
  props:{
    listArray: {
      type: Array,
      required: true
    },
    pageSize: {
      type: Number,
      required: false,
      default: 10
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
    myData() {
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
    listPage(index) {
      // axios.get("api/inquiry/Questions", {
      //   params:{
      //     inq_id:this.$route.params.id
      //   }
      // })
      // .then(res=>{
      //   // this.post_list = res.data;
      //   // console.log(res.data.inq_count)
      //   this.inq_count = res.data.inq_count;
      // })
      this.$router.push({
        name: 'DetailPage', params: {id: index}
      }).catch((error)=>{
        this.error = error
      })
    },
    prevPage() {
      this.pageNum -= 1;
    },
    nextPage() {
      this.pageNum += 1;
    },
    keyword(){

    }
  },
   computed:{
     pageCount() {
        let listLeng = this.post_list.length,
          listSize= this.pageSize,
          page = Math.floor(listLeng/listSize);
        if (listLeng % listSize > 0) page += 1;

        return page;
      },
     paged_post_list() {
       const start = this.pageNum * this.pageSize,
       end = start + this.pageSize;
       return this.post_list().slice(start, end);
       // paged_post_list()
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
