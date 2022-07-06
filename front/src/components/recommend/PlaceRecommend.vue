<template>
  <div>
<!--    <h2>{{ $store.state.user.userId }}님에게 추천하는 여행지들</h2>-->
    <v-row v-if="items!=''">
      <v-col
        v-for="(item,i) in items"
        :key="i"
        cols="2"
        @click="$router.push({path:'/calender'})"
      >
        <img
        :src="item.firstimage"
        >
        <span
          v-if="item.title!==''"
          class="innerText"
        >
          <h3>{{ item.title }}</h3>
<!--          <v-btn @click="toCalendar(item)">-->
<!--            일정에추가-->
<!--          </v-btn>-->
        </span>
      </v-col>
    </v-row>
    <v-row v-else>
        <img
          src="/api/photo/placeImage/no_place.png"
          style="width: 230px;height:200px"
          class="mt-5"
          @click="$router.push({path:'/calender'})"

        >
    </v-row>

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
img{
  width: 180px;
  height: 180px;
}

</style>
