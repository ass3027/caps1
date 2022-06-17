<template>
  <v-container>
    <h2>장소 준비물 등록</h2>
    <v-card>
      <input v-model="keyword" type="text" @keyup.enter="getPlace">
      <v-btn @click="getPlace">장소 찾기</v-btn>
      <p>선택된장소:{{ selectedPlace.title }}</p>
      <p>선택된준비물:{{ selectedSupply.supl_name }}</p>
    </v-card>

    <v-card>
      <v-row>
        <v-col cols="4">
          <v-container class="placeCard">
            <h2>장소</h2>
            <v-card v-for="(place,i) in places" :key="i" @click="selectPlace(place)">
              <p>{{ place.title }}</p>
            </v-card>
          </v-container>
        </v-col>

        <v-col cols="4">
          <v-container class="placeCard">
            <h2>준비물</h2>
            <p>준비물을 클릭하면 장소에 추가 됩니다</p>
            <v-card>
              <input v-model="suplName" type="text" placeholder="직접입력" @keyup.enter="searchSupply">
              <v-btn @click="searchSupply">검색</v-btn>

              <div v-if="supplies == ''">
                해당하는 준비물이 없습니다. 직접 추가하시겠습니까?
                <v-btn @click="addSupply">추가</v-btn>
              </div>

            </v-card>

            <v-card v-for="(supply,i) in supplies" :key="i" @click="addPlSupply(supply)">
              <p>{{ supply.supl_name }}</p>
            </v-card>
          </v-container>
        </v-col>

        <v-col cols="4">
          <v-container class="placeCard">
            <h2>장소의 준비물</h2>
            <p>준비물을 클릭하면 장소에서 제거됩니다</p>
            <v-card v-for="(supply,i) in placeSupplies" :key="i" @click="deletePlSupply(supply)">
              <p>{{ supply.supl_name }}</p>
            </v-card>
          </v-container>
        </v-col>

      </v-row>
    </v-card>

  </v-container>
</template>

<script>
import axios from 'axios'

export default {
  name: "PlaceSuppliesView",
  data() {
    return {
      keyword: '',
      places: '',
      selectedPlace: '',
      selectedSupply: '',
      supplies: '',
      placeSupplies: '',
      suplName: ''
    }
  },
  computed: {},
  mounted() {
    axios.get("/api/getSupl")
      .then(res => {
        console.log(res.data)
        this.supplies = res.data
      })
  },
  methods: {
    getPlace() {
      axios.get("/api/placeA01/" + this.keyword)
        .then(res => {
          console.log(res.data)

          this.places = res.data
        })

    },
    selectPlace(place) {
      this.selectedPlace = place;
      this.getPlaceSupl()

    },
    getPlaceSupl() {
      axios.get("/api/getPlaceSupl/" + this.selectedPlace.pl_id)
        .then(res => {
          console.log(res.data)

          this.placeSupplies = res.data
        })

    },
    addPlSupply(supply) {
      if (this.selectedPlace == '') {
        return alert("장소를 먼저 선택해주세요")
      }
      this.selectedSupply = supply
      axios.post("/api/addPlSupply", {},
        {
          params: {
            supl_id: supply.supl_id,
            pl_id: this.selectedPlace.pl_id
          }
        }).then(() => {
        this.getPlaceSupl()
      }).catch(() => {
        alert("실패:이미 있는 준비물입니다")
      })
    },
    deletePlSupply(supply) {
      axios.delete("/api/delPlSupply",
        {
          params: {
            supl_id: supply.supl_id,
            pl_id: supply.pl_id
          }
        }).then(() => {
        this.getPlaceSupl()
      })
    },
    searchSupply() {
      axios.get("/api/searchSupply",
        {params: {keyword: this.suplName}}
      ).then((res) => {
        this.supplies = res.data
      })
    },
    addSupply() {
      axios.post("/api/addSupply", "",
        {params: {supl_name: this.suplName}})
        .then((res) => {
          console.log(res.data)
          var supply = {supl_id: res.data}
          this.addPlSupply(supply)

        })
    }
  }
}
</script>

<style scoped>
.placeCard {
  height: 500px;
  overflow: scroll;
}
.placeCard > .v-card> p{
  padding:3px;
  margin:3px;
}
</style>
