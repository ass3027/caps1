<template>
  <div id="duser_orders">
    [DUSER_ORDERS_COMPONENTS] {{ userId }}
    <br>
    <div>
      <span>
        배송 가능
      </span>
      <table
        v-for="order in orders"
        :key="order.ord_id"
        class="mt-5"
        style="margin: 0 auto"
        @click="select(order.ord_id)"
      >
        <DuserOrderItem
          :order="order"
          :latitude="latitude"
          :longitude="longitude"
        />
      </table>
    </div>

    <div>
      <span>
        배송 중
      </span>
      <table
        v-for="order in orders"
        :key="order.ord_id"
        class="mt-5"
        style="margin: 0 auto"
        @click="select(order.ord_id)"
      >
        <DuserOrderItem
          :order="order"
          :latitude="latitude"
          :longitude="longitude"
        />
      </table>
    </div>

    <div>
      <span>
        배송 완료
      </span>
      <table
        v-for="order in orders"
        :key="order.ord_id"
        class="mt-5"
        style="margin: 0 auto"
        @click="select(order.ord_id)"
      >
        <DuserOrderItem
          :order="order"
          :latitude="latitude"
          :longitude="longitude"
        />
      </table>
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
  data() {
    return {
      userId: this.$store.state.user.userId,
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
    this.test().then()




  },
  methods: {
    test: async function test() {
      await navigator.geolocation.getCurrentPosition((position) => {
        this.latitude = position.coords.latitude;
        this.longitude = position.coords.longitude;

        axios.post('/api/location/update', {
          'user_id': this.userId,
          'latitude': position.coords.latitude,
          'longitude': position.coords.longitude,
        }).then(() => {

          axios({
            method: 'GET',
            url: '/api/duser/orders',
            params:  {
              'user_id': this.userId
            }
          }).then(res => {
            console.log(`[Orders]:`);
            this.orders = res.data
            console.log('this.orders', this.orders);
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

#duser_orders{
  width: 80%;
  margin: 0 auto;
}

#duser_orders>div{
  width: 30%;
  display: inline-block;
  margin: 1.66%;
}
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

