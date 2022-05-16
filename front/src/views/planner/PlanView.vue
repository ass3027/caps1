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
    <div style="padding:40px"></div>
    <v-row>

      <!--      <ul>-->
      <!--        <li-->
      <!--          v-for="(plan,index) in plan_list"-->
      <!--          :key="index"-->
      <!--        >-->
      <!--          ID : {{ plan.plan_id }} / NAME : {{ plan.plan_name }}-->
      <!--          <v-btn-->
      <!--            @submit.prevent-->
      <!--            @click="deletePlan(plan.plan_id)"-->
      <!--            color="light-green"-->
      <!--          >-->
      <!--            삭제-->
      <!--          </v-btn>-->
      <!--        </li>-->
      <!--      </ul>-->
      <v-simple-table dark>
        <thead>
          <tr>
            <th
              v-for="(key,index) in keys"
              :key="index"
              class="text-left">
              {{ key }}
            </th>
            <th>삭제</th>
          </tr>
        </thead>
        <tbody>
          <tr
            v-for="(plan,index) in plan_list"
            :key="index"
          >
            <td>
              {{ plan.plan_id }}
            </td>
            <td>{{ plan.plan_name }}</td>
            <td>{{ plan.user_id }}</td>
            <td>{{ plan.plan_start }}</td>
            <td>{{ plan.plan_end }}</td>
            <td>
              <v-btn
                @submit.prevent
                @click="deletePlan(plan.plan_id)"
                color="light-green"
              >
                삭제
              </v-btn>
            </td>
          </tr>
        </tbody>

      </v-simple-table>

    </v-row>
    <div style="padding:40px"></div>

  </div>
</template>

<script>

import axios from "axios";
import PlannerHeader from "@/components/PlannerHeader";

export default {
  name: "PlanView",
  components: {
    PlannerHeader
  },
  data() {
    return {
      plan_name: '',
      plan_start: '',
      plan_end: '',
      plan_list: ''
    }
  },
  computed: {
    user_id() {
      return this.$store.state.user.userId
    },
  },
  mounted() {
    this.loadPlan()
  },
  methods: {
    addPlan() {
      axios({
        method: 'post',
        url: '/api/planner/',
        data: {
          plan_id: '',
          plan_name: this.plan_name,
          plan_start: this.plan_start,
          plan_end: this.plan_end,
          user_id: this.user_id,
          keys:[]
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
        url: '/api/planner/' + this.user_id,
      })
        .then((res) => {
          this.plan_list = res.data;
          this.keys = Object.keys(this.plan_list[0])
        })
    },
    deletePlan(plan_id) {

      if (confirm("delete?")) {
        axios({
          method: 'delete',
          url: '/api/planner/',
          data: plan_id
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
