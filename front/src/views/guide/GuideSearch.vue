<template>
  <div class="col-9 ">
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

.col-4 {
  border: 10px double black;
}

</style>

