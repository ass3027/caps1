<template>
  <div class="container">
    <div class="item_list">
      <div class="card"
           v-for="(item,index) in lists"
           :key="index"

      >
        <div class="img">

          <v-img
            class="white--text align-end"
            height="200px"
            :src="img(item.pic_name)"
          >
            <h2 style="color: black">
            </h2>

          </v-img>
        </div>

        <div class="text-overline mb-4"
             style="white-space:nowrap; overflow:hidden; text-overflow:ellipsis; line-height: 3rem"
        >

          {{ item.user_name}}({{item.user_id}})<br>
          {{ item.guser_intro }}<br>
          {{ item.user_area}}
          <v-btn
            style="left: 71px; width: 90px; height: 30px"
            @click="views(item)"
          >
            자세히보기
          </v-btn>
        </div>
      </div>
    </div>

  </div>
</template>

<script>
import axios from "axios";
export default {
  name: "GuideSearch.vue",
  components:{

  },
  props:['keyword'],
  data(){
    return{
      lists:[],
    }
  },
  mounted() {

    console.log("keyword: " + this.keyword)
    this.importGuide()
  },
  methods:{
    importGuide(){
      axios({
        method: 'get',
        url   : '/api/guide/search/' + this.keyword,
      })
        .then((res) => {
          this.lists = res.data;
          console.log("asdsa" + res.data);
        })
    },
    img(fileName){
      return "/api/photo/" + fileName
    },
    view(item){

      this.$router.push("/GuideView/" + item.user_id)
    }
    },


}
</script>

<style scoped>

.container{
  max-width: 1100px;
  margin: auto;
}
.item_list{
  display: inline-flex;
  flex-wrap: wrap;
  justify-content: space-between;
}

.item_list .card {
  border: 1px solid #eeeeee;
  border-radius: 5px;
  height: 350px;
  width:250px;
  padding: 5px;
  margin-bottom: 10px;
}
.item_list .card img{
  height: 200px;
  width: 250px;
}
</style>

