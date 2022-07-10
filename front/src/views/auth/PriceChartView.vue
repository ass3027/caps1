<template>
  <div style="position: relative">
    <canvas
      ref="barChart"
    />
  </div>
</template>

<script>
import { Chart, registerables } from 'chart.js'
import axios from "axios";
Chart.register(...registerables)

export default {

  data(){
    return{
      list1:'',
      list:'',
      list7:'',
      list30:'',
      type: 'bar',
      data: {
        labels: [ '총매출', '최근한달', '최근 7일', '금일'],
        datasets: [{
          label: '# of price',
          data: ['','','',''],
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
    }
  },
  created(){
    this.a()
  },
  methods:{
    async a() {
      const { data } = await axios.get("/api/user/price")

      console.log(data)
      this.data.datasets[0].data = data

      this.selectRCount()
    },

    createChart(){
      new Chart(this.$refs.barChart, {
        type:'bar',
        data:this.data,
        options:this.options
      })
    },

  },


}
</script>
