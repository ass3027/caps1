<template>
  <div class="card">

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
              자격증:<br>{{item.guide_license}}
            </div>
            <v-list-item-title class="text-h5 mb-1">
              ID:{{ item.user_id }}
            </v-list-item-title>
            <v-list-item-subtitle>
              소개<br>{{ item.guide_intro }}
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
          />
        </v-list-item>

        <v-card-actions />
      </v-card>
    </v-row>
    <div>
      <h1>검색</h1>
      <input name="keyword" type="text" style="border: #1e90cc solid" v-model="keyword" class="search">
    </div>
    <v-btn
      class="btn2"
      @click="onsubmit()"
    >
      Search
    </v-btn>

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
    onsubmit(){
      console.log(this.keyword)
     this.$router.push("/GuideView/Search/" + this.keyword)

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
<style>
.card {
  position: relative;
  display: flex;

}

</style>

