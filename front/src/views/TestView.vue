<template>
  <!--  <div class="section_write">-->
  <!--    <div class="head_aticle">-->
  <!--      <h2 class="tit">후기 작성</h2>-->
  <!--    </div>-->
  <!--  <form name="form_review" id="form_review" method="post" action="" enctype="multipart/form-data">-->
  <!--    <input type="hidden" name="mode">-->
  <!--    <input type="hidden" name="goodsno">-->
  <!--    <input type="hidden" name="ordno">-->
  <!--    <input type="hidden" name="package_goodsno">-->
  <!--    <input type="hidden" name="sno">-->
  <!--    <input type="hidden" name="referer">-->
  <!--    <input type="hidden" name="name">-->
  <!--    <div class="write_board">-->
  <!--      <div class="goods_info">-->
  <!--        <div class="thumb">-->
  <!--          <img src="http://img-cf.kurly.com/shop/data/goods/1622609196762s0.jpg" onerror="this.src='/shop/data/skin/designgj/img/common/noimg_100.gif'" alt="상품 이미지">-->
  <!--        </div>-->
  <!--        <div class="desc">-->
  <!--          <div class="inner_dese">-->
  <!--            <div class="name_main">[ardo] 냉동 완두콩 400g</div>-->
  <!--          </div>-->
  <!--        </div>-->
  <!--      </div>-->
  <!--      <table class="tbl">-->
  <!--        <caption style="display: none">후기 작성 입력상자</caption>-->
  <!--        <colgroup>-->
  <!--          <col style="width: 110px;">-->
  <!--          <col style="width: auto;">-->
  <!--        </colgroup>-->
  <!--        <tbody>-->
  <!--          <tr>-->
  <!--            <th>제목</th>-->
  <!--            <td>-->
  <!--              <input type="text" name="subject" placeholder="제목을 입력해주세요." class="inp " value>-->
  <!--            </td>-->
  <!--          </tr>-->
  <!--          <tr>-->
  <!--            <th>후기 작성</th>-->
  <!--            <td>-->
  <!--              <div class="field_cmt">-->
  <!--                <textarea>-->

  <!--                </textarea>-->
  <!--              </div>-->
  <!--            </td>-->
  <!--          </tr>-->
  <!--          <tr>-->
  <!--            <th>사진등록</th>-->
  <!--            <td>-->

  <!--            </td>-->
  <!--          </tr>-->
  <!--        </tbody>-->
  <!--      </table>-->
  <!--      <p class="link_inquire"></p>-->
  <!--      <button type="button" id="btnSubmit" class="btn_reg">등록하기</button>-->
  <!--    </div>-->
  <!--  </form>-->

  <!--  </div>-->
  <div>
    <DuserOrdersComponent/>
    <!--      <OrdersComponent :user_id="user_id" />-->
  </div>
</template>

<script>
// import OrdersComponent from "@/components/OrdersComponent";
import DuserOrdersComponent from "@/components/order/DuserOrdersComponent";
// import OrdersComponent from "@/components/OrdersComponent";



export default {

  name: 'TestView',
  components: {
    // OrdersComponent,
    DuserOrdersComponent
    // OrdersComponent

  },

  data() {
    return {
      userId: this.$store.state.user.userId
    }
  },
  method: {
    scrollEvents() {
      this.scroll = window.scrollY
    }
  },
  mounted() {
    console.log("[current_user_id]" + this.userId)

    // setInterval(function () {
    //   console.log(window.scrollY)
    // }, 300)

  },

}

</script>

<style>
/*.tit{*/
/*  height: 36px;*/
/*  font-weight: 700;*/
/*  font-size: 24px;*/
/*  line-height: 36px;*/
/*  color: #333;*/
/*  letter-spacing: -.5px;*/
/*}*/

</style>
