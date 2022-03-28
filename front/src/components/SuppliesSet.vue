<template>
  <v-container class="test">

    <h3>{{sets[0].pl_name}} 관련 세트</h3>
    <ol>
      <li v-for="(item, index) in sets" :key="index">
        {{ item.supl_id }} {{ item.supl_name }}
<!--        <v-btn v-on:click="makeDone(item.id,item.done)">완료</v-btn>-->
        <v-btn fab text small color="green" @click="sendItem(item)">가져오기</v-btn>
      </li>
    </ol>
    <v-btn fab text small color="red" @click="sendList()">모두가져오기</v-btn>
    <hr>
    {{sets}}
    <hr>
    <v-btn @click="handleToggle">
      모달?
    </v-btn>
    <div v-show="toggle">
      <h5>뷰하!</h5>
      <p>v-if와 v-show로 모달창을 띄워봅시다.</p>

      <v-btn @click="handleToggle">
        확인
      </v-btn>
    </div>
  </v-container>
</template>

<script>
// import axios from "axios";

import axios from "axios";

export default {
  name: "SuppliesSet",
  data() {
    return {
      inputItem: "",
      todoList: [{todo:"test",quantity:"입니다"}],
      doneList: [],
      supplies: [],
      toggle: false,
    }
  },
  props:['sets'],
  mounted() {

  },
  methods: {
    sendItem(item) {
      console.log(item)
      axios.post("/api/sendItem",item).then(()=>{
      })
    },
    sendList() {
      axios.post("/api/sendList",this.sets).then(()=>{
      })
    },
    getList() {

    },
    getMyList() {

    },
    inputList(e) {
      e.preventDefault();
    },
    makeDone(id, done) {
      console.log(id,done)
    },
    allDelete() {
    },
    handleToggle() {
      this.toggle = !this.toggle;
    }

  }
}
</script>

<style scoped>
  .test{
    border: solid;

  }
</style>