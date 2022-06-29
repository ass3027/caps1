<template>
  <div>
    <h2>베스트 여행지</h2>
    <v-carousel style="width:300px;height: 300px">
      <v-carousel-item
        v-for="(item,i) in items"
        :key="i"
        :src="item.firstimage"

        reverse-transition="fade-transition"
        transition="fade-transition"
      >
        <div class="innerText">
          <h3>{{ i+1 }}.{{ item.title }}</h3>
          <v-btn @click="toCalendar(item)">
            일정에추가
          </v-btn>
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
      .get('/api/bestPlace')
      .then(res=>{
        console.log(res.data)

        res.data.forEach(i=>{
          if(i.pic_name==null){
            i.pic_name="placeImage/noImage.png"
          }
        })
        this.items=res.data

      })
  },
  methods:{
    toCalendar(item){
      this.$router.push({name:'calender', params:{rec:item.title}})
    }

  }

}
</script>

<style scoped>
/*.v-carousel{*/
/*  width: 500px;*/
/*  height: 300px;*/
/*  align: center;*/
/*}*/
.innerText{
  text-align: right;
  margin: 10px;
}

</style>
