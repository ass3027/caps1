<template>
  <div>
    <HelloWorld/>
    <hr>
    <div>
      <p>planner make</p>
      <label>plan Name<input v-model="plan_name"></label>
      <button @click="addPlan">생성</button>
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
    }
  },
  methods: {
    addPlan() {
      axios({
        methods:'post',
        url:'/api/plan',
        data : this.plan_name
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