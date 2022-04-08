<template>
  <div class="header-layout">
    <div class="login-box">
      <v-btn text> login</v-btn>
      <v-btn text> join</v-btn>
    </div>
    <div>
      <div>Packless Travel</div>
      <div class="hotel-list-form">
        <div>HOTEL</div>
        <div class="select-box">
          <v-select
              :items="hotelList"
              hide-details
              dense
              @change="test()"
              class="select-size"
          ></v-select>
        </div>
      </div>
      <v-divider class="divider-padding"></v-divider>
    </div>
  </div>
</template>
<script>
// import axios from 'axios';
export default {
  data() {
    return {
      hotelList: ['제주', '대구', '부산', '서울', '인천', '여수']
    };
  },
  methods: {
    // test() {
    //   let a = {
    //     hotelInfos: [
    //       {
    //         hotelName: 'hotel1',
    //         hotelImage: '',
    //         hotelPrice: 50000,
    //         hotelPhone: '010-3433-3333'
    //       },
    //       {
    //         hotelName: 'hotel1',
    //         hotelImage: '',
    //         hotelPrice: 50000,
    //         hotelPhone: '010-3433-3333'
    //       },
    //       {
    //         hotelName: 'hotel1',
    //         hotelImage: '',
    //         hotelPrice: 50000,
    //         hotelPhone: '010-3433-3333'
    //       },
    //       {
    //         hotelName: 'hotel1',
    //         hotelImage: '',
    //         hotelPrice: 50000,
    //         hotelPhone: '010-3433-3333'
    //       },
    //       {
    //         hotelName: 'hotel2',
    //         hotelImage: '',
    //         hotelPrice: 443,
    //         hotelPhone: '010-1232-3333'
    //       },
    //       {
    //         hotelName: 'hotel3',
    //         hotelImage: '',
    //         hotelPrice: 500443300,
    //         hotelPhone: '010-6655-3333'
    //       },
    //       {
    //         hotelName: 'hotel4',
    //         hotelImage: '',
    //         hotelPrice: 500055550,
    //         hotelPhone: '010-7653-3333'
    //       },
    //       {
    //         hotelName: 'hotel5',
    //         hotelImage: '',
    //         hotelPrice: 4332,
    //         hotelPhone: '010-5421-3333'
    //       },
    //       {
    //         hotelName: 'hotel6',
    //         hotelImage: '',
    //         hotelPrice: 6546,
    //         hotelPhone: '010-3333-3333'
    //       }
    //     ]
    //   };
    //   axios.get('test').then(res => {
    //     console.log(res);
    //   });

      // this.$store.state.test = a;
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
.login-box {
  display: flex;
  justify-content: flex-end;
}
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
