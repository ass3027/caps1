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
              {{ review.rev_like }}
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
          v-if="review.img_link != null"
        >
          <img
            :src="'/api/photo/'+review.img_link"
            alt="리뷰 이미지"
            width="200px"
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
          <span class="num"> {{ review.rev_like }} </span>
        </button>
      </div>
      <div class="review_answer" v-if="answer">

        <h3>답변</h3><span> {{ answer }}</span>

      </div>
      <div class="answer_register" v-if="!answer && (seller_id === this.$store.state.user.userId)">

        <h3>답변 작성</h3>

        <table style="width: 100%" class="answer_textarea">
          <textarea style="float: left; width: 90%; height: 80px" v-model="answer_content">

          </textarea>
          <button type="button" @click="answerRegister" style="float: right; border: 1px solid #ccc; width: 9%; height: 80px">
            등록
          </button>
        </table>

      </div>


    </div>
  </div>
</template>

<script>

import axios from "axios";

export default {
  name: 'ReviewItem',
  props: {
    review_prop: {
      type: Object
    }
  },
  data() {
    return {
      review: null,
      user: null,
      on: false,
      answer: null,
      seller_id: null,
      answer_content: '',
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
  created() {
    this.review = this.review_prop

    axios.get('/api/review/answer/' + this.review.rev_id).then(res => {
      this.answer = res.data
    })

    axios.get('/api/review/seller/' + this.review.rev_id).then(res => {
      this.seller_id = res.data
    })

    axios({
      method: 'GET',
      url: '/api/user/find',
      params: {
        id: this.review.user_id
      }
    })
      .then(res => {
        this.user = res.data
      })
      .catch((err) => {
        console.log(err)
      })
    console.log(this.review.rev_content)
  },
  mounted() {
    console.log("[REVIEW]")
    console.log('review', this.review)
  },
  methods: {
    displayContent() {
      this.on = !this.on

      console.log('selected review', this.review.rev_id, this.on)
      if(this.on) {
        axios.post('/api/hit/' + this.review.rev_id).then(res => {
          if(res.data) {
            this.review.hit += 1
          }
          console.log('hit test', this.review.hit)
        })
      }

    },
    answerRegister() {
      axios({
        method : 'post',
        url    : '/api/review/answer/' + this.review.rev_id,
        params : {
          'content': this.answer_content
        }
      }).then((res) => {

        if(res.data) {
          alert('답변이 등록되었습니다.')
          this.$router.go();
        } else {
          alert('등록에 실패하였습니다.')
        }

      })
    },
    like() {
      axios.post('/api/like/' + this.review.rev_id).then(res => {
        if(res.data) {
          this.review.rev_like += 1
        }
        console.log('like test', this.review.rev_like)
      })
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

.review_answer {
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


.answer_textarea{
  padding: 8px 10px 9px;
  border: 1px solid #dddfe1;
}

textarea {
  width: 947px;
  font-size: 12px;
}
</style>
