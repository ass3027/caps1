<template>
  <div class="text-center">
    <!--    <v-dialog-->
    <!--      v-model="dialog"-->
    <!--      width="1500" h-->
    <!--    >-->
    <!--      <template v-slot:activator="{ on, attrs }">-->
    <!--        <v-btn color="red lighten-2" dark v-bind="attrs" v-on="on">-->
    <!--          장소-->
    <!--        </v-btn>-->
    <!--      </template>-->

    <v-card>
      <v-card-title class="text-h5 grey lighten-2">
        장소를 검색하세요
      </v-card-title>
      <v-card>
        <v-list-item>
          <div v-if="lists.length != 0">
            <div v-for="(date,index) in lists" :key="index">
              <div v-if="date.areacode==2">
              장소명: {{ date.title }}
              주소명:{{ date.addr1 }}
              지역코드:{{ date.areacode }}
              </div>
            </div>
          </div>
        </v-list-item>
      </v-card>

      <v-card>

      </v-card>

      <v-divider></v-divider>

      <v-card-actions>
        <v-spacer></v-spacer>
        <v-btn color="primary" text @click="dialog = false">
          완료
        </v-btn>
      </v-card-actions>
    </v-card>
    <!--    </v-dialog>-->
  </div>
</template>

<script>
import axios from "axios";

export default {
  data() {
    return {
      dialog: false,
      lists: [],

    }
  },
  mounted() {
    this.placeTitle()

  },
  methods: {
    placeTitle() {
      axios({
        method: "get",
        url: "/api/place2"
      })
        .then((res) => {
          this.lists = res.data;
          console.log(res);
        })
    }
  }

}
</script>

<style scoped>

</style>
