<template>
  <div class="main-layout">
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
        height="350px"
      >
        <v-img
          v-if="placeInfo.firstimage"
          width="100%"
          height="300px"
          :src="placeInfo.firstimage"
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
  height: 350px;
  display: flex;
  flex-direction: column;
  cursor: pointer;
  align-items: center;
}
</style>
