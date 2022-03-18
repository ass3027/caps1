<template>
  <div class="date"
       v-on:click="select"
       @mousemove="timeSet"
  >
    <p>{{date}}</p>
    <p>{{location}}</p>
    <div v-for="(selected,index) in selectedArr"
         :id="index"
         :key="index"
         :style="{width:100,height:2,border:2,top:selected,position: absolute}"
    ></div>
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
      location:'',
      selectedTime1:'',
      selectedTime2:'',
      selectedArr:[30],
      length:0,
    }
  },
  methods : {
    setLocation(location) {
      this.location = location;
    },
    select(e) {
      //console.log(e.target.id);
      //선택된 태그를 눌렀을때
      var sameChecking = this.$emit('select',e.target);
      if(!sameChecking){
        if(this.selectedTime1==''){
          this.selectedTime1 = e.offsetY;
          this.selectedArr[this.selectedArr.length] = e.offsetY;
        }else{
          this.selectedTime2 = e.offsetY;
          this.selectedArr[this.selectedArr.length] = e.offsetY;
        }
      }
      console.log("ss")
      console.log(sameChecking);
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
    width:100px;
    height:500px;
    border: 2px solid;

  }
</style>