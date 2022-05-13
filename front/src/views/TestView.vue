<template>
  <div>
    <h2>게시판 리스트</h2>
    <a
      href="javascript:;"
      @click="getList"
    >GET 방식 데이터 접근</a>
    <h2>{{ accessStatus }}</h2>
  </div>
</template>

<script>

import axios from 'axios'

export default {
  name: 'TestView',
  data() {
    return {
      accessStatus:false,
    }
  },
  mounted() {
    this.getLocation()
    this.currentPosition = setInterval(this.getLocation, 500)
  },
  methods: {
    getList: function () {

      console.log("hi")
      axios({
        method : 'get',
        url    : '/api/test',
        headers: {
          'Content-Type': 'application/json',
        },
        data   : '',
      })
        .then((res) => {
          res.data.forEach(function (i){
            console.log(i.supl_id.supl_name)
          })
        })
    }
  }
}

</script>

<style scoped>

</style>
