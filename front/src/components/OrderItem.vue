<template>
  <div>
    <br>
    <div>
      [ORDER_ITEM] <br>
      ord_id:           {{ order.ord_id }} <br>
      user_id:          {{ order.user_id }} <br>
      ord_amount:       {{ order.ord_amount }} <br>
      entrust_time:     {{ order.entrust_time }} <br>
      withdraw_time:    {{ order.withdraw_time }} <br>
      keep_start:       {{ order.keep_start }} <br>
      keep_end:         {{ order.keep_end }} <br>
      delivery_id:      {{ order.delivery_id }} <br>
      call_time:        {{ order.call_time }} <br>
      status:           {{ order.status }} <br>
      pay_id:           {{ order.pay_id }} <br>
      ord_selection:    {{ order.ord_selection }} <br>

    </div>
    <div>

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
      keep_start: Object,
      keep_end: Object,
    }
  },
  computed:{

  },
  mounted() {
    if(this.order.keep_start !== null)
      axios({
        method: 'GET',
        url: 'api/keep/find',
        params: {
          keep_id: this.order.keep_start
        }
      }).then(res => {
        console.log(res.data)
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

