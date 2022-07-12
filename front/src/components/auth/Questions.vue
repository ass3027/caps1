<template>
  <div style="width: 820px; float: right;">
    <div class="head_aticle">
      <h1 class="tit">
        게시판 목록
      </h1>
    </div>

    <!--        <div style="border-top: 2px solid #333">-->
    <v-row>
      <v-text-field
        style="margin: 10px"
        v-model="keyword"
        label="검색"
        class="mx-4"
        flat
        hide-details
        prepend-inner-icon="mdi-magnify"
        solo-inverted
        show-select
        @keyup.enter="search"
      />
      <v-btn style="margin:20px" @click="search">검색</v-btn>
    </v-row>

<v-row>
  <v-col>
    <!--실험용 코드-->
    <v-simple-table
      skyblue
      dense
    >
      <thead>
      <tr>
        <th class="text-center" style="font-size:20px;">게시글번호</th>
        <th class="text-center" style="font-size:20px;">제목</th>
        <th class="text-center" style="font-size:20px;">날짜</th>
        <th class="text-center" style="font-size:20px;">아이디</th>
        <th class="text-center" style="font-size:20px;">조회수</th>
      </tr>
      </thead>
      <tbody>
      <tr
        v-for="(data,index) in list"
        :key="index"
        @click="listPage(data.inq_id)"
      >
        <th class="text-center" style="font-size:15px;">{{ data.inq_id }}</th>
        <th class="text-center" style="font-size:20px;">{{ data.inq_title }}</th>
        <th class="text-center" style="font-size:20px;">{{ data.inq_time }}</th>
        <th class="text-center" style="font-size:20px;">{{ data.user_id }}</th>
        <th class="text-center" style="font-size:20px;">{{ data.inq_count }}</th>
      </tr>
      </tbody>
    </v-simple-table>
  </v-col>
</v-row>


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
  <!--  </div>-->
</template>

<script>

import axios from "axios";
export default {
  name: "QuestionsView",
  components:{

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
      headers:[
        {text:'게시글 번호', value: 'inq_id', align:'center'},
        {text:'제목', value: 'inq_title',align:'center'},
        {text:'날짜', value: 'inq_time',align:'center'},
        {text:'아이디', value: 'user_id',align:'center'},
        {text:'조회수', value: 'inq_count',align:'center'},
      ],
      keyword:'',
      pageSize: 10,
      list:[], //검색한 리스트
    }
  },
  computed:{
    items(){
      return this.post_list; //데이터값
    },
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
    // axios.get("/api/inquiry/Questions/")
    //   .then(res=>{
    //     console.log(res.data)
    //     this.list = res.data;
    //   }).catch((error)=>{
    //   this.error = error
    // })
    this.search()
  },
  methods: {
    search() {
      axios.get("/api/user/search" ,{
        params:{keyword:this.keyword}
      })
        .then(res=>{
          this.list = res.data
          console.log(res)
        }).catch((error)=> {
        this.error = error
      })
    },
    check() {
      console.log('성공')
    },
    bookmark() {
      this.$router.push('/BookMark')
    },
    write() {
      if (this.$store.state.user.userId) {
        this.$router.push({name: 'Writing'})
      } else {
        alert("로그인해주세요")
      }
      // this.$router.push('/Writing')
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
  }
}
</script>

<style scoped>
.v-application .text-start { text-align: center; font-size: 20pt}
.text-start { text-align: center; font-size: 20pt}

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

td {
  text-align: center;
}


</style>
