<template>
  <div>
    <HelloWorld/>
    <div class="right">
      <h2>현재 멤버</h2>
      <div v-for="(data,key) in arr" :key="key">
        <hr>
        <p>{{data}}</p>
      </div>
    </div>
    <div class="left">
      <h2>멤버 초대</h2>
      <hr>
      <label>
        회원ID <input id="idInput" placeholder="내용을 입력해주세요">
        <button class="button" @click="invite">초대</button>
      </label>
    </div>
  </div>
</template>

<script>
import HelloWorld from "@/components/HelloWorld";

export default {
  name: "PlInviteView",
  components:{
    HelloWorld
  },
  data() {
    return {
      arr:['ㅁ','ㄴ','ㅇ','ㄹ']
    }
  },
  methods:{
    invite() {
      console.log("axios 어떻게 쓰더라?? 개망ㅋㅋ")
    }
  }

}
</script>

<style scoped>
 .right {
   width:40%;
   height:90%;
   left:10%;
   float:left;
 }

 .left {
   width:40%;
   height:90%;
   right:10%;
   float:right;
 }

 .button {
   border:2px solid;
   border-radius: 12px;
   color:blue;
   padding: 10px 20px;
   display: inline-block;
   text-align: center;
   text-decoration: none;
   font-size: 18px;
 }
</style>