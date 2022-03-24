<template>
  <div>
    <HelloWorld/>
    <hr>
    <div>
      <p>planner make</p>
      <div>
        <label>plan Name<input v-model="plan_name"></label>
        <input type="date" v-model="plan_start">
        <input type="date" v-model="plan_end">
      </div>


      <button @click="addPlan" @submit.prevent>생성</button>
    </div>
  </div>
</template>

<script>
import HelloWorld from "@/components/HelloWorld";
import axios from "axios";

export default {
  name: "PlanView.vue",
  components:{
    HelloWorld
  },
  data (){
    return {
      plan_name:'',
      plan_start:'',
      plan_end:'',
    }
  },
  methods: {
    addPlan() {
      axios({
        method:'post',
        url:'/api/plan',
        data : {
          plan_id:'',
          plan_name:this.plan_name,
          plan_start:this.plan_start,
          plan_end:this.plan_end,
          user_id:'um'
        }
      })
      .then( (res) => {
        if(res.data) alert("success")
      })
    }
  }
}
</script>

<style scoped>

</style>