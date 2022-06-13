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
              상품아이디:{{ item.gitem_id }}
            </div>
            <div>
              시작시간:{{ item.usage_time }}
              요구시간:{{ item.require_time }}
            </div>
            <v-list-item-title class="text-h5 mb-1">
              ID:{{ item.user_id }}
            </v-list-item-title>
            <v-list-item-subtitle>
              소개:{{ item.introduce }}
            </v-list-item-subtitle>
          </v-list-item-content>

          <v-list-item-avatar
            tile
            size="80"
            color="grey"
          />
        </v-list-item>

        <v-card-actions />
      </v-card>
    </v-row>
  </div>
</template>
<script>
import axios from "axios";

export default {
  name: "GuideProduct.vue",

  data(){
    return{
      lists:[]
    }
  },
  mounted() {
    this.importGitem()
  },

  methods:{
    importGitem(){
      axios({
        method:'get',
        url   :'/api/gitem',
      })
      .then((res)=>{
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
