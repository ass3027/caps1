<template>
  <div>
    [DUSER_ORDERS_COMPONENTS] {{ user_id }}
    <br>
    <div v-for="order in orders" :key="order.ord_id" class="mt-5">
      <DuserOrderItem :order="order"/>
    </div>

  </div>
</template>

<script>
import axios from "axios";
import DuserOrderItem from "@/components/DuserOrderItem";


export default {
  name: 'DuserOrdersComponent',
  components: {
    DuserOrderItem
  },
  props:{'user_id': String},
  data() {
    return {
      orders: [],
      latitude: null,
      longitude: null,
    }
  },
  computed:{

  },
  created(){

  },
  mounted() {
    this.test().then(
      )


  },
  methods: {
    test: async function test() {
      await navigator.geolocation.getCurrentPosition((position) => {

        axios.post('/api/location/update', {
          'user_id': this.user_id,
          'latitude': position.coords.latitude,
          'longitude': position.coords.longitude,
        }).then(() => {
          console.log("this.user_id: " + this.user_id)

          axios({
            method: 'GET',
            url: '/api/duser/orders',
            params:  {
              'user_id': this.user_id
            }
          }).then(res => {
            console.log(`[Orders]:`);
            console.log(res.data);
            this.orders = res.data
          })
        })


      })
    },

    getCurrentPosition() {

    }

    ,getOrderList() {


    },
    updateLocation(latitude, longitude){
      console.log(latitude + " " + longitude)


    }

  }
};
</script>

<style>
#gnb {
  display: flex;
  justify-content: center;
}
.gnb_stop {
  z-index: 300;
  position: fixed;
  top: 0;
  left: 0;
  width: 100%;
  height: 67px;
  background-color: white;
}

.login-box {
  display: block;
  /*justify-content: flex-end;*/
  float: right;
}

.menu-Bar {
  display: flex;
  justify-content: center;

}

/*.text-center {*/
/*  margin-top: 20px;*/
/*  display: flex;*/
/*  justify-content: center;*/
/*}*/

/*.hotel-list-form {*/
/*  display: flex;*/
/*  align-items: center;*/
/*}*/

/*.select-box {*/
/*  padding: 0 2%;*/
/*}*/

/*.select-size {*/
/*  width: 50%;*/
/*}*/

/*.divider-padding {*/
/*  margin-top: 10px;*/
/*}*/
</style>

