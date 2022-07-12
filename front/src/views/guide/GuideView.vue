<template>
  <div class="col-9 ">
    <input
      v-model="keyword"
      name="keyword"
      type="text"
      style="border: #1e90cc solid; margin-bottom:22px"
    >
    <v-btn
      @click="onsubmit()"
    >
      Search
    </v-btn>
    <div class="row">
      <div class="col-4" id="listaproyectos"
           v-for="(item,index) in lists"
           :key="index">
        <div class="card text-align textocorrecto">
          <!--Card image-->
          <v-img
            class="pic"
            height="140px"
            width="140px"
            :src="img(item.pic_name)"
          >
          </v-img>
          <!--Card content-->
          <div class="card-body">
            <!--Title-->
            <h4 class="card-title">{{item.user_name}}</h4>
            <!--Text-->
            <p class="card-text">{{item.guser_intro}}</p>

            <v-btn
              color="orange"
              text
              @click="view(item)"
            >
              VIEWS
            </v-btn>

            <v-btn
              v-if="item.user_id==$store.state.user.userId"
              color="orange"
              text
              @click="views(item)"
            >
              INTRO
            </v-btn>
          </div>
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

.col-4 {
  border: 10px double black;
}

</style>

