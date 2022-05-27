<template>
  <div>

  </div>
</template>

<script>

import axios from 'axios'

export default {
  name: 'TestView',
  data() {
    return {
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


    }

}

</script>

<style scoped>

</style>
