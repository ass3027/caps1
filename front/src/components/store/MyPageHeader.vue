<template>
<v-container>
 <v-tabs
   fixed-tabs
   background-color="cyan"
   dark
  >
   <v-tab button type="submit" @click="MyPage">회원정보</v-tab>
   <v-tab button type="submit" @click="BookMark">즐겨찾기</v-tab>
   <v-tab button type="submit" @click="UserUpdate">회원정보수정</v-tab>
   <v-tab button type="submit" @click="questions">문의사항</v-tab>
 </v-tabs>
</v-container>
</template>

<script>
export default {
  data(){
    return{
    }
  },
  methods:{
    MyPage(){
      this.$router.push("/MyPage")
    },
    BookMark(){
      this.$router.push("/Bookmark")
    },
    UserUpdate(){
      this.$router.push("/MyPageUpdate")
    },
    questions(){
      this.$router.push("/Questions")
    }

  }
}
</script>

<style scoped>
.header{
  background-color: #1e90cc;
}
</style>
