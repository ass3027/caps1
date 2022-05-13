<template>
<div>

  <header>
    <nav>
      <button @click="bookmark" type="submit">즐겨찾기</button> |
      <button @click="pay" type="submit">수익관리</button> |
      <button @click="mydata" type="submit">내정보수정 </button> |
      <button @click="questions" type="submit">1대1문의</button>
    </nav>
  </header>
  <h2>게시판 목록</h2>

<!--  <v-container>-->
<!--    <v-data-table-->
<!--      style="width:50%"-->
<!--      :headers="headers"-->
<!--      :items="contents"-->
<!--      :items-per-page="5"-->
<!--      class="elevation-1"-->
<!--    ></v-data-table>-->
<!--  </v-container>-->

     <v-simple-table
       skyblue
       dense
      >
        <thead>
        <tr>
         <th class="text-center">
            게시글번호
          </th>
          <th class="text-center">
            제목
         </th>
          <th class="text-center">
            날짜
         </th>
         <th class="text-center">
           아이디
          </th>
          <th class="text-center">
           조회수
         </th>
        </tr>
        </thead>
      </v-simple-table>

  <div id="button" >
    <v-btn v-on:click="check" type="button" color="pink">등록</v-btn>
    <v-btn v-on:click="cancel" type="button">취소</v-btn>
    <v-btn v-on:click="write" type="button">글쓰기</v-btn>
  </div>
</div>
</template>

<script>

import axios from "axios";

export default {
  name: "QuestionsView",
  data(){
    return {
      inq_type: '',
      inq_title: '',
      inq_body: '',
      items: ['여행시설문의', '가방관리문의', '가이드문의', '기타문의'],
      tableList:[]
    }
  },
  methods:{
    check(){
      console.log('성공')
    },
    cancel() {
    },
    bookmark(){
      this.$router.push('/BookMark')
    },
    pay(){
    },
    mydata(){
      this.$router.push('/MyData')
    },
    questions(){
      this.$router.push('/Questions')
    },
    write(){
      if (this.$store.state.user.userId){
        this.$router.push({name:'Writing'})
      }else{
        alert("로그인해주세요")
      }
      // this.$router.push('/Writing')
    }
  },
  mounted() {

  }
}
</script>

<style scoped>
#button{
  position: center;
  text-align: center;
  padding: 100px;
}



</style>
