<template>
  <div class="date" v-on:click="select">
    <p>{{date}}</p>
    <p>{{location}}</p>
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
      location:''
    }
  },
  methods : {
    setLocation(location) {
      this.location = location;
    },
    select(e) {
      //console.log(e.target.id);
      this.$emit('select',e.target);
    },
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