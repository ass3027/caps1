<template>
  <div>
    [DUSER_ORDERS_ITEM]

    <div>
      <!-- 출발키퍼 주소 --> <!-- 거리 --> <!-- 도착키퍼 주소 -->    <!-- 운송품에 대한 간략한 정보 -->
      <!-- 운송원과 출발키퍼사이의 거리 -->                         <!-- 금액 -->

      <!-- 최소 도착시간(주문서.찾을시간) -->
      <!-- 예상 도착시간 (필요한가) -->
    </div>
  </div>
</template>

<script>
import axios from "axios";


export default {
  name: 'DuserOrderItem',
  props:{
    order: { type: Object },

  },
  data() {
    return {

    }
  },
  computed:{

  },
  created(){

  },
  mounted() {


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

