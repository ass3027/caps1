<template>
  <v-container>
    <h2>장소 준비물 등록</h2>
    <v-card>
      <input v-model="keyword" type="text" @keyup.enter="getPlace">
      <v-btn @click="getPlace">찾기</v-btn>
      <p>선택된장소:{{selectedPlace}}</p>
      <p>선택된준비물:{{selectedSupply}}</p>
    </v-card>

    <v-row>
      <v-col cols="4">
        <v-container class="placeCard">
          <h2>장소</h2>
          <v-card v-for="(place,i) in places" :key="i" @click="selectPlace(place)" >
            <p>{{place.title}}</p>
          </v-card>
        </v-container>
      </v-col>

      <v-col cols="4">
        <v-container class="supplyCard">
          <h2>준비물</h2>
          <v-card v-for="(supply,i) in supplies" :key="i" @click="selectSupply(supply)" >
            <p>{{supply.supl_name}}</p>
          </v-card>
        </v-container>
      </v-col>

      <v-col cols="4">
        <v-container class="PlaceSuppliesCard">
          <h2>장소의 준비물</h2>
          <v-card v-for="(supply,i) in placeSupplies" :key="i" @click="selectSupply(supply)" >
            <p>{{supply.supl_name}}</p>
          </v-card>
        </v-container>
      </v-col>

    </v-row>

  </v-container>
</template>

<script>
import axios from 'axios'

export default {
  name: "PlaceSuppliesView",
  data() {
    return {
      keyword: '',
      places:'',
      selectedPlace:'',
      selectedSupply:'',
      supplies:'',
      placeSupplies:'',
    }
  },
  computed: {},
  mounted() {
    axios.get("/api/getSupl")
    .then(res => {
      console.log(res.data)
      this.supplies=res.data
    })
  },
  methods: {
    getPlace() {
      axios.get("/api/placeA01/" + this.keyword)
        .then(res => {
          console.log(res.data)
          this.places=res.data
        })

    },
    selectPlace(place){
      this.selectedPlace=place.title

      axios.get("/api/getPlaceSupl/" + place.pl_id)
        .then(res => {
          console.log(res.data)
          this.placeSupplies=res.data
        })

    },
    selectSupply(supply){
      this.selectedSupply=supply.supl_id
    },

  }
}
</script>

<style scoped>
.placeCard{
  height: 500px;
  overflow: scroll;
}
</style>
