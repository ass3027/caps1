<template>
  <div
    id="duser_orders"
    style="padding-top: 60px"
  >
    <div>
      <h3>현재 위치</h3>
      <span>latitude: {{ latitude }}</span> <br>
      <span>longitude: {{ longitude }}</span>
    </div>


    <div>
      <!--      상태변경      -->
      <div class="status" style="margin: 0 auto">
        <span
          id="status1"
          @click="changeStatus(1)"
        >운송 가능</span>
        <span
          id="status2"
          @click="changeStatus(2)"
        >운송 중</span>
        <span
          id="status3"
          @click="changeStatus(3)"
        >운송 완료</span>
      </div>


      <!--      운송가능      -->
      <div
        v-if="status===1"
        class="orders_status"
      >
        <div
          v-for="(order, index) in orders_possible"
          :key="`possible-${index}`"
          class="item_container"
        >
          <DuserOrderItem
            :latitude="latitude"
            :longitude="longitude"
            :order="order"
          />
        </div>
        <div
          v-if="orders_possible === null || orders_possible.length === 0"
          class="wrap_empty"
        >

          <strong>운송가능한 주문이 없습니다.</strong>
        </div>
      </div>


      <!--      운송 중      -->
      <div
        v-else-if="status===2"
        class="orders_status"
      >
        <!--      픽업 전      -->
        <div style="text-align: center">
          <h3 class="sub_status">픽업 전</h3>
        </div>
        <div
          v-for="(order, index) in orders_pickup_before"
          :key="`before-${index}`"
          class="item_container"
        >
          <DuserOrderItem
            :latitude="latitude"
            :longitude="longitude"
            :order="order"
          />


        </div>
        <div
          v-if="orders_pickup_before.length === 0 || orders_pickup_before === null"
          class="wrap_empty"
        >
          <strong>픽업전인 주문이 없습니다.</strong>
        </div>

        <!--      픽업완료      -->
        <div style="text-align: center; padding-top: 50px">
          <h3 class="sub_status">운송 중</h3>
        </div>
        <div
          v-for="(order, index) in orders_pickup_after"
          :key="`after-${index}`"
          class="item_container"
        >
          <DuserOrderItem
            :latitude="latitude"
            :longitude="longitude"
            :order="order"
          />
        </div>

        <div
          v-if="orders_pickup_after.length === 0 || orders_pickup_after === null"
          class="wrap_empty"
        >
          <strong>운송중인 주문이 없습니다.</strong>
        </div>

      </div>


      <!--      운송완료      -->
      <div
        v-else
        class="orders_status"
      >
        <div
          v-for="(order, index) in orders_end"
          :key="`end-${index}`"
          class="item_container"
        >
          <DuserOrderItem
            :latitude="latitude"
            :longitude="longitude"
            :order="order"
          />
        </div>

        <div
          v-if="orders_end.length === 0"
          class="wrap_empty"
        >
          <strong>운송완료 내역이 없습니다.</strong>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import axios from "axios";
import DuserOrderItem from "@/components/duser/DuserOrderItem";


export default {
  name: 'DuserOrdersComponent',
  components: {
    DuserOrderItem
  },
  data() {
    return {
      userId: this.$store.state.user.userId,
      order_all: null,
      latitude: null,
      longitude: null,
      status: 1,
    }
  },
  computed: {
    orders_possible() {
      if (this.order_all === null) return null
      return this.order_all.filter(i => {
        return i.status === '승인완료'
      })
    },

    orders_pickup_before() {
      if (this.order_all === null) return null
      return this.order_all.filter(i => {
        return i.status === '픽업전'
      })
    },

    orders_pickup_after() {
      if (this.order_all === null) return null
      return this.order_all.filter(i => {
        return i.status === '운송중'
      })
    },

    orders_end() {
      if (this.order_all === null) return null
      return this.order_all.filter(i => {
        return i.status === '운송완료'
      })
    },


  },
  created() {
    axios.get('/api/duser/orders/' + this.userId).then(res => {
      this.order_all = res.data

      console.log('test', this.order_all)
    })


  },
  mounted() {
    this.test().then()


    const status1 = document.getElementById('status1')
    const status2 = document.getElementById('status2')
    const status3 = document.getElementById('status3')


    console.log('status1', status1)

    status1.style.borderBottom = 'none'
    status1.style.backgroundColor = 'white'

    status2.style.backgroundColor = '#dee2e6'
    status3.style.backgroundColor = '#dee2e6'


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
      const selected = document.getElementById('status' + status)

      for (let i of [1, 2, 3]) {
        if (i === status) continue;
        var selected_else = document.getElementById('status' + i)

        selected_else.style.borderBottom = ''
        selected_else.style.backgroundColor = '#dee2e6'

      }


      selected.style.borderBottom = 'none'
      selected.style.backgroundColor = 'white'
    }

  }
};
</script>

<style scoped>

.item_container {
  padding: 20px;
}

.orders_status {
  padding-top: 30px;
  border: 1px solid #ccc;
  border-top: none;
  border-bottom-left-radius: 10px;
  border-bottom-right-radius: 10px;
}

.status > span {
  width: 33.33%;
  text-align: center;
  display: inline-block;
  border: 1px solid #ccc;
  border-top-left-radius: 10px;
  border-top-right-radius: 10px;
  background-color: #f8f9fa;
}

#duser_orders {
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
  font-family: NotoSansCJKkr
}

.wrap_empty strong {
  display: block;
  margin-bottom: 10px;
  color: #666;
  font-size: 16px;
  font-weight: 500;
  line-height: 1.25;
  letter-spacing: -.3px
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
  color: #b5b5b5
}

.wrap_empty button.btn_type1 {
  width: 150px;
  height: 44px
}

.wrap_empty button.btn_type1 span {
  display: inline-block;
  height: 100%;
  font-weight: 500;
  font-size: 14px;
  line-height: 44px;
  color: #fff;
  text-align: center
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

.sub_status {
  display: block;
  border-bottom: 2px solid #D3D3D3;
  width: 96%;
  margin: 0 auto;
}
</style>

