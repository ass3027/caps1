<template>
  <div>
    <h2>게시판 리스트</h2>
    <a href="javascript:;" @click="getList">GET 방식 데이터 접근</a>
    <h2>{{accessStatus}}</h2>
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
              console.log(res);
              this.accessStatus=true;
          })
    }
  }
}

</script>

<style scoped>

</style>