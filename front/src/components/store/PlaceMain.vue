<template>
  <div class="main-layout">
    <v-carousel
      v-model="model"
      height="200px"
      cycle
      interval="3000"
    >
      <v-carousel-item
        v-for="(color, i) in colors"
        :key="color"
      >
        <v-sheet
          :color="color"
          height="100%"
          tile
        >
          <v-row
            class="fill-height"
            align="center"
            justify="center"
          >
            <div class="text-h2">
              Slide {{ i + 1 }}
            </div>
          </v-row>
        </v-sheet>
      </v-carousel-item>
    </v-carousel>
    <div
      v-for="(placeInfo, aIdx) in place.place"

      :key="aIdx"
      class="hotelcard"
      @click="goStoreInfo(placeInfo)"
    >
      <br><br>
      {{ placeInfo.title }}
      <v-card
        v-if="placeInfo.firstimage!=''"
        width="100%"
        height="250px"
      >
        <v-img
          v-if="placeInfo.firstimage"
          width="100%"
          height="250px"
          :src="placeInfo.firstimage"
          alt="실허어엄"
        />
      </v-card>
    </div>
  </div>
</template>
<script>
import StoreDetail from "@/views/store/StoreDetail";

export default {
  name: "PlaceMain",
  data() {
    return {
      dd: `/api/photo/` + "userImage/1648100757821img.jpg",
      model: 0,
      colors: ["primary", "secondary", "yellow darken-2", "red", "orange"],
    };
  },
  computed: {
    place() {
      return this.$store.state.place;
    },
  },

  methods: {
    goStoreInfo(placeInfo) {
      console.log(placeInfo);
      this.$router.push({name:'hotelDetail', params:{pl_id : placeInfo.pl_id}})
      // this.$router.push({name: 'hotelDetail', params:{pl_id :placeInfo.pl_id}, query:{pl_name: placeInfo.pl_name}});
    },
  },
};
</script>
<style scoped>
.main-layout {
  width: 70%;
  height: 100%;
  margin-top: 33px;
  /* overflow: auto; 스크롤안에 스크롤 */
  display: flex;
  flex-wrap: wrap;
  justify-content: center;
}

.hotelcard {
  /*margin-right: 3%;*/
  width: 800px;
  height: 300px;
  display: flex;
  flex-direction: column;
  cursor: pointer;
  align-items: center;
}
</style>
