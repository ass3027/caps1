<template>
<div>
<!--    <script type="text/javascript" src="https://code.jquery-1.12.4.min.js"></script>-->
<!--    <script type="text/javascript" src="https://cdn.iamport.kr/js/iamport.payment-1.2.0.js"></script>-->

      <h2>IAMPORT 결제 데모</h2>
      <li>
        <button @click="iamportPayment" type="button">결제테스트</button>
      </li>
    </div>
</template>

<script>


export default {
  name: 'GuideReserve',
  components: {

  },
  data(){
    return{

    }
  },
  mounted(){
    const script = document.createElement("script")
    const script2 = document.createElement("script")
    script2.src = "https://code.jquery.com/jquery-3.6.0.min.js";
    script2.integrity = "sha256-/xUj+3OJU5yExlq6GSYGSHk7tPXikynS7ogEvDej/m4=";
    script2.setAttribute("crossOrigin" ,"anonymous");

    document.head.appendChild(script2);

    script.src = "https://cdn.iamport.kr/js/iamport.payment-1.2.0.js";
    script.type="text/javascript"

    document.head.appendChild(script);





  },
  methods:{
    iamportPayment(){
      var IMP = window.IMP;
      IMP.init('imp19569487');

      IMP.request_pay({
        pg:"kakaopay.TC0ONETIME",
        pay_method: "card",
        merchant_uid:"iamport_test_id" + new Date().getTime(),
        name:"가이드",
        amount: 20,
        buyer_email:"testiamport@naver.com",
        buyer_name:"홍길동",
        buyer_tel:"01012341234"
      }), function (rsp){
        if (rsp.success)
        {

        } else{

        }     }
    }
  }
}
// $(document).ready(function(){
//   $("#iamportPayment").click(function (){
//     payment();
//   })
// })
//
// function payment(data){
//   IMP.init('imp19569487');
//   IMP.request_pay({
//     pg:"kakaopay.TC0ONETIME",
//     pay_method: "card",
//     merchant_uid:"iamport_test_id",
//     name:"가이드",
//     amount: 20000,
//     buyer_email:"testiamport@naver.com",
//     buyer_name:"홍길동",
//     buyer_tel:"01012341234"
//   }, function (rsp){
//     if (rsp.success) {
//       alert("완료 -> imp_uid :" +rsp.imp_uid+" / merchant_uid(orderkey) : "+rsp.merchant_uid);
//     } else{
//       alert("실패 : 코드("+rsp.error_code+") /메세지("+rsp.error_msg+")");
//     }
//   })
// }
</script>
