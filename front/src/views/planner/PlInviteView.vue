<template>
  <div>
    <select>
      <option v-for="(plan,index) in plan_list" :key="index">{{ plan.plan_name }}</option>
    </select>
    <div class="float-right">
      <select v-model="selected_plan">
        <option/>
      </select>
    </div>
    <div class="float-left">
      <h2>현재 멤버</h2>
      <div
        v-for="(data,key) in plan_user_list"
        :key="key">

        <p>{{ data }}</p>
      </div>
    </div>
    <div class="float-right">
      <h2>멤버 초대</h2>
      <hr>
      <label>
        회원ID <input
        v-model="input_id"
        placeholder="내용을 입력해주세요"
      >
        <button
          class="button"
          @click="invite">
          초대
        </button>
      </label>
    </div>
    <hr>
    <div>
      초대 현황
      <hr>

      내가 초대한
      <ul>
        <li
          v-for="(data,index) in invite_list_plan"
          :key="index"
        >
          {{ data.user_id }} / {{ data.plan_id }}
        </li>
      </ul>
      <hr>
      내가 초대된
      <ul>
        <li
          v-for="(data,index) in invite_list_user"
          :key="index"
        >
          {{ data.user_id }} / {{ data.plan_id }}
          <button
            class="button"
            @click="accept(data)"
          >
            수락
          </button>
        </li>
      </ul>
    </div>
  </div>
</template>

<script>
import axios from "axios";

export default {
  name: "PlInviteView",
  data() {
    return {
      plan_user_list  : ['ㅁ', 'ㄴ', 'ㅇ', 'ㄹ'],
      input_id         : '',
      plan_id         : 2,
      current_user_id : 'um',
      invite_list_user: [],
      invite_list_plan: [],
      selected_plan   : 0,
      plan_list:[]
    }
  },
  computed: {
    user_id() {
      return this.$store.state.user.userId
    }
  },
  mounted() {
    this.updateInvite()
    this.loadAffiliated()
    this.getPlanListByUserId()
  },
  methods: {
    loadAffiliated() {
      axios({
        url   : `/api/affiliating?plan_id=${this.plan_id}`,
        method: 'get'
      })
        .then((res) => {
          this.plan_user_list = res.data;
        })
        .catch((err) => {
          console.log(err);
        })
    },
    invite() {
      console.log("axios 어떻게 쓰더라?? 개망ㅋㅋ")
      axios({
        url   : `/api/invite`,
        method: 'post',
        data  : {
          user_id: this.input_id,
          plan_id: this.plan_id,
        }
      })
        .then((res) => {
          if (res.data === true) {
            this.updateInvite()
            console.log('success')
            alert('success')
          } else {
            console.log('fail ')
            alert('fail')
          }

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
        url   : '/api/affiliating',
        method: 'post',
        data  : data
      })
        .then(() => {
          alert("success")
        })
        .catch((err) => {
          console.log(err);
        })
    },
    getPlanListByUserId() {
      axios({
        url:'/api/plan/'+this.user_id,
        method:'get'
      })
      .then( (res)=>{
        this.plan_list=res.data
        console.log(this.plan_list)
      })
      .catch( (err)=>{
        console.error(err)
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
  float: right;
}

.left {
  width: 40%;
  height: 90%;
  right: 10%;
  float: left;
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
