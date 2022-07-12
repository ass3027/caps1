<template>
  <div style="position: relative ; max-width:1040px">
    <canvas
      ref="barChart"
    />
    <!--        <div>{{data.datasets[0]}}</div>-->
  </div>
</template>

<script>
import { Chart, registerables } from 'chart.js'
import axios from "axios";
Chart.register(...registerables)

export default {

  data(){
    return{
      type: 'bar',
      data: {
        labels: ['10대','20대','30대','40대','50대 이상'],
        datasets: [{
          label: '# of age',
          data: ['','','','',''],
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
  async created(){
    await this.a()
  },
  methods:{
    async a() {
      await this.selectAge()


      // this.selectArea1()

    },
    createChart(){
      new Chart(this.$refs.barChart, {
        type:'bar',
        data:this.data,
        options:this.options
      })
    },
    async selectAge(){
      const res10 = await axios.get('/api/user/age')
      this.data.datasets[0].data[0] = res10.data.user_birth10

      const res20 = await axios.get('/api/user/age20')
      this.data.datasets[0].data[1] = res20.data.user_birth20

      const res30 = await axios.get('/api/user/age30')
      this.data.datasets[0].data[2] = res30.data.user_birth30

      const res40 = await axios.get('/api/user/age40')
      this.data.datasets[0].data[3] = res40.data.user_birth40

      const res50 = await axios.get('/api/user/age50')
      this.data.datasets[0].data[4] = res50.data.user_birth50

      this.createChart()

    },

  },
}
</script>
