<template>
  <v-container style="padding-left: 15px;min-width: 1350px">
    <PlannerHeader />

    <v-row style="margin-bottom: 30px">
      <v-col>
        <h2>플래너 생성</h2>
        <v-divider />
      </v-col>
    </v-row>

    <v-row
      justify="center"
    >
      <v-col

      >
        <v-card>
          <v-card-title><h2>플래너 이름</h2></v-card-title>
          <v-card-text>
            <v-text-field
              v-model="plan_name"
              placeholder="name"
              solo
              style="width:200px;"
            />
            <h3>시작날짜 및 종료날짜</h3>
            <v-date-picker
              v-model="plan_range"
              style="width:50%;height:350px;"
              type="date"
              range
            />
            <div>
              <br>
              <br>
            </div>
          </v-card-text>
          <v-card-actions>
            <v-btn
              color="blue"
              @click="addPlan"
              @submit.prevent
            >
              생성
            </v-btn>
          </v-card-actions>
        </v-card>

      </v-col>
      <v-col>
        <v-card style="height: 100%">
          <v-card-title>
            <h2>현재 소속된 플래너 목록</h2>
          </v-card-title>
          <v-card-text>
            <v-simple-table>
              <thead>
              <tr>
                <!--              <th-->
                <!--                v-for="(key,index) in keys"-->
                <!--                :key="index"-->
                <!--                class="text-left"-->
                <!--              >-->
                <!--                {{ key }}-->
                <!--              </th>-->
                <th>플래너 번호</th>
                <th>플래너 이름</th>
                <th>생성자</th>
                <th>시작날짜</th>
                <th>종료날짜</th>
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
                    color="light-green"
                    @submit.prevent
                    @click="deletePlan(plan.plan_id)"
                  >
                    삭제
                  </v-btn>
                </td>
              </tr>
              </tbody>
            </v-simple-table>
          </v-card-text>
        </v-card>


      </v-col>
    </v-row>
    <div style="padding:40px" />
    <v-row />
    <v-row />
    <div style="padding:40px" />
  </v-container>
</template>

<script>

import axios from "axios";
import PlannerHeader from "@/components/planner/PlannerHeader";

export default {
  name: "PlanView",
  components: {
    PlannerHeader
  },
  data() {
    return {
      plan_name: '',
      plan_list: '',
      plan_range:[],
      keys:[]
    }
  },
  computed: {
    user_id() {
      return this.$store.state.user.userId
    },
    plan_start(){
      return this.plan_range[0]
    },
    plan_end(){
      return this.plan_range[1]
    },
  },
  mounted() {
    this.loadPlan()
  },
  methods: {
    addPlan() {
      if(this.plan_start>this.plan_end){
        const temp = this.plan_range[0]
        this.plan_range[0] = this.plan_range[1]
        this.plan_range[1] = temp
      }
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
  .v-sheet.v-card:not(.v-sheet--outlined){
    box-shadow: 0px 0px 20px 4px rgb(0 0 0 / 20%), 0px 2px 2px 0px rgb(0 0 0 / 14%), 0px 1px 5px 0px rgb(0 0 0 / 12%);
  }

  .v-input__slot{
    box-shadow: 0px 0px 20px 4px rgb(0 0 0 / 20%), 0px 2px 2px 0px rgb(0 0 0 / 14%), 0px 1px 5px 0px rgb(0 0 0 / 12%) !important;
  }
</style>
