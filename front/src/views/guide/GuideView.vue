<template>
<div>
  <input
    v-model="keyword"
    name="keyword"
    type="text"
    style="border: #1e90cc solid"
    class="search"
  >
  <v-btn @click="onsubmit()">
    검색
  </v-btn>

  <div style="display: flex"
  >
    <v-row
    cols="0"
    md="0">

  <v-card
    v-for="(item,index) in lists"
    :key="index"
    style="margin: 20px;"
    class="mx-auto"
    width="400"
  >
    <v-img
      class="white--text align-end"
      height="200px"
      :src="img(item.pic_name)"
    >
      <h2 style="color: black">{{item.user_name}}
      <br> {{item.user_id}}
      </h2>

    </v-img>

    <v-card-subtitle class="pb-0">

    </v-card-subtitle>

    <v-card-text class="text--primary">
      <div>자격증:{{item.guide_license}}</div>

      <div>소개:{{item.guser_intro}}</div>
    </v-card-text>

    <v-card-actions>
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
    </v-card-actions>
  </v-card>
    </v-row>
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


</style>

