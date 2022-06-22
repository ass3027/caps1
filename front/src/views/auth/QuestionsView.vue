<template>
  <div
    id="app"
    style="width: 1050px; padding-top: 65px"
  >
    <MyPageHeader />
    <div style="width: 820px; float: right;">
      <div class="head_aticle">
        <h1 class="tit">
          게시판 목록
        </h1>
      </div>

      <div style="border-top: 2px solid #333">
        <v-text-field
          v-model="keyword"
          class="mx-4"
          flat
          hide-details
          label="Search"
          prepend-inner-icon="mdi-magnify"
          solo-inverted
        />


        <v-data-table
          dense
          :headers="post_list"
          :items="dd"
          item-key="name"
          class="elevation-1"
        />


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
              v-for="(post,index) in paged_post_list"
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
        </v-simple-table>
        <div style="text-align: center">
          <div style="display: inline-block;">
            <v-btn
              :disabled="pageNum === 0"
              class="page-btn"
              @click="prevPage"
            >
              이전
            </v-btn>
            <span class="page-count">{{ pageNum + 1 }} / {{ pageCount }} 페이지</span>
            <v-btn
              :disabled="pageNum+1 >= pageCount"
              class="page-btn"
              @click="nextPage"
            >
              다음
            </v-btn>
          </div>
        </div>

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
          <!--      <v-btn type="button" @click="cancel">취소</v-btn>-->
          <v-btn
            type="button"
            @click="write"
          >
            글쓰기
          </v-btn>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import MyPageHeader from "@/components/store/MyPageHeader";
import axios from "axios";
export default {
  name: "QuestionsView",
  components:{
    MyPageHeader
  },

  props:{ //매개변수, 값을 받아올떄 쓰는아이
    // listArray: {
    //   type: Array,
    //   required: true
    // },
    // pageSize: {
    //   type: Number,
    //   required: false,
    //   default: 10
    // }

  },
  data() {
    return {
      pageNum : 0,
      inq_id: '',
      inq_title: '',
      inq_time: '',
      user_id: '',
      inq_count: 0,
      post_list:[],

      pageSize: 10
      // paged_post_list:[]
      // tableList:[]
    }
  },

   computed:{
     pageCount() {
       // let page = Math.floor(this.post_list.length/this.pageSize);
       //  if (this.post_list.length % this.pageSize > 0) page += 1;
       return Math.floor((this.post_list.length - 1) / this.pageSize) + 1
      },
     paged_post_list() {
       const start = this.pageNum * this.pageSize,
         //  start =      0 * 10 = 0
       end = start + this.pageSize;
       // end = 0 + 10 = 10
       return this.post_list.slice(start, end);
       //  0~9번째 까지
       // paged_post_list()
     }
  },

  mounted() {
    axios.get("/api/inquiry/Questions/")
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
  }
}
</script>

<style scoped>
#button{
  position: center;
  text-align: center;
  padding: 100px;
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
