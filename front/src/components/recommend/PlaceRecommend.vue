<template>
  <div>
    <h2>{{ $store.state.user.userId }}님에게 추천하는 여행지들</h2>
    <v-carousel v-if="items.length!=0">
      <v-carousel-item
        v-for="(item,i) in items"
        :key="i"
        :src="item.firstimage"

        reverse-transition="fade-transition"
        transition="fade-transition"
      >
        <div
          v-if="item.title!==''"
          class="innerText"
        >
          <h3>{{ i+1 }}.{{ item.title }}</h3>
          <v-btn @click="toCalendar(item)">
            일정에추가
          </v-btn>
        </div>
      </v-carousel-item>
    </v-carousel>
    <v-carousel v-else>
      <v-carousel-item
        src="/api/photo/placeImage/no_place.png"
      />
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
        console.log("ㄴㄹ"+res.data)
        if(res.data==''){
          return
        }
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
  },
  methods:{
    toCalendar(item){
      this.$router.push({name:'calender', params:{rec:item.title}})
    }
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
