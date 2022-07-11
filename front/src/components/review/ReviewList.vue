<template>
  <div class="board">
    <form name="frmList">
      <!--/.title-->
      <table
        border="0"
        cellpadding="0"
        cellspacing="0"
        class="xans-board-listheader"
        width="100%"
      >
        <caption style="display:none">
          구매후기 제목
        </caption>
        <colgroup>
          <col style="width:70px;">
          <col style="width:auto;">
          <col style="width:51px;">
          <col style="width:77px;">
          <col style="width:100px;">
          <col style="width:40px;">
          <col style="width:80px;">
        </colgroup>
        <tbody>
          <tr>
            <th
              class="input_txt"
              scope="col"
            >
              번호
            </th>
            <th
              class="input_txt"
              scope="col"
            >
              제목
            </th>
            <th
              class="input_txt"
              scope="col"
            />
            <th
              align="left"
              class="input_txt"
              scope="col"
            >
              작성자
            </th>
            <th
              class="input_txt"
              scope="col"
            >
              작성일
            </th>
            <th
              class="input_txt"
              scope="col"
            >
              도움
            </th>
            <th
              class="input_txt"
              scope="col"
            >
              조회
            </th>
          </tr>
        </tbody>
      </table>
      <div
        v-for="(review, index) in reviews"
        :key="index"
        class="tr_line"
        style="list-style: none"
      >
        <ReviewItem
          :review_prop="review"
          @deleteReview="deleteReview"
          @review-updated="updateReview"
        />
      </div>
    </form>
  </div>
</template>

<script>
import ReviewItem from "@/components/review/ReviewItem";

export default {


  name: 'ReviewList',
  components: {
    ReviewItem
  },
  props: {
    reviews: {
      type: [Array, Object],
    }
  },
  data() {
    return {}
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

.btnArea {
  display: table;
}

.xans-product-additional div.board > .btnArea {
  border-top: 1px solid #333;
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


* {
  font-size: 12px;
}

.input_txt th {
  padding: 10px 0
}

.xans-board-search .input_txt img {
  position: relative;
  right: 5px;
  top: -2px
}


.xans-board-listheader {
  border-top: 1px solid #333;
  font-size: 13px;
  line-height: 140%
}

.xans-board-listheader tr td {
  padding: 12px;
  border-top: 1px solid #f4f4f4;
  text-align: center;
  vertical-align: middle;
  word-wrap: break-word
}

.xans-board-listheader tbody tr:first-child td {
  border-top: 1px solid #333
}

.xans-board-listheader.jh tr td {
  padding: 20px 0
}

.xans-board-listheader td.button a span {
  width: 100px;
  line-height: 27px;
  margin: 0 15px 5px 0
}


.xans-board-listheader .thumb img {
  width: 126px
}


.xans-board-listheader th {
  padding: 20px 0;
  vertical-align: middle;
  font-size: 12px
}

</style>
