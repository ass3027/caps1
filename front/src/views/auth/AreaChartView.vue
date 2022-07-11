<template>
  <div style="position: relative;width: 80%">
    <canvas
      ref="barChart"
    />
    <!--    <div>{{data.datasets[0]}}</div>-->
  </div>
</template>

<script>
import { Chart, registerables } from 'chart.js'
import axios from "axios";
Chart.register(...registerables)

export default {

  data(){
    return{
      area_list:['서울', '경기도'], //지역
      list1:'',
      list:'',
      list7:'',
      list30:'',
      type: 'bar',
      data: {
        labels: [],
        datasets: [{
          label: '# of Area',
          data: ['','','','','','','','','','','','','','','','',''],
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
    a() {
      // this.selectArea()
      this.selectArea1()
      // await this.selectArea2()
      // await this.selectArea3()
      // await this.selectArea4()
      // this.selectArea5()

      //진환이 오빠꺼
      // await this.selectRCount1()
      // await this.selectRCount7()
      // await this.selectRCount30()
      // await this.selectArea1() //서울
      // this.selectRCount()
      // this.selectArea()
    },
    createChart(){
      new Chart(this.$refs.barChart, {
        type:'bar',
        data:this.data,
        options:this.options
      })
    },
    selectArea(){
      axios({
        method:'get',
        url:'/api/user/area1',
      }).then((res10)=>{
        // console.log(res10.data)
        // this.area_list = res10.data[0]
        // this.area_list = res10.data[1]

        // this.data.datasets[0].data[0]= res10.data[0]
        // this.data.datasets[0].data[1]= res10.data[1]
        // this.data.datasets[0].data[2]= res10.data[2]
        // this.data.datasets[0].data[3]= res10.data[3]
        // this.data.datasets[0].data[4]= res10.data[4]
        // this.data.datasets[0].data[5]= res10.data[5]
        this.data.datasets[0].data= res10.data
        this.createChart()
      })
    },
    selectArea1(){
      axios({
        method:'get',
        url:'/api/user/area',
      }).then((res20)=>{
        // const tempArr = []
        console.log(res20.data)
        res20.data.forEach((it, index)=>{ //하나씩 빼와서 it 이라는 변수에 넣고 그 다음작업을 한다? //it은 아무변수나 만들어줘도 된다!
          // tempArr.push(it.count)
          this.data.datasets[0].data[index] = it.count
          this.data.labels[index] = it.user_area
          console.log(it.user_area)
        })
        // console.log(tempArr)
        // console.log(Object.keys(res20.data[0]))
        // this.data.datasets[0].data= tempArr
        // this.data.datasets[0].data[0]= res20.data.count;
        this.createChart()
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
        //this.data.datasets[0].data[3]= res1.data.count1;

        this.data.datasets[0].data.unshift(this.list1) // 금일
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
        this.data.datasets[0].data.unshift(this.list7) // 7일

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

        this.data.datasets[0].data.unshift(this.list30)// 30일

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

      })
    },


  },


}
</script>
