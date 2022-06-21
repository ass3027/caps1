<template>
  <div id="duser_orders">
    [DUSER_ORDERS_COMPONENTS] {{ userId }}
    <br>
    {{ status }}
    <div>
      <div class="status" style="margin: 0 auto">
        <span id="status1" @click="changeStatus(1)">배송 가능</span>
        <span id="status2" @click="changeStatus(2)">배송 중</span>
        <span id="status3" @click="changeStatus(3)">배송 완료</span>
      </div>

      <div v-if="status===1"  class="orders_status">
        <div
          class="item_container"
          v-for="order in orders_possible"
          :key="order.ord_id">

          <DuserOrderItem
            :order="order"
            :latitude="latitude"
            :longitude="longitude"
          />
        </div>
        <div class="wrap_empty" v-if="orders_possible.length === 0">
          <strong>배송가능한 주문이 없습니다.</strong>
        </div>
      </div>
      <div v-else-if="status===2" class="orders_status">
        <div
          class="item_container"
          v-for="order in orders_shipping"
          :key="order.ord_id"
        >
          <DuserOrderItem
            :order="order"
            :latitude="latitude"
            :longitude="longitude"
          />
        </div>

        <div class="wrap_empty" v-if="orders_shipping.length === 0">
          <strong>배송중인 주문이 없습니다.</strong>
        </div>


      </div>
      <div v-else class="orders_status">
        <div
          class="item_container"
          v-for="order in orders_end"
          :key="order.ord_id"
        >
          <DuserOrderItem
            :order="order"
            :latitude="latitude"
            :longitude="longitude"
          />
        </div>

        <div class="wrap_empty" v-if="orders_end.length === 0">
          <strong>배송완료 내역이 없습니다.</strong>
        </div>
      </div>

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
      orders_possible: [],
      orders_shipping: [],
      orders_end: [],
      latitude: null,
      longitude: null,
      status: 1,
    }
  },
  computed:{

  },
  mounted() {
    this.test().then()


    axios.get('http://localhost:8080/api/duser/orders/' + this.userId + '/2').then(res => {
      this.orders_shipping = res.data
      console.log('test', this.orders_shipping)
    })

    axios.get('/api/duser/orders/' + this.userId + '/3').then(res => {
      this.orders_end = res.data
      console.log('test', this.orders_end)
    })

    var status = document.getElementById('status1')
    status.style.borderBottom = 'none'
    status.style.backgroundColor = 'white'

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
            url: '/api/duser/orders/' + this.userId + '/1'
          }).then(res => {
            console.log(`[Orders]:`);
            this.orders_possible = res.data
            console.log('this.orders', this.orders_possible);
          })
        })
      })
    },

    select(ord_id) {
      console.log("clicked " + ord_id)
      // this.$router.push("/orderDetail/" + ord_id).catch(()=>{})
      this.$router.push("/orderDetail/" + ord_id)
    },

    changeStatus(status) {

      console.log('changeStatus, status:', status)

      this.status = status
      var selected = document.getElementById('status'+status)

      for(let i of [1, 2, 3]){
        if(i === status) continue;
        var selected_else = document.getElementById('status'+i)

        selected_else.style.borderBottom = ''
        selected_else.style.backgroundColor = '#f8f9fa'

      }


      selected.style.borderBottom = 'none'
      selected.style.backgroundColor = 'white'
    }

  }
};
</script>

<style>

.item_container{
  padding: 20px;
}

.orders_status{
  border: 1px solid #ccc;
  border-top: none;
  border-bottom-left-radius: 10px;
  border-bottom-right-radius: 10px;
}

.status>span{
  width: 33.33%;
  text-align: center;
  display: inline-block;
  border: 1px solid #ccc;
  border-top-left-radius: 10px;
  border-top-right-radius: 10px;
  background-color: #f8f9fa;
}

#duser_orders{
  width: 1050px;
  margin: 0 auto;
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

.wrap_empty {
  display: flex;
  flex-direction: column;
  align-items: center;
  justify-content: center;
  height: 557px;
  text-align: center;
  font-family:NotoSansCJKkr
}

.wrap_empty strong {
  display: block;
  margin-bottom: 10px;
  color: #666;
  font-size: 16px;
  font-weight: 500;
  line-height: 1.25;
  letter-spacing:-.3px
}

.wrap_empty strong:before {
  display: block;
  width: 60px;
  height: 60px;
  margin: 0 auto 16px;
  /*content: ""*/
}

.wrap_empty p {
  margin-bottom: 24px;
  font-size: 14px;
  color:#b5b5b5
}

.wrap_empty button.btn_type1 {
  width: 150px;
  height:44px
}

.wrap_empty button.btn_type1 span {
  display: inline-block;
  height: 100%;
  font-weight: 500;
  font-size: 14px;
  line-height: 44px;
  color: #fff;
  text-align:center
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

