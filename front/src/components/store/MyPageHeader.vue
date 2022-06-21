<template>
  <v-card color="header">
<!--    <v-card-title class="text-center justify-center py-6">-->
<!--      <h1 class="font-weight-bold text-h2 basil&#45;&#45;text">-->
<!--        ㅇㅇ-->
<!--      </h1>-->
<!--    </v-card-title>-->

    <v-tabs
      v-model="tab"
      background-color="transparent"
      color="header"
      grow
    >

      <v-tab button type="submit" @click="MyPage">회원정보</v-tab>
      <v-tab button type="submit" @click="BookMark">즐겨찾기</v-tab>
      <v-tab button type="submit" @click="UserUpdate">회원정보수정</v-tab>
      <v-tab button type="submit" @click="questions">문의사항</v-tab>

<!--      <v-tab-->
<!--        v-for="item in items"-->
<!--        :key="item"-->
<!--      >-->
<!--        {{ item }}-->
<!--      </v-tab>-->
    </v-tabs>

    <v-tabs-items v-model="tab">
      <v-tab-item
        v-for="item in items"
        :key="item"
      >
        <v-card
          color="basil"
          flat
        >
<!--          <v-card-text>{{ text }}</v-card-text>-->
        </v-card>
      </v-tab-item>
    </v-tabs-items>
  </v-card>







<!--<v-container>-->
<!--  <v-card color="header">-->
<!--   <v-card-title class = "text-center justify-center py-6">-->
<!--    <h1 class="font-weight-bold text-h2 basil&#45;&#45;text">-->
<!--      BASiL-->
<!--    </h1>-->
<!--  </v-card-title>-->

<!--  <v-tabs-->
<!--    class="header"-->
<!--    fixed-tabs-->
<!--    v-model="tab"-->
<!--    background-color="transparent"-->
<!--    color="basil"-->
<!--    grow-->
<!--  >-->
<!--    <v-tab-->
<!--      v-for="item in items"-->
<!--      :key="item"-->
<!--    >-->
<!--      {{item}}-->
<!--      </v-tab>-->
<!--  </v-tabs>-->

<!--  <v-tabs-items v-model="tab">-->
<!--    <v-tab-item-->
<!--      v-for="item in items"-->
<!--      :key="item"-->
<!--    >-->
<!--      <v-card-->
<!--        color="basil"-->
<!--        flat-->
<!--      >-->
<!--&lt;!&ndash;        <v-card-text>{{test}}</v-card-text>&ndash;&gt;-->
<!--      </v-card>-->
<!--    </v-tab-item>-->
<!--  </v-tabs-items>-->
<!--</v-card>-->








    <!--      <v-tab button type="submit" @click="MyPage">회원정보</v-tab>-->
<!--      <v-tab button type="submit" @click="BookMark">즐겨찾기</v-tab>-->
<!--      <v-tab button type="submit" @click="UserUpdate">회원정보수정</v-tab>-->
<!--      <v-tab button type="submit" @click="questions">문의사항</v-tab>-->

<!--  <v-tabs-->
<!--     class="header"-->
<!--     fixed-tabs-->
<!--     background-color="transparent"-->
<!--     color="basil"-->
<!--     grow-->
<!--     dark>-->
<!--  <v-tab button type="submit" @click="MyPage">회원정보</v-tab>-->

<!--   <v-tabs-->
<!--     class="header"-->
<!--     fixed-tabs-->
<!--     background-color="transparent"-->
<!--     color="basil"-->
<!--     grow-->
<!--     dark-->
<!--     ></v-tabs>-->
<!--    <v-tab button type="submit" @click="BookMark">즐겨찾기</v-tab>-->




<!--   <v-tab button type="submit" @click="BookMark">즐겨찾기</v-tab>-->
<!--   <v-tab button type="submit" @click="UserUpdate">회원정보수정</v-tab>-->
<!--   <v-tab button type="submit" @click="questions">문의사항</v-tab>-->

<!-- </v-tabs>-->
<!--</v-container>-->
</template>

<script>
export default {
  data(){
    return{
      tab :null,
      items:['회원정보', '즐겨찾기','회원정보수정', '문의사항'],


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
.header{
  background-color: #FFFBE6 !important;
  color: #356859 !important;
}
.basil--text{
  color: #356859 !important;
}
</style>
