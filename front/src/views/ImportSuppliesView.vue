<template>
  <div>
    <h2>준비물 가져오기</h2>
    <SuppliesSet v-bind:seto="set" v-for="(set,index) in sets" :key="index"></SuppliesSet>
    <v-btn @click="$router.push({path:'/supplies'})">내준비물</v-btn>

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
      sets:[],
    }
  },
  mounted() {
    axios.get('/api/getSets')
    .then((res)=>{
      console.log(res.data);
      var outerArray = [[{pl_id:0,pl_name:"기본",supl_id:"1",supl_name:"수건"},{pl_id:0,pl_name:"기본",supl_id:"3",supl_name:"여권"}]];
      var innerArray = [];
      var id = res.data[0].pl_id;
      res.data.forEach((i)=>{

        if(i.pl_id!=id) {

          outerArray.push(innerArray);
          innerArray = [];
          id = i.pl_id;
        }
        innerArray.push(i);

      })
      outerArray.push(innerArray);
      console.log("outerArray")
      console.log(outerArray);
      this.sets=outerArray;
      console.log("this.sets")
      console.log(this.sets)
    })
  }
}
</script>

<style scoped>

</style>