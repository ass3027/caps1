<template>
  <div class="header-layout">
    <!--<div class="login-box" />-->
    <div>
      <div>Packless Travel</div>
      <div class="hotel-list-form">
        <div>{{ category }}</div>
        <div class="select-box">
          <v-select
            :items="AddressList"
            hide-details
            dense
            class="select-size"
            @change="test()"
          />
        </div>
      </div>
      <v-divider class="divider-padding" />
    </div>
  </div>
</template>
<script>
// import axios from 'axios';
export default {
  name : 'PlaceHeader',
  props:['category'],
  data() {
    return {
      AddressList: ['서울', '경기도', '제주', '부산', '여수', '대전',]
    };
  },
  methods: {

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
