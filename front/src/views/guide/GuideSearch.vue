<template>
  <div class="card">
    <h1>{{ keyword }}</h1>
    <v-row
      cols="0"
      md="0"
    >
      <v-card
        v-for="(item,index) in lists"
        :key="index"
        class="mx-auto"
        width="344"
        outlined
        justify="start"
      >
        <v-list-item three-line>
          <v-list-item-content>
            <div class="text-overline mb-4">
              이름:{{ item.user_name }}
            </div>
            <div>
              자격증:<br>{{ item.guide_license }}
            </div>
            <v-list-item-title class="text-h5 mb-1">
              ID:{{ item.user_id }}


            </v-list-item-title>
            <v-list-item-subtitle>
              소개<br>{{ item.guser_intro }}
            </v-list-item-subtitle>
          </v-list-item-content>

          <v-card-actions>
            <v-btn
              outlined
              rounded
              text
              @click="view(item)"
            >
              상세보기
            </v-btn>
          </v-card-actions>

          <v-list-item-avatar
            tile
            size="80"
            color="grey"
          ><img v-bind:src="img(item.pic_name)"></v-list-item-avatar>
        </v-list-item>

        <v-card-actions />
      </v-card>
    </v-row>
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
      return "http://localhost:8081/api/photo/" + fileName
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

