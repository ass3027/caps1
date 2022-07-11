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
          label: '# of Votes',
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
      await this.selectRCount1()
      await this.selectRCount7()
      await this.selectRCount30()

      this.selectRCount()
    },

    createChart(){
      new Chart(this.$refs.barChart, {
        type:'bar',
        data:this.data,
        options:this.options
      })
    },

    selectRCount1(){
      axios({
        method:'get',
        url:'/api/gcount1',
        params:{
          'id':this.$store.state.user.userId
        }
      }).then((res1)=>{

        this.list1 = res1.data.count1;
        this.data.datasets[0].data[3]= res1.data.count1;

        //this.data.datasets[0].data.unshift(this.list1) // 금일
      })
    },

    selectRCount7(){
      axios({
        method:'get',
        url:'/api/gcount7',
        params:{
          'id':this.$store.state.user.userId
        }
      }).then((res7)=>{

        this.list7 = res7.data.count7;
        this.data.datasets[0].data[2]= res7.data.count7
        // this.data.datasets[0].data.unshift(this.list7) // 7일

      })
    },

    selectRCount30(){
      axios({
        method:'get',
        url:'/api/gcount30',
        params:{
          'id':this.$store.state.user.userId
        }
      }).then((res30)=>{

        this.list30 = res30.data.count30;
        this.data.datasets[0].data[1]= res30.data.count30

        // this.data.datasets[0].data.unshift(this.list30)// 30일

      })
    },

    selectRCount(){
      axios({
        method:'get',
        url:'/api/gcount',
        params:{
          'id':this.$store.state.user.userId
        },
      }).then((res)=>{
        this.list = res.data.count;

        this.data.datasets[0].data[0]= this.list
        this.data.datasets[0].data[3]= this.list1
          // this.data.datasets[0].data.unshift(this.list7) // 7일
          // this.data.datasets[0].data.unshift(this.list30)// 30일
          // this.data.datasets[0].data.unshift(this.list) //총
        this.createChart()


      })
    },


  },


}
</script>
