<template>
  <div>
    <PlannerHeader/>
    <hr>
    <div>
      <p>planner make</p>
      <div>
        <label>plan Name<input v-model="plan_name"></label>
        <input
          v-model="plan_start"
          type="date"
        >
        <input
          v-model="plan_end"
          type="date"
        >
      </div>
      <button
        @click="addPlan"
        @submit.prevent
      >
        생성
      </button>
      <hr>

      <ul>
        <li
          v-for="(plan,index) in plan_list"
          :key="index"
        >
          ID : {{ plan.plan_id }} / NAME : {{ plan.plan_name }}
          <button
            @submit.prevent
            @click="deletePlan(plan.plan_id)"
          >
            삭제
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
  name      : "PlanView.vue",
  components: {
    PlannerHeader
  },
  data() {
    return {
      plan_name : '',
      plan_start: '',
      plan_end  : '',
      plan_list : ''
    }
  },
  mounted() {
    this.loadPlan()
  },
  computed:{
    user_id() {
      return this.$store.state.user.userId
    }
  },
  methods: {
    addPlan() {
      axios({
        method: 'post',
        url   : '/api/plan',
        data  : {
          plan_id   : '',
          plan_name : this.plan_name,
          plan_start: this.plan_start,
          plan_end  : this.plan_end,
          user_id   : this.user_id
        }
      })
        .then((res) => {
          if (res.data) {
            alert("success")
            this.loadPlan()
          }
        })
    },
    loadPlan() {
      axios({
        method: 'get',
        url   : '/api/plan/'+this.user_id,
      })
        .then((res) => {
          this.plan_list = res.data;
        })
    },
    deletePlan(plan_id) {

      if (confirm("delete?")) {
        axios({
          method: 'delete',
          url   : '/api/plan',
          data  : plan_id
        })
          .then(() => {

            console.log("success")
            this.loadPlan();


          })
      }
    }
  }
}
</script>

<style scoped>

</style>
