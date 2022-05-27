<template>
  <div>
    <p v-if="data1">
      넌 나가라
    </p>
    <v-btn @click="hidden">
      숨기기
    </v-btn>

    <v-btn @click="disable">
      비활성화
    </v-btn>
    <v-btn :disabled="data2" />
    <div />
  </div>
</template>

<script>
export default {
  name: "MINA",
  data(){
    return {
      data1:true,
      data2:false
    }
  },
  methods:{
    hidden(){
      this.data1 = this.data1 !== true;
    },
    disable(){
      if(this.data2===true) this.data2 = false
      else this.data2 = true
    }
  }
}
</script>

<style scoped>

</style>
