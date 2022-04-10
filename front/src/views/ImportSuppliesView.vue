<template>
  <div>
    <h2>준비물 가져오기</h2>
    <v-btn @click="$router.push({path:'/supplies'})">
      내준비물
    </v-btn>
    <SuppliesSet
      v-for="(set,index) in sets"
      :key="index"
      :seto="set"
    />
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
    axios.get('/api/getSets/'+this.$store.state.user.planId)
      .then((res)=>{
        console.log("여기")
        console.log(res.data);
        var outerArray = [
          [
            {pl_id:0,pl_name:"기본",supl_id:"2",supl_name:"휴대폰 충전기"},
            {pl_id:0,pl_name:"기본",supl_id:"58",supl_name:"물"},
            {pl_id:0,pl_name:"기본",supl_id:"60",supl_name:"마스크"},
            {pl_id:0,pl_name:"기본",supl_id:"43",supl_name:"스킨/로션"},
            {pl_id:0,pl_name:"기본",supl_id:"23",supl_name:"물티슈"},
            {pl_id:0,pl_name:"기본",supl_id:"14",supl_name:"신용카드"},
            {pl_id:0,pl_name:"기본",supl_id:"14",supl_name:"신용카드"},
            {pl_id:0,pl_name:"기본",supl_id:"15",supl_name:"보조 배터리"},
            {pl_id:0,pl_name:"기본",supl_id:"4",supl_name:"선크림"},
          ]
        ];
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
