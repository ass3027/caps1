<template>
  <div>
    <table
      border="0"
      cellpadding="0"
      cellspacing="0"
      class="xans-board-listheaderd tbl_newtype1"
      style="border-top: 1px solid #e3e3e3;"
      width="100%"
      @click="displayContent"
    >
      <colgroup>
        <col style="width:70px;">
        <col style="width:auto;">
        <col style="width:51px;">
        <col style="width:77px;">
        <col style="width:100px;">
        <col style="width:40px;">
        <col style="width:80px;">
      </colgroup>
      <tbody class="review-lists">
        <tr>
          <td style="text-align: center">
            {{ review.rev_id }}
          </td>
          <td class="subject">
            {{ review.title }}
          </td>
          <td class="user_grade grade_comm" />
          <td class="user_grade">
            {{ user.user_name }}
          </td>
          <td class="time txt_center">
            {{ reg_date }}
          </td>
          <td class="txt_center">
            <span class="review-like-cnt">
              {{ review.like }}
            </span>
          </td>
          <td class="txt_center">
            <span class="review-hit-cnt">
              {{ review.hit }}
            </span>
          </td>
        </tr>
      </tbody>
    </table>

    <div
      v-if="on"
      id="content_view"
      class="review_view"
      style="display: block;"
    >
      <div class="inner_review">
        <div
          class="review_photo"
          style="padding-top: 30px"
        >
          <!--          <img src="/api/photo/altImage/packless_travel_logo.png" border="0">-->
          <img
            v-if="review.rev_img_filename != null"
            :src="'/api/photo/'+review.rev_img_filename"
            alt="리뷰 이미지"
            width="600px"
          >
          <br>
          <br>
        </div>

        <br>
        <span>{{ review.rev_content }}</span>
      </div>
      <div class="goods-review-grp-btn">
        <button
          class="styled-button review-like-btn"
          type="button"
          @click="like"
        >
          도움이 돼요
          <span class="num"> {{ review.like }} </span>
        </button>
      </div>
    </div>
  </div>
</template>

<script>

import axios from "axios";

export default {
  name: 'ReviewItem',
  props: {
    review: {
      type: Object
    }
  },
  data() {
    return {
      rev_rating: this.review.rev_rating,
      user: {},
      on: false,
    }
  },
  computed: {
    reg_date: function () {
      return this.review.reg_date.substring(0, 10);
    },
    // rev_img: function() {
    //   return "http://localhost:8080/api/photo/"+this.review.rev_img_filename;
    // }
    // answer: function () {
    //   axios.get('/api/')
    // }
  },
  mounted() {
    console.log("[REVIEW]")
    console.log('review', this.review)
    axios({
      method: 'GET',
      url: 'http://localhost:8080/api/user/find',
      params: {
        user_id: this.review.user_id
      }

    })
      .then(res => {
        console.log("[user/find]")
        console.log(res.data)

        this.user = res.data
      })
      .catch((err) => {
        console.log(err)
      })
    console.log(this.review.rev_content)
  },
  methods: {
    displayContent() {
      this.on = !this.on
      console.log('test', this.review.rev_id)

    },
    like() {


      // DB 변경 추후
    }
  }
}

</script>

<style scoped>
img {
  display: block;
  max-width: 600px;
  margin: 0 auto;
}

.review-like-btn {
  height: 32px;
  width: auto;
  min-width: 75px;
  padding: 0 15px;
  line-height: 28px;
  font-size: 12px;
  display: inline-block;
  border: 1px solid #ccc;
  background-color: #fff;
  color: #333;
  text-align: center;
}

.review_view {
  display: none;
  padding: 10px 10px 11px;
  /*border-top: 1px solid #e3e3e3;*/
}

.goods-review-grp-btn {
  text-align: right;
}

.inner_review {
  width: 100%;
  padding: 20px 9px 9px;
  line-height: 25px;
}

table {
  border-color: gray;
}


.subject {
  padding-left: 50px;
}

.txt_center {
  text-align: center;
}

.tr_line .user_name {
  display: inline-block;
  text-align: center;
  color: #ccc
}

.tr_line .user_name:hover {
  text-decoration: underline
}

.tr_line .user_name .btn_userReview {
  display: none;
  position: absolute;
  top: 17px;
  left: 36px;
  width: 81px;
  height: 24px;
  background: #fff;
  color: #4c4c4c;
  border: 1px solid #aaa;
  line-height: 20px;
  text-align: center;
  font-size: 11px
}

.happy_faq .subject {
  display: block;
  padding: 30px 0 6px;
  font-weight: 700;
  font-size: 15px;
  color: #ccc;
  line-height: 22px
}

.happy_faq .subject.no {
  display: none
}

.happy_faq .subject.no_padding {
  padding-top: 0
}


.order .happy_faq .subject {
  padding: 28px 0 0;
  color: #333
}


.agree_ready .subject {
  display: block;
  font-style: initial;
  font-family: malgun gothic
}

.xans-board-write table #subject {
  width: 630px
}

.xans-product-additional table.tbl_newtype1 td.user_grade {
  padding: 22px 0 23px;
  line-height: 19px;
  text-align: left
}

.xans-board-title table.tbl_newtype1 td.user_grade {
  padding: 0;
  line-height: 19px;
  text-align: left
}

.grade_comm .ico_grade {
  display: inline-block;
  min-width: 38px;
  height: 16px;
  padding: 0 4px;
  border-radius: 30px;
  font-size: 9px;
  color: #fff;
  line-height: 14px;
  text-align: center
}

.grade_comm .grade6 {
  border: 1px solid #949296;
  background-color: #fff;
  color: #949296
}

.grade_comm .grade0 {
  border: 1px solid #5f0080;
  background-color: #fff;
  color: #5f0080
}

.grade_comm .grade5 {
  border: 1px solid #cba3e8;
  background-color: #cba3e9
}

.grade_comm .grade1 {
  border: 1px solid #a864d7;
  background-color: #a864d8
}

.grade_comm .grade2 {
  border: 1px solid #8c4cc3;
  background-color: #8c4cc4
}

.grade_comm .grade3 {
  border: 1px solid #641797;
  background-color: #641798
}

.grade_comm .grade4 {
  border: 1px solid #4f1770;
  background-color: #4f177a
}
</style>
