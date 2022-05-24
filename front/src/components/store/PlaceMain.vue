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
      v-for="(placeInfo, aIdx) in place.placeInfo"
      :key="aIdx"
      class="hotelcard"
      @click="gostoreInfo(placeInfo)"
    >
      <br><br>
      {{ placeInfo.store_name }}
      <v-card
        v-if="placeInfo.pic_name"
        width="300%"
        height="200px"
      >
        <v-img
          v-if="placeInfo.pic_name"
          width="100%"
          height="100%"
          :src="'/api/photo/' + placeInfo.pic_name"
          alt="실허어엄"
        />
      </v-card>
    </div>
  </div>
</template>
<script>
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
    gostoreInfo(placeInfo) {
      console.log('storeInfo:' + placeInfo);
      this.$router.push(`hotel/${placeInfo.store_id}`);
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
