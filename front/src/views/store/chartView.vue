<template>
  <div style="position: relative; max-width: 1040px">
    <div>
      <v-select
        :items="items"
        item-text="pd_name"
        item-value="pd_id"
        v-model="value"
        @change="change_pd(value)"
      >
      </v-select>
      {{ value }}
    </div>
    <canvas
      ref="barChart"
      id="chart"
    />
  </div>
</template>

<script>
// import { Bar } from 'vue-chartjs/legacy'

import {Chart, registerables} from "chart.js";

Chart.register(...registerables)
import axios from "axios";

export default {
  name: "chartView",

  data: () => ({
    selectedValue: null,
    listss: '',
    listss1: '',
    listss7: '',
    listss30: '',

    list: '',
    list1: '',
    list7: '',
    list30: '',
    type: 'bar',
    data: {
      labels: ['총 매출', '최근 한달', '최근 7일', '금일'],
      datasets: [{
        label: '매출',
        data: ['', '', '', ''],
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
      }],
    },
    options: {
      scales: {
        y: {
          beginAtZero: true
        }
      }
    },
    items: [],
    value: [],
  }),
  created() {
    this.a()

    axios({
      method: 'GET',
      url: '/api/productChart',
      params: {
        'user_id': this.$store.state.user.userId
      }
    })
      .then((res) => {
        console.log(res.data)

        this.items = res.data
      })
  },

  methods: {
    async a() {
      await this.productSales1()
      await this.productSales7()
      await this.productSales30()

      this.productSales()
    },


    createChart() {
      new Chart(this.$refs.barChart, {
        type: 'bar',
        data: this.data,
        options: this.options
      })
    },

    change_pd(value) {
      axios({
        method: 'GET',
        url: '/api/productChartss',
        params: {
          'user_id': this.$store.state.user.userId,
          'value': this.value
        }
      })
        .then((res) => {

          this.list = res.data.count;

          this.data.datasets[0].data[0] = this.listss
          this.data.datasets[0].data[3] = this.listss1
          // this.data.datasets[0].data.unshift(this.list7) // 7일
          // this.data.datasets[0].data.unshift(this.list30)// 30일
          // this.data.datasets[0].data.unshift(this.list) //총
          this.createChart()

        })
    },


    productSales() {
      axios({
        method: 'GET',
        url: '/api/chart',
        params: {
          'id': this.$store.state.user.userId
        }
      })
        .then((res) => {

          this.list = res.data.count;

          this.data.datasets[0].data[0] = this.list
          this.data.datasets[0].data[3] = this.list1
          // this.data.datasets[0].data.unshift(this.list7) // 7일
          // this.data.datasets[0].data.unshift(this.list30)// 30일
          // this.data.datasets[0].data.unshift(this.list) //총
          this.createChart()

        })
    },

    productSales1() {
      axios({
        method: 'GET',
        url: '/api/chart1',
        params: {
          'id': this.$store.state.user.userId
        }
      })
        .then((res1) => {
          this.list = res1.data.count1;

          this.list1 = res1.data.count1;
          this.data.datasets[0].data[3] = res1.data.count1;
          // this.data.datasets[0].data.unshift(this.list7) // 7일
          // this.data.datasets[0].data.unshift(this.list30)// 30일
          // this.data.datasets[0].data.unshift(this.list) //총
        })
    },

    productSales7() {
      axios({
        method: 'GET',
        url: '/api/chart7',
        params: {
          'id': this.$store.state.user.userId
        }
      }).then((res7) => {

        this.list7 = res7.data.count7;
        this.data.datasets[0].data[2] = res7.data.count7
        // this.data.datasets[0].data.unshift(this.list7) // 7일

      })
    },

    productSales30() {
      axios({
        method: 'GET',
        url: '/api/chart30',
        params: {
          'id': this.$store.state.user.userId
        }
      }).then((res30) => {
        this.list30 = res30.data.count30;
        this.data.datasets[0].data[1] = res30.data.count30
        // this.data.datasets[0].data.unshift(this.list7) // 7일
        console.log(res30)

      })
    },

    // 객실별 매출 조회 기능 구현하면 여기에 구현 하면 된다.
  }
}
</script>
<style>

</style>
