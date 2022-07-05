<template>
  <div>
    <v-container>
      details
      {{ $route.params }}
      id:{{ id }}
      id2:{{ id2 }}
      abc:{{ abc }}
    </v-container>
  </div>
</template>

<script>
import axios from 'axios'

export default {
  name: "TestBshDetailView",
  props:['id','id2','abc'],
  data() {
    return {
      exchangeRate:'bsh',
      dialogAdd:[true,true],
      message:'hello world',
      array:['array']
    }
  },
  computed:{
    reverse: function() {
      console.log('I am computed :: reverse')
      var a = this.message.split('').reverse().join('')
      return a
    },
    now: function() { // 아무곳에도 의존하지 않기때문에 절대로 업데이트 되지 않는다.
      console.log('I am computed :: now')
      return Date.now()
    }

  },
  mounted() {
    var a = `/api/${this.exchangeRate}/this.message`
    alert(a)
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
    },
    inputArray(){
      this.array['i']='i'
      // this.array.push('asdf')
      // this.$set(this.array,0,this.array[0])
      this.$nextTick(function (){
        console.log(this.array)
      })

    }
  }
}
</script>

<style scoped>

</style>
