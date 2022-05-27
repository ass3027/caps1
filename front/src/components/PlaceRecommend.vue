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
        <div
          v-if="item.pl_name!==''"
          class="innerText"
        >
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
      ],
    }
  },
  mounted() {
    axios
      .get('/api/recPlace')
      .then(res=>{
        res.data.forEach(i=>{
          if(i.pic_name==null){
            i.pic_name="placeImage/noImage.png"
          }
        })
        this.items=res.data


        if(res.data.length===0){
          this.items=[
            {
              pl_name:'',
              pic_name:'placeImage/no_place.png'
            }
          ]
        }

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
