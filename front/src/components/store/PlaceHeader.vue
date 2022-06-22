<template>
  <div class="header-layout">
    <!--<div class="login-box" />-->
    <div>
      <div>Packless Travel</div>
      <div class="hotel-list-form">
        <div>{{ category }}</div>
        <div class="select-box">
          <v-select
            v-model="searchCon"
            :items="items"
            item-text="text"
            :value="lists.areaCode"
            @change="area()"
          />
        </div>
      </div>
      <v-divider class="divider-padding" />
    </div>
  </div>
</template>
<script>
import axios from "axios";
export default {
  name : 'PlaceHeader',
  components: {},
  props:['category'],
  data() {
    return {
      searchCon: '1',
      lists: [],
      items: [
        {text: '서울', value: '1'},
        {text: '인천', value: '2'},
        {text: '대전', value: '3'},
        {text: '대구', value: '4'},
        {text: '광주', value: '5'},
        {text: '부산', value: '6'},
        {text: '울산', value: '7'},
        {text: '세종특별자치시', value: '8'},
        {text: '경기도', value: '31'},
        {text: '강원도', value: '32'},
        {text: '충청북도', value: '33'},
        {text: '충청남도', value: '34'},
        {text: '경상북도', value: '35'},
        {text: '경상남도', value: '36'},
        {text: '전라북도', value: '37'},
        {text: '전라남도', value: '38'},
        {text: '제주도', value: '39'},
      ],
    };
  },
  methods: {
    area(){
      axios({
        method: 'GET',
        url: `/api/place1/${this.category}/${this.searchCon}`,
      })
        .then((res) => {
          this.$store.commit('place/PlaceAeraUpdate', res.data)
          console.log(res.data)
        })
    }
  }

};
</script>
<style scoped>
.header-layout {
  display: flex;
  height: 20vh;
  flex-direction: column;
  justify-content: space-between;
}
/*.login-box {*/
/*  display: flex;*/
/*  justify-content: flex-end;*/
/*}*/
.hotel-list-form {
  display: flex;
  align-items: center;
}
.select-box {
  padding: 0 2%;
}
.select-size {
  width: 50%;
}
.divider-padding {
  margin-top: 10px;
}
</style>
