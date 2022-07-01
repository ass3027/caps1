<template>
  <v-container>
    <v-row justify="center" style="text-align: center">
      <v-col cols="8">
        {{tour.overview}}
      </v-col>
    </v-row>
  </v-container>
</template>

<script>
import axios from "axios";

export default {

  name: 'TourDetailView',
  components: {
  },
  props:['pl_id'],
  data() {
    return {
      tour:''
    }
  },
  mounted() {
    axios.get(`http://api.visitkorea.or.kr/openapi/service/rest/KorService/detailCommon?ServiceKey=5rTZpUCbTNQQs2rG3%2FXoLvSO%2FDTYLSBp8OgERTKIgFOKwh5LHirGiqkQ%2Begr9tI6qHEkQJWFY2wHcA36h6DU6A%3D%3D&contentId=${this.pl_id}&defaultYN=Y&addrinfoYN=Y&overviewYN=Y&MobileOS=ETC&MobileApp=AppTest`,
      {
      })
    .then((res)=>{
      console.log(res.data)
      this.tour=res.data.response.body.items.item
    })
  },
  method: {
  },

}

</script>

<style>
</style>
