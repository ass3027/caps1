<template>
  <v-card
    class="mx-auto my-12"
    max-width="374"
  >
    <template slot="progress">
      <v-progress-linear
        color="deep-purple"
        height="10"
        indeterminate
      />
    </template>

    <v-img
      height="250"
      v-bind:src="img(lists.pic_name)"
    />

    <v-card-title>{{ lists.user_name }}</v-card-title>

    <v-card-text>
      <v-row
        align="center"
        class="mx-0"
      >
        <div>
          {{lists.user_phone}}
        </div>
      </v-row>

      <div class="my-4 text-subtitle-1">
        {{ lists.guide_license }}
      </div>

      <div>{{ lists.guser_intro }}</div>
    </v-card-text>

    <v-divider class="mx-4" />

    <v-card-title>{{lists.user_id}}</v-card-title>
    <div>성별: {{lists.gender}}</div>

    <v-card-text>
      <v-chip-group
        v-model="selection"
        active-class="deep-purple accent-4 white--text"
        column
      >

      </v-chip-group>
    </v-card-text>

    <v-card-actions>

    </v-card-actions>
  </v-card>
</template>

<script>
import axios from "axios";

export default {
  name:'GuideViewUser',
  props:['user_id'],

  data(){
    return {
      lists:'',
      selection:0,
      loading:"dd"
    }
  },
  mounted() {
    this.importGuide()

  },

  methods:{
    reserve(){
    },
    img(fileName){
      return "http://localhost:8081/api/photo/" + fileName
    },

    importGuide() {
      axios({
        method:'get',
        url : `/api/guide/`+ this.user_id,
      })
        .then((res)=>{
          this.lists = res.data;
          console.log(res.data + "상세보기")
        })
    }
  }


}


</script>
