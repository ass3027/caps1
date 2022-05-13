<template>
  <div>
    <h2>{{ $store.state.user.userId }}님에게 추천하는 여행지들</h2>
    <v-carousel>
      <v-carousel-item
        v-for="(item,i) in items"
        :key="i"
        :src="'/api/photo/'+item.pic_name"

        reverse-transition="fade-transition"
        transition="fade-transition"
      >
        <div class="innerText">
          <h3>{{ i+1 }}.{{ item.pl_name }}</h3>
          <v-btn>일정에추가</v-btn>
        </div>
      </v-carousel-item>
    </v-carousel>
  </div>
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
.v-carousel{
  width: 500px;
  height: 300px;
  align: center;
}
.innerText{
  text-align: right;
  margin: 10px;
}

</style>
