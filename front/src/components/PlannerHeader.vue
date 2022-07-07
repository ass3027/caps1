<template>

  <div style="width:100px;min-width: 1350px;margin-bottom: 20px ">
    <v-divider />
    <div style="margin-top: 30px">
      <h2>일정선택</h2>
      <select
        v-model="plan_id"
        style="border-style:solid;width:150px;height:45px;font-size: 24px; "
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
      <p
        v-if=" plan_id==='' "
        class="warningText"
      >
        플래너를 선택해주세요!
      </p>
    </div>
    <!--    <v-select-->
    <!--      class="select-size"-->
    <!--      :item="plan_list"-->
    <!--      v-model="plan_id"-->
    <!--      @change="reload"-->
    <!--      >-->

    <!--    </v-select>-->
  </div>
</template>

<script>
import axios from "axios";

export default {
  name: "PlannerHeader",
  data() {
    return {
      plan_id: 1,
      plan_list: []
    }
  },
  mounted() {
    this.getPlanListByUserId();
    this.plan_id = this.$store.state.user.planId
  },
  methods: {
    getUserId() {
      return this.$store.state.user.userId
    },
    reload() {
      this.$store.commit('user/updatePlanId', this.plan_id)
      location.reload();
    },
    getPlanListByUserId() {
      axios({
        url: '/api/planner/' + this.getUserId(),
        method: 'get'
      })
        .then((res) => {
          if (res.data.length === 0) {
            this.plan_list = [{
              "plan_name": "없음"
            }]

          } else {

            this.plan_list = res.data

          }
          if (this.$store.state.user.planId === '') {
            this.plan_id = this.plan_list[1].plan_id
          }
        })
        .catch((err) => {
          console.error(err)
        })
    }

  }
}
</script>

<style scoped>
.warningText{
  color: red;
}
</style>
