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
              {{ item.guide_license }}
            </div>
            <v-list-item-title class="text-h5 mb-1">
              {{ item.user_id }}
            </v-list-item-title>
            <v-list-item-subtitle>{{ item.guide_intro }}</v-list-item-subtitle>
          </v-list-item-content>

          <v-card-actions>
            <v-btn
              outlined
              rounded
              text @click="view(item)"
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

        <v-card-actions>
          <v-btn
            outlined
            rounded
            text
          >
            Button
          </v-btn>
        </v-card-actions>
      </v-card>
    </v-row>
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
      //user_id:"",
      //guide_license:"",
      //guide_intro:"",


      lists: [],
    }
  },
  mounted() {
    this.importGuide()
  },
  methods:{
    view(item){
      console.log(item)
      this.$router.push("/GuideView/" + item.user_id)
    },


    importGuide(){
      axios({
        method: 'get',
        url   : '/api/guide',
      })
        .then((res) => {
          this.lists = res.data;
        })
    }
  }
}

</script>
<style>
.card {
  position: relative;
  display: flex;

}

</style>

