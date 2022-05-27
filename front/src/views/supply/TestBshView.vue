<template>
  <div>
    <v-container>
      <v-row>
        <v-col>
          {{exchangeRate}}
        </v-col>
      </v-row>
      <v-row>
        <v-col v-for="(status,i) in dialogAdd" :key="i">
          {{i}}{{status}}
          <v-btn @click="changeBoolean(i)">
            상태변경
          </v-btn>
        </v-col>
      </v-row>
    </v-container>

  </div>
</template>

<script>
import axios from 'axios'

export default {
  name: "TestBshView",
  data() {
    return {
      exchangeRate:'bsh',
      dialogAdd:[true,true]
    }
  },
  mounted() {
    axios.get("https://api.manana.kr/exchange/price/KRW/1/KRW,JPY.json")
    .then((res)=>{
      this.exchangeRate=res.data
    })
    this.dialogAdd.push(true)
    this.dialogAdd.push(true)
    this.dialogAdd.push(true)
    console.log(this.dialogAdd)
  },
  methods: {
    changeBoolean(i){
      this.dialogAdd[i]=!this.dialogAdd[i];
      console.log(this.dialogAdd)
    }
  }
}
</script>

<style scoped>

</style>
