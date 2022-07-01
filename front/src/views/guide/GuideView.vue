<template>
  <div
    class=""
    style="width: 1050px; padding-top: 60px"
  >
    <v-row style="padding-bottom: 20px">

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
    </v-row>
    <div>
      <div
        v-for="(item,index) in lists"
        :key="index"
        style="width: 33%;margin-right: 0.3%;margin-bottom: 2px; display: inline-block; border: 10px solid #dddfe1; border-radius: 10px"
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

          <div>
            <v-list-item-avatar
              tile
              size="80"
              color="grey"
            >
              <img :src="img(item.pic_name)">
            </v-list-item-avatar>

            <v-card-actions style="padding: 0">
              <v-btn
                outlined
                rounded
                text
                @click="view(item)"
              >
                상세보기
              </v-btn>
            </v-card-actions>
          </div>
        </v-list-item>
        <v-btn
          v-if="item.user_id==$store.state.user.userId"
          outlined
          rounded
          text
          @click="views(item)"
        >
          소개 작성
        </v-btn>
        <v-card-actions />
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


</style>

