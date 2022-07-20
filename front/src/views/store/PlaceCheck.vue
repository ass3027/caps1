<template>
  <v-container>
    <template>
      <h3>내 등록시설 조회</h3>

      <v-data-table
        :headers="placeHeaders"
        :items="placeList"
        :items-per-page="5"
      >
        <template #[`item.detail`]="{ item }">
          <v-btn @click="placeDetail(item)">
            상세보기
          </v-btn>
        </template>
      </v-data-table>
    </template>
  </v-container>
</template>

<script>
const axios = require("axios");
export default {
  name: "PlaceCheck",
  data() {
    return {
      placeHeaders: [
        {text: '장소번호', align: 'start', sortable: false,value: 'pl_id'},
        {text: '시설이름', value: 'title'},
        {text: '주소', value: 'addr1'},
        {text: '전화번호', value: 'tel'},
        {text: '사업자번호', value: 'store_id'},
        {text: '상세보기', value: 'detail'}
      ],
      placeList: [],
      userId: '',
    }
  },
  computed: {},
  mounted() {
    this.placeData()
  },
  methods: {
    placeData() {
      //키퍼 장소조회 리스트
      axios.get("/api/placeCheck")
        .then((res) => {
          console.log('장소정보')
          console.log(res.data)
          this.placeList = res.data;
        })
    },
    placeDetail(item) {
      this.$router.push({name: 'placeDetail',params:{place:item}})
    }
  }
}
</script>

<style scoped>

</style>
