<template>
  <div>
<<<<<<< Updated upstream
    <h2>게시판 리스트</h2>
    <a
      href="javascript:;"
      @click="getList"
    >GET 방식 데이터 접근</a>
    <h2>{{ accessStatus }}</h2>
=======
    {{ info }}
    aaa
>>>>>>> Stashed changes
  </div>
</template>

<script>

import axios from 'axios'

export default {
  name: 'TestView',
  data() {
    return {
<<<<<<< Updated upstream
      accessStatus:false,
    }
  },
  mounted() {
    this.getLocation()
    this.currentPosition = setInterval(this.getLocation, 500)
=======
      info: null
    }
  },
  mounted() {

    axios({
      method: 'GET',
      url:'http://api.visitkorea.or.kr/openapi/service/rest/KorService/areaBasedList?ServiceKey=5rTZpUCbTNQQs2rG3%2FXoLvSO%2FDTYLSBp8OgERTKIgFOKwh5LHirGiqkQ%2Begr9tI6qHEkQJWFY2wHcA36h6DU6A%3D%3D&areaCode=39&MobileOS=ETC&MobileApp=AppTest&numOfRows=10000&_type=json',
      params: {

      }

    })
      .then(res => {
        console.log(res.data.response.body.items.item)
        this.info = res.data
        axios({
          method: 'POST',
          url: '/api/user/apiTest',
          data: res.data.response.body.items.item
        })
      })
      .catch((err) => {
        console.log(err)
      })

>>>>>>> Stashed changes
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
