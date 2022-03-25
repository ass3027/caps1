<template>
  <div>
    <h2>준비물 가져오기</h2>
    <SuppliesSet v-bind:sets="set" v-for="(set,index) in sets" :key="index">{{set.name}}asdf</SuppliesSet>

  </div>
</template>

<script>
import SuppliesSet from "@/components/SuppliesSet";
import axios from 'axios';

export default {
  name: "ImportSuppliesView",
  components:{
    SuppliesSet
  },
  data(){
    return{
      sets:[{name:"기본"}],
    }
  },
  mounted() {
    axios.get('/api/getSets')
    .then((res)=>{
      console.log(res);
      res.data.forEach((i)=>{

      })
    })
  }
}
</script>

<style scoped>

</style>