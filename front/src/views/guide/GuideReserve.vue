<template>
  <div>
    <canvas
      ref="barChart"
    />
    <v-btn @click="test2()"></v-btn>
  </div>
</template>

<script>
import { Chart, registerables } from 'chart.js'
import axios from "axios";
Chart.register(...registerables)

export default {

  data(){
    return{
      list:'',
      type: 'bar',
      data: {
        labels: [ 'Red', 'Blue', 'Yellow', 'Green', 'Purple', 'Orange' ],
        datasets: [{
          label: '# of Votes',
          data: [  3, 5, 2, 3 ],
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
  mounted(){
    this.selectRCount()
  },
  methods:{
    test2(){
      this.test.push(1)
    },
    createChart(){
      new Chart(this.$refs.barChart, {
        type:'bar',
        data:this.data,
        options:this.options
      })
    },
    selectRCount(){
      axios({
        method:'get',
        url:'/api/gcount',
        params:{
          'id':this.$store.state.user.userId
        }
      }).then((res)=>{
        console.log(res)
        // this.list = res.data.count;
        this.data.datasets[0].data.unshift(res.data.count)
        //this.data.datasets[1].data.unshift(res.data.count*2)
        this.createChart()
        console.log(this.data)
      })

    }
  }
}
</script>
