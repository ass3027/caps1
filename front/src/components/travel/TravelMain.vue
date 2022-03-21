<template>
  <div class="main-layout">
    <v-carousel v-model="model" height="200px" cycle interval="3000">
      <v-carousel-item v-for="(color, i) in colors" :key="color">
        <v-sheet :color="color" height="100%" tile>
          <v-row class="fill-height" align="center" justify="center">
            <div class="text-h2">Slide {{ i + 1 }}</div>
          </v-row>
        </v-sheet>
      </v-carousel-item>
    </v-carousel>
    <div
        v-for="(hotelInfo, aIdx) in test.hotelInfos"
        :key="aIdx"
        class="hotelcard"
        @click="goHotelInfo(hotelInfo)"
    >
      {{ hotelInfo.user_id }}
      <v-card width="100%" height="200px"></v-card>
    </div>
  </div>
</template>
<script>
export default {
  name: 'TravelMain',
  data() {
    return {
      model: 0,
      colors: ['primary', 'secondary', 'yellow darken-2', 'red', 'orange']
    };
  },
  computed: {
    test() {
      return this.$store.state.test;
    }
  },
  methods: {
    goHotelInfo(hotelInfo) {
      console.log(hotelInfo);
      // console.log(this.$router);
      // console.log(`/${hotelInfo.hotelName}`);
      this.$router.push(`/${hotelInfo.hotelName}`);
    }
  }
};
</script>
<style scoped>
.main-layout {
  width: 70%;
  height: 100%;
  margin-top: 25px;
  /* overflow: auto; 스크롤안에 스크롤 */
  display: flex;
  flex-wrap: wrap;
  justify-content: center;
}
.hotelcard {
  margin-right: 3%;
  width: 200px;
  height: 250px;
  display: flex;
  flex-direction: column;
  cursor: pointer;
  align-items: center;
}
</style>
