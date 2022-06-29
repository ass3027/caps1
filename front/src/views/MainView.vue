<template>
  <v-container>
    <v-card>
      h
    </v-card>
    <place-recommend></place-recommend>
    <best-place></best-place>
  </v-container>
</template>

<script>
// import axios from "axios";
import PlaceRecommend from "@/components/recommend/PlaceRecommend";
import BestPlace from "@/components/recommend/BestPlace";
export default {
  name: 'MainView',
  components: {
    BestPlace,
    PlaceRecommend
  },
  data() {
    return {}
  },
  mounted() {
  },
  methods: {},
}
</script>
<style scoped>
</style>
