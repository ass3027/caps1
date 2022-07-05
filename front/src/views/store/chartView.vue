<template>
  <div>
    <canvas
      ref="barChart"
    />
  </div>
</template>

<script>
// import { Bar } from 'vue-chartjs/legacy'

import { Chart, registerables } from 'chart.js'
import axios from "axios";
Chart.register(...registerables)
let chart


export default {
  name:"chartView",

  props:{
    height:{
      type:String,
      required:true
    },
    firstData:{
      type:String,
      required:true
    },
    secondData:{
      type:String,
      required:true
    }
  },

  data:() => ({
    type: 'bar',
    data: {
      labels: [ 'Red', 'Blue', 'Yellow', 'Green', 'Purple', 'Orange' ],
      datasets: [{
        label: '# of Votes',
        data: [ '', '', '', '', '', '' ],
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
  mounted(){
    
    this.createChart()
  },
  methods:{
    createChart(){
      new Chart(this.$refs.barChart, {
        type:'bar',
        data:this.data,
        options:this.options
      })
      chart.data.datasets[0].data[0] = this.firstData
      chart.data.datasets[0].data[1] = this.secondData
      chart.update()
    }
  }
}
</script>
<style>

</style>
