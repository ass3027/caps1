<template>
  <div>
    <br>
    <div>
      [ORDER_ITEM]
      {{ order }}
    </div>

  </div>
</template>

<script>
import axios from "axios";

export default {
  name: 'OrderItem',
  props: {
    order: Object
  },
  data() {
    return {
      orders: [],

    }
  },
  computed:{

  },
  created(){

  },
  mounted() {

    console.log("[user_id]: "+this.user_id)
    axios({
      method: 'GET',
      url: 'api/user/orders',
      params: {
        user_id: this.user_id
      }
    }).then(res => {
      console.log(`[Orders]:`);
      console.log(res.data);
      this.orders = res.data
    })

  },
  methods: {

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

