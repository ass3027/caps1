<template>
  <div class="board">
    <form name="frmList">
      <h3>Review ({{ reviews.length }})</h3>
      <!--/.title-->
      <table class="xans-board-listheader" width="100%" border="0" cellpadding="0" cellspacing="0">
        <caption style="display:none">구매후기 제목</caption>
        <colgroup>
          <col style="width:70px;">
          <col style="width:auto;">
          <col style="width:51px;">
          <col style="width:77px;">
          <col style="width:100px;">
          <col style="width:40px;">
          <col style="width:80px;">
        </colgroup>
        <tbody><tr>
          <th scope="col" class="input_txt">번호</th>
          <th scope="col" class="input_txt">제목</th>
          <th scope="col" class="input_txt"></th>
          <th scope="col" class="input_txt" align="left">작성자</th>
          <th scope="col" class="input_txt">작성일</th>
          <th scope="col" class="input_txt">도움</th>
          <th scope="col" class="input_txt">조회</th>
        </tr>
        </tbody></table>
      <div
        v-for="(review, index) in reviews"
        :key="index"
        class="tr_line"
        style="list-style: none"
      >
        <ReviewItem
          :review="review"
          @deleteReview="deleteReview"
          @review-updated="updateReview"
        />
      </div>
    </form>
    <p class="btnArea after" style="margin-bottom: 0">
<!--      <a>-->
<!--        <span class="bhs_button" style="line-height:30px; width:130px;">후기쓰기</span>-->
<!--      </a>-->
    </p>
  </div>
</template>

<script>
import ReviewItem from "@/components/review/ReveiwItem";

export default {


  name:'ReviewList',
  components: {
    ReviewItem
  },
  props: {
    reviews:{
      type: [Array, Object],
    }
  },
  data() {
    return {


    }
  },
  created() {

  },
  methods: {
    deleteReview() {
      this.$emit('deleteReview')
    },
    updateReview() {
      this.$emit('review-updated')
    }
  }

}
</script>

<style scoped>
.bhs_button:hover {
  width: 150px;
  display: inline-block;
  line-height: 40px;
  text-align: center;
  background-color: #fff;
  border: 1px solid #5f0080;
  color: #5f0080;
  font-size: 15px;
  float: right;
  margin-left: 2px;
  font-size: 13px;
}

.xans-product-additional .btnArea .bhs_button {
  margin: 20px 0 20px 5px;
}
.btnArea {
  display: table;
}
.xans-product-additional div.board>.btnArea {
  border-top: 1px solid #6a3664;
}
.xans-product-additional div.board .btnArea {
  padding: 10px 0;
  text-align: right;
  width: 100%;
  display: table;
}
.after:after {
  content: " ";
  display: block;
  height: 0;
  clear: both;
}
.xans-product-additional div.board .btnArea a {
  display: inline-block;
}
.xans-product-additional div.board .btnArea {
  padding: 10px 0;
  text-align: right;
  width: 100%;
  display: table;
}
.bhs_button {
  width: 150px;
  display: inline-block;
  line-height: 40px;
  text-align: center;
  background-color: #795b8f;
  border: 1px solid #5f0080;
  color: #fff;
  font-size: 15px;
  float: right;
  margin-left: 2px;
  font-size: 13px;
}

* {
  font-size: 12px;
}

.input_txt th {
  padding:10px 0
}
.xans-board-search .input_txt img {
  position: relative;
  right: 5px;
  top:-2px
}
.xans-board-listheaderd .ico_secret, .xans-board-title table td .ico_secret {
  width: 12px;
  height: 17px;
  margin-right: 10px;
  vertical-align:middle
}

.xans-board-listheader {
  border-top: 2px solid #522772;
  font-size: 13px;
  line-height:140%
}

.xans-board-listheader tr td {
  padding: 12px;
  border-top: 1px solid #f4f4f4;
  text-align: center;
  vertical-align: middle;
  word-wrap:break-word
}

.xans-board-listheader tbody tr:first-child td {
  border-top:1px solid #333
}

.xans-board-listheader.jh {
  border-bottom:1px solid #f4f4f4
}

.xans-board-listheader.jh tr td {
  padding:20px 0
}

.xans-board-listheader td.product {
  padding-left: 10px;
  text-align: left;
  font-size:16px
}

.xans-board-listheader td .price {
  font-size: 18px;
  font-weight: 700;
  color:#6a3664 !important
}

.xans-board-listheader td.button a span {
  width: 100px;
  line-height: 27px;
  margin:0 15px 5px 0
}

.xans-board-listheader .thumb {
  width:126px
}

.xans-board-listheader .thumb img {
  width:126px
}

.xans-board-listheader .thumb2 {
  width:150px
}

.xans-board-listheader .quantity {
  width:70px
}

.xans-board-listheader .system {
  width:100px
}

.xans-board-listheader .rev {
  width:100px
}

.xans-board-listheader .price_p {
  font-size: 12px;
  color:#000
}

.xans-board-listheader .opt {
  color: gray;
  font-size:12px
}

.xans-board-listheader td.product {
  width: auto;
  padding:12px 20px
}

.xans-board-listheader p.ordno {
  text-decoration: underline;
  letter-spacing:.2px
}

.xans-board-listheader th {
  padding: 20px 0;
  vertical-align: middle;
  font-size:12px
}

.xans-board-listheader .state {
  width: 110px;
  font-weight:700
}

.xans-board-listheader .product {
  width:auto
}

.xans-board-listheader .state {
  width: 110px;
  font-weight:700
}

.xans-board-listheader .number {
  width:181px
}

.xans-board-listheader .state {
  width: 156px;
  font-weight:700
}

.xans-board-listheader td.state {
  color:#795c90
}
</style>
