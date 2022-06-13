<template>
  <div>
    [DUSER_ORDERS_COMPONENTS] {{ userId }}
    <br>
    <div
      v-for="order in orders"
      :key="order.ord_id"
      class="mt-5"
      @click="select(order.ord_id)"
    >
      <DuserOrderItem :order="order"/>
    </div>
  </div>
</template>

<script>
import axios from "axios";
import DuserOrderItem from "@/components/order/DuserOrderItem";


export default {
  name: 'DuserOrdersComponent',
  components: {
    DuserOrderItem
  },
  props:{'userId': String},
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
          'user_id': this.userId,
          'latitude': position.coords.latitude,
          'longitude': position.coords.longitude,
        }).then(() => {
          console.log("user_id: " + this.userId)

          axios({
            method: 'GET',
            url: '/api/duser/orders',
            params:  {
              'user_id': this.userId
            }
          }).then(res => {
            console.log(`[Orders]:`);
            console.log(res.data);
            this.orders = res.data
          })
        })
      })
    },
    select(ord_id) {
      console.log("clicked " + ord_id)
      this.$router.push("/orderDetail/" + ord_id).catch(()=>{})
    },

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

