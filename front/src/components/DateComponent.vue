<template>
  <div
    class="date"
  >
    <p>{{ date }}</p>
    <p>{{ location }}</p>

    <div
      v-for="(selected,index) in plan"
      :id="index"
      :class ="{dd:true,selecting:selectedTime==index}"
      :key="index"
      @click="select(index)"

    >
      <div>{{index}}시</div>
      <div> {{selected}} </div>
    </div>

  </div>
</template>

<script>
export default {
  name: "DateComponent",
  props: {
    date: {
      type: String,
      required: true
    },
    // location:{
    //   type:String,
    //   required:false
    // }
  },
  computed : {
    plan : function() {
      return this.$store.state.calendar.calendar.date[''+this.date]
    }
  },
  data() {
    return {
      height: 500,
      location: '',
      selectedTime: 30,
      selectedTime2: '',
      selectedArr: [],
      length: 0,
      selectedTag: '',
      style: "width:100,height:2,border:2,top:selected",
    }
  },
  methods: {
    setLocation(location) {
      this.location = location;
    },
    select(index) {
      //console.log(e.target.id);
      const a = {
        "date" : this.date,
        "time" : index
      }
      this.$store.commit('calendar/updateSelect',a)
      console.log(this.$store.state.calendar.selectTime)
      console.log(this.$store.state.calendar.selectDate)
      console.log(this.plan)
      this.selectedTime = index
    },

  }
}
</script>

<style scoped>
.date {
  width: 60%;
  /*height: 100%;*/
  border: 2px solid;
  margin-right: 15px;
  margin-bottom: 10px;

}
.dd {
  border-style:solid;
  height:8vh;
}

.selecting {
  color: #81ecec;
}
</style>
