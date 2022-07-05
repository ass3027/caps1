<template>
<div>
  <canvas
    ref="barChart"
    :height="height"
   />
</div>

</template>

<script>
import { Chart, registerables } from 'chart.js'
import axios from "axios";
Chart.register(...registerables)
let chart

export default {
  props:{
    height:{
      type: String,
      required:true
    }
  },
  firstData:{
    type:String,
    required:true
  },
  secondData:{
    type:String,
    required:true
  },
  data:() => ({
    type: 'bar',
    data: {
      labels: [ '10대', '20대', '30대', '40대', '50대', '60대 이상' ],
      datasets: [{
        label: '연령별 가입 기간별 추이',
        data: ['1', '10'],
        backgroundColor: [
          'rgba(255, 99, 132, 0.2)',
          'rgba(54, 162, 235, 0.2)',
          'rgba(255, 206, 86, 0.2)',
          'rgba(75, 192, 192, 0.2)',
          'rgba(153, 102, 255, 0.2)',
          'rgba(255, 159, 64, 0.2)'
        ],
        borderColor: [
          'rgba(255, 99, 132, 1)',
          'rgba(54, 162, 235, 1)',
          'rgba(255, 206, 86, 1)',
          'rgba(75, 192, 192, 1)',
          'rgba(153, 102, 255, 1)',
          'rgba(255, 159, 64, 1)'
        ],
        borderWidth: 1
      }]
    },
    options: {
      scales: {
        y: {
          beginAtZero: true
        }
      }
    }
  }),
  watch:{
    firstData(){
      this.createChart()
    },
    secondData(){
      this.createChart()
    },
    deep:true
  },
  mounted(){
    this.createChart()
  },
  methods:{
    createChart(){
      axios.get("/api/area")
      if (chart !== undefined){
        chart.destroy()
      }
      chart =  new Chart(this.$refs.barChart, {
        type:'bar',
        data:this.chartData,
        options:this.options
      })
      chart.data.datasets[0].data[0] = this.firstData
      chart.data.datasets[0].data[1] = this.secondData
      chart.update()
      new Chart(this.$refs.barChart, {
        type:'bar',
        data:this.data,
        options:this.options
      })
    }
  }
}
</script>

<style scoped>

</style>
