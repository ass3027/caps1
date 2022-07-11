<template>
  <div>
    <div style="display: flex">
      <v-row
        style="margin: 18px"
        cols="0"
        md="0"
      >
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
            <h2 style="color: black">
              {{ item.user_name }}
              <br> {{ item.user_id }}
            </h2>
          </v-img>

          <v-card-subtitle class="pb-0" />

          <v-card-text class="text--primary">
            <div>자격증:{{ item.guide_license }}</div>

            <div>소개:{{ item.guser_intro }}</div>
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
.card {
  position: relative;
  display: flex;

}

</style>

