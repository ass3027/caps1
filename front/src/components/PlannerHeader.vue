<template>
  <div style="width:100px;height:100px">
    <select
      v-model="plan_id"
      style="border-style:solid;width:100px;height:30px "
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
    <p v-if=" plan_id==='' " class="warningText">
      플래너를 선택해주세요!
    </p>
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
  computed: {
    user_id() {
      return this.$store.state.user.userId
    }
  },
  mounted() {

    this.getPlanListByUserId();
    this.plan_id = this.$store.state.user.planId
  },
  methods: {
    reload() {
      this.$store.commit('user/updatePlanId', this.plan_id)
      console.log(this.$store.state.user.planId)
      location.reload();
    },
    getPlanListByUserId() {
      axios({
        url: '/api/planner/' + this.user_id,
        method: 'get'
      })
        .then((res) => {
          console.log(res)
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
