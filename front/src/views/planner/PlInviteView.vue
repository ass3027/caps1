<template>
  <div>
    <PlannerHeader />
    <select
      v-model="plan_id"
      style="border-style:solid "
      @change="reload"
    >
      <option
        v-for="(plan,index) in plan_list"
        :key="index"
        :value="plan.plan_id"
      >
        {{ plan.plan_name }}
      </option>
    </select>
    <div class="float-right">
      <select v-model="selected_plan">
        <option />
      </select>
    </div>
    <div >
      <h2>현재 멤버</h2>
      <ul
        v-for="(data,key) in plan_user_list"
        :key="key"
      >
        <li>id : {{ data.user_id }} / plan_id : {{data.plan_id}}</li>
      </ul>
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
          @click="invite"
        >
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
import PlannerHeader from "@/components/PlannerHeader";

export default {
  name: "PlInviteView",
  components: {PlannerHeader},
  data() {
    return {
      plan_user_list  : ['ㅁ', 'ㄴ', 'ㅇ', 'ㄹ'],
      input_id         : '',
      plan_id         : 0,
      invite_list_user: [],
      invite_list_plan: [],
      selected_plan   : 0,
      plan_list:[]
    }
  },
  computed: {
    user_id() {
      return this.$store.state.user.userId
    },
  },
  mounted() {
    this.plan_id = this.$store.state.user.planId;
    console.log("mounted")
    this.updateInvite()
    this.loadAffiliated()
    this.getPlanListByUserId()


  },
  methods: {
    reload(){
      this.updateInvite()
      this.loadAffiliated()
    },
    loadAffiliated() {
      axios({
        url   : `/api/planner/affiliating?plan_id=${this.plan_id}`,
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
        url   : `/api/planner/invite`,
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
        url   : `/api/planner/inviteListPlan?id=${this.plan_id}`,
        method: 'get'
      })
        .then((res) => {
          this.invite_list_plan = res.data
        })
        .catch((err) => {
          console.log(err);
        });

      axios({
        url   : `/api/planner/inviteListUser?id=${this.id}`,
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
        url   : '/api/planner/affiliating',
        method: 'post',
        data  : data
      })
        .then(() => {
          alert("success")
          location.reload();
        })
        .catch((err) => {
          console.log(err);
        })
    },
    getPlanListByUserId() {
      axios({
        url:'/api/planner/plan/'+this.user_id,
        method:'get'
      })
      .then( (res)=>{
        this.plan_list=res.data
        this.plan_id=this.plan_list[0].plan_id
      })
      .catch( (err)=>{
        console.error(err)
      })
    }

  }

}
</script>

<style scoped>


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
