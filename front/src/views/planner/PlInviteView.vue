<template>
  <div>
    <HelloWorld/>
    <div class="right">
      <h2>현재 멤버</h2>
      <div v-for="(data,key) in arr" :key="key">
        <hr>
        <p>{{ data }}</p>
      </div>
    </div>
    <div class="left">
      <h2>멤버 초대</h2>
      <hr>
      <label>
        회원ID <input v-model="inputId" placeholder="내용을 입력해주세요">
        <button class="button" @click="invite">초대</button>
      </label>
    </div>
    <hr>
    <div>
      초대 현황
      <hr>

      내가 초대한
      <ul>
        <li v-for="(data,index) in this.invite_list_plan" :key="index">{{ data }}</li>
      </ul>

      내가 초대된
      <ul>
        <li v-for="(data,index) in this.invite_list_user" :key="index">
          {{ data }}
          <button @click="accept(data)" class="button">수락</button>
        </li>
      </ul>

    </div>
  </div>
</template>

<script>
import HelloWorld from "@/components/HelloWorld";
import axios from "axios";

export default {
  name      : "PlInviteView",
  components: {
    HelloWorld
  },
  data() {
    return {
      arr             : ['ㅁ', 'ㄴ', 'ㅇ', 'ㄹ'],
      inputId         : '',
      id              : 'um',
      plan_id         : 2,
      current_user_id : 'um',
      invite_list_user: [],
      invite_list_plan: [],
    }
  },
  mounted() {
    this.updateInvite()
  },
  methods: {
    invite() {
      console.log("axios 어떻게 쓰더라?? 개망ㅋㅋ")
      axios({
        url   : '/api/invite',
        method: 'post',
        data  : {
          user_id: this.inputId,
          plan_id: this.plan_id,
        }
      })
          .then((res) => {
            console.log(res)
            if (res.data) {
              this.updateInvite()
              console.log('success')
            } else console.log('fail ')
          })
    },
    updateInvite() {
      axios({
        url   : `/api/inviteListPlan?id=${this.plan_id}`,
        method: 'get'
      })
          .then((res) => {
            this.invite_list_plan = res.data
          })
          .catch((err) => {
            console.log(err);
          });

      axios({
        url   : `/api/inviteListUser?id=${this.id}`,
        method: 'get'
      })
          .then((res) => {
            this.invite_list_user = res.data
          })
          .catch((err) => {
            console.log(err);
          })
    },
    accept(data) {
      axios({
        url   : '/api/affiliated',
        method: 'post',
        data  : data
      })
          .then(() => {
            alert("success")
          })
          .catch((err) => {
            console.log(err);
          })
    }
  }

}
</script>

<style scoped>
.right {
  width: 40%;
  height: 90%;
  left: 10%;
  float: left;
}

.left {
  width: 40%;
  height: 90%;
  right: 10%;
  float: right;
}

.button {
  border: 2px solid;
  border-radius: 12px;
  color: blue;
  padding: 10px 20px;
  display: inline-block;
  text-align: center;
  text-decoration: none;
  font-size: 18px;
}
</style>