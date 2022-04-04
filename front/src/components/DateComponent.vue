<template>
  <div class="date"
       v-on:click="select"
       @mousemove="timeSet"
  >
    <p>{{date}}</p>
    <p>{{location}}</p>
<!--    <ul>-->
<!--      <li v-for="(selected,index) in selectedArr"-->
<!--          :key="index"></li>-->
<!--    </ul>-->
    <div v-for="(selected,index) in selectedArr"
         :id="index"
         :key="index"
         :style="{backgroundColor:'aqua', top:selected+100+'px', position:'absolute'}"
    >{{parseInt(selected/height*24)}}시 {{parseInt(height/selected*14400%60)}}분</div>

  </div>
</template>

<script>
export default {
  name: "DateComponent",
  props:{
    date:{
      type:String,
      required:true
    },
    // location:{
    //   type:String,
    //   required:false
    // }
  },
  data() {
    return{
      height:500,
      location:'',
      selectedTime1:'',
      selectedTime2:'',
      selectedArr:[],
      length:0,
      selectedTag:'',
      style:"width:100,height:2,border:2,top:selected",
    }
  },
  methods : {
    setLocation(location) {
      this.location = location;
    },
    select(e) {
      //console.log(e.target.id);

      this.$emit('select',e.target);
      //선택된 태그를 눌렀을때
      if(e.target===this.selectedTag){
        if(this.selectedTime1===''){
          this.selectedTime1 = e.offsetY;
          this.selectedArr.push(e.offsetY);
        }else{
          this.selectedTime2 = e.offsetY;
          this.selectedArr.push(e.offsetY);
        }
      }
      else {
        this.selectedTag = e.target
      }
      console.log("ss")
      console.log(this.selectedArr)
    },
    timeSet() {
      //this.selectedArr[this.selectedArr.length]=e.offsetY;
      //console.log(e.offsetY);
    }
  }
}
</script>

<style scoped>
  .date{
    width:20%;
    height:100%;
    border: 2px solid;

  }
</style>