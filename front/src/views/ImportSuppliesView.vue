<template>
  <div>
    <h2>준비물 가져오기</h2>
    <SuppliesSet v-for="(set,index) in sets" :key="index">{{set.name}}</SuppliesSet>
  </div>
</template>

<script>
import SuppliesSet from "@/components/SuppliesSet";

export default {
  name: "ImportSuppliesView",
  components:{
    SuppliesSet
  },
  data(){
    return{
      sets:[{name:"기본"},{name:"1"},{name:"2"},{name:"3"},{name:"4"}],
    }
  }
}
</script>

<style scoped>

</style>