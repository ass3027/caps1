<template>
  <v-card
    :loading="loading"
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
      src="https://cdn.vuetifyjs.com/images/cards/cooking.png"
    />

    <v-card-title>{{ lists.user_id }}</v-card-title>

    <v-card-text>
      <v-row
        align="center"
        class="mx-0"
      >
        <v-rating
          :value="4.5"
          color="amber"
          dense
          half-increments
          readonly
          size="14"
        />

        <div class="grey--text ms-4">
          4.5 (413)
        </div>
      </v-row>

      <div class="my-4 text-subtitle-1">
        {{ lists.guide_license }}
      </div>

      <div>{{ lists.guide_intro }}</div>
    </v-card-text>

    <v-divider class="mx-4" />

    <v-card-title>Tonight's availability</v-card-title>

    <v-card-text>
      <v-chip-group
        v-model="selection"
        active-class="deep-purple accent-4 white--text"
        column
      >
        <v-chip>5:30PM</v-chip>

        <v-chip>7:30PM</v-chip>

        <v-chip>8:00PM</v-chip>

        <v-chip>9:00PM</v-chip>
      </v-chip-group>
    </v-card-text>

    <v-card-actions>
      <v-btn
        color="deep-purple lighten-2"
        text
        @click="reserve"
      >
        Reserve
      </v-btn>
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
