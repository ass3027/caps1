<template>
  <v-app>
    <h1>배송조회</h1>
    <ul>
      <li
        v-for="(data,index) in list"
        :key="index"
      >
        주문번호:{{ data.ord_id }}
        금액: {{data.order_amount}}
        출발키퍼: {{data.keep_start}}
        도착키퍼: {{data.keep_end}}
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
