<template>
  <div>
    <v-container>
      asdf
      {{reverse}}
      {{now}}
      {{array}}
      {{array.i}}
      <v-btn @click="goDetail">inputArray</v-btn>
      <input v-model="message" type="text">
      <v-row>
        <v-col>
          {{ exchangeRate }}
        </v-col>
      </v-row>
      <v-row>
        <v-col
          v-for="(status,i) in dialogAdd"
          :key="i"
        >
          {{ i }}{{ status }}
          <v-btn @click="changeBoolean(i)">
            상태변경
          </v-btn>
        </v-col>
      </v-row>




        <v-btn
          color="red lighten-2"
          dark
          @click="dialog = true"
        >
          Click Me
        </v-btn>


      <v-dialog
        v-model="dialog"
        width="500"
      >

        <v-card>
          <v-card-title class="text-h5 grey lighten-2">
            Privacy Policy
          </v-card-title>

          <v-card-text>
            Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.
          </v-card-text>

          <v-divider></v-divider>

          <v-card-actions>
            <v-spacer></v-spacer>
            <v-btn
              color="primary"
              text
              @click="dialog = false"
            >
              I accept
            </v-btn>
          </v-card-actions>
        </v-card>
      </v-dialog>
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
      dialogAdd:[true,true],
      message:'hello world',
      array:['array'],
      dialog:false
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

    },
    goDetail(){
      this.$router.push({name:'bshDetail',params:{id:'123'},query:{bsh:this.message}})
    }
  }
}
</script>

<style scoped>

</style>
