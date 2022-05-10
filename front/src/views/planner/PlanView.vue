<template>
  <div>
    <PlannerHeader/>
    <hr>

    <p>planner make</p>
    <v-row>
      <v-col>
        plan Name
        <v-text-field
          placeholder="name"
          solo
          v-model="plan_name"
          style="width:200px"
        />
      </v-col>
    </v-row>
    <v-row justify="center" style="width:100%">
      <v-col>
        <v-date-picker
          style="width:50%;height:350px;"
          v-model="plan_start"
          type="date"

        />

      </v-col>
      <v-col>
        <v-date-picker
          style="width:50%;height:350px"
          v-model="plan_end"
          type="date"
        />
      </v-col>
    </v-row>

    <v-row>
      <v-btn
        @click="addPlan"
        @submit.prevent
        color="blue"
      >
        생성
      </v-btn>
    </v-row>

    <v-row>
      <hr>
      <ul>
        <li
          v-for="(plan,index) in plan_list"
          :key="index"
        >
          ID : {{ plan.plan_id }} / NAME : {{ plan.plan_name }}
          <v-btn
            @submit.prevent
            @click="deletePlan(plan.plan_id)"
            color="light-green"
          >
            삭제
          </v-btn>
        </li>
      </ul>

    </v-row>

  </div>
</template>

<script>

import axios from "axios";
import PlannerHeader from "@/components/PlannerHeader";

export default {
  name      : "PlanView",
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
  computed: {
    user_id() {
      return this.$store.state.user.userId
    }
  },
  mounted() {
    this.loadPlan()
  },
  methods: {
    addPlan() {
      axios({
        method: 'post',
        url   : '/api/planner/plan',
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
        url   : '/api/planner/plan/' + this.user_id,
      })
        .then((res) => {
          this.plan_list = res.data;
        })
    },
    deletePlan(plan_id) {

      if (confirm("delete?")) {
        axios({
          method: 'delete',
          url   : '/api/planner/plan',
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
