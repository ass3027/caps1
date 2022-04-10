<template>
  <v-app>

    <h1>배송조회</h1>
    <ul>
      <li v-for="(data,index) in list" :key="index">
        {{data}}
      </li>
    </ul>

</v-app>

</template>

<script>
import axios from "axios";

export default {
  name: "TrackingView",
  data(){
    return {
      list:[]
    }
  },
  mounted() {
    this.a()
  },
  methods:{
    a(){
      axios.get("/api/bag/"+this.$store.state.user.userId)
      .then((res)=>{
        this.list=res.data
      })
    }
  }

}
</script>

<style scoped>

</style>
