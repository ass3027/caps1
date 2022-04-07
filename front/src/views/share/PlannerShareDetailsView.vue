<template>
  <div>
    <h2>공유 플래너 상세보기</h2>

  </div>
</template>

<script>
import axios from 'axios';

export default {
  name: "PlannerShareDetailsView",
  components:{
  },
  data(){
    return{
      tableSets:[],

    }
  },
  mounted() {
    alert(this.$route.params.id)
    axios.get('/api/getSharePostDetails')
    .then((res)=>{
      this.tableSets=res.data;
      console.log(res.data);
    })
  }
}
</script>

<style scoped>

</style>