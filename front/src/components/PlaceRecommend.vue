<template>
  <v-carousel>
    <v-carousel-item
      v-for="(item,i) in items"
      :key="i"
      :src="'/api/photo/'+item.pic_name"

      reverse-transition="fade-transition"
      transition="fade-transition"
    ><h2>추천컴포넌트</h2>{{i+1}}.{{item.pl_name}}</v-carousel-item>
  </v-carousel>
</template>

<script>
import axios from "axios";

export default {
  name: "PlaceRecommend",
  data () {
    return {
      items: [
        {
          src: 'https://cdn.vuetifyjs.com/images/carousel/squirrel.jpg',
        },
        {
          src: 'https://cdn.vuetifyjs.com/images/carousel/sky.jpg',
        },
        {
          src: 'https://cdn.vuetifyjs.com/images/carousel/bird.jpg',
        },
        {
          src: 'https://cdn.vuetifyjs.com/images/carousel/planet.jpg',
        },
      ],
    }
  },
  mounted() {
    axios
      .get('/api/recPlace')
      .then(res=>{
        console.log(res.data)
        res.data.forEach(i=>{
          if(i.pic_name==null){
            i.pic_name="placeImage/noImage.png"
          }
        })
        console.log(res.data)
        this.items=res.data

      })
  }

}
</script>

<style scoped>

</style>
