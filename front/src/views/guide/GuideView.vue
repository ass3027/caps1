<template>
  <div class="container">
    <div
      style="position: relative; left: 800px">
      <input
        v-model="keyword"
        name="keyword"
        type="text"
        style="border: #000000 solid"
        class="search"
      >

      <v-btn
        style="width: 90px; height: 30px; margin-top: -2px"
        class="btn2"
        @click="onsubmit()"
      >
        Search
      </v-btn>
    </div>

    <div class="item_list">
      <div class="card"
           v-for="(item,index) in lists"
           :key="index"
           @click="view(item)"

      >
        <div class="img">

          <v-img
            class="white--text align-end"
            height="200px"
            :src="img(item.pic_name)"
          >
            <h2 style="color: black">
              <br> {{item.user_id}}
            </h2>

          </v-img>
        </div>

        <div class="text-overline mb-4"
             style="white-space:nowrap; overflow:hidden; text-overflow:ellipsis; line-height: 3rem"
        >

          {{ item.user_name}}<br>
          {{ item.user_area}}<br>
          {{ item.guser_intro }}
          <v-btn
            style="left: 100px; width: 90px; height: 30px"
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
    name: 'GuideView',
    components: {

    },
    data(){
      return{
        lists: [],
        keyword:'',
      }
    },


    mounted() {
      this.importGuide()

    },
    methods:{
      view(item){

        this.$router.push("/GuideView/" + item.user_id)
      },
      views(item){
        this.$router.push("/GuideIntro/" + item.user_id)
      },
      onsubmit(){
        console.log(this.keyword)
       this.$router.push("/GuideView/Search/" + this.keyword)

      },
      img(fileName){
        return "/api/photo/" + fileName
      },


      importGuide(){
        axios({
          method: 'get',
          url   : '/api/guide',
        })
          .then((res) => {
            this.lists = res.data;
            console.log("asdasd" + res)
          })
      },

    }
  }

  </script>
<style scoped>

.container{
  max-width: 1100px;
  margin: auto;
}
.item_list{
  display: -webkit-box;
  flex-wrap: wrap;
  justify-content: space-between;
}

.item_list .card {
  border: 1px solid #eeeeee;
  border-radius: 5px;
  height: 350px;
  width:269px;
  padding: 5px;
  margin-bottom: 10px;
}
.item_list .card img{
  height: 200px;
  width: 250px;
}
</style>

