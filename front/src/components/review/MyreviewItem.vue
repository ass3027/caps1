<template>
  <div>


    <div
      id="form_review"
      name="form_review"
      style="width: 100%"
    >
      {{ this.payment.gitem_num }}
      {{ this.review.rev_id }}
      <table style="width:100%; border-bottom: 2px solid black ">
        <div class="name" style="float:left;"><h3>[<span v-if="type === 'guide'">가이드</span><span v-if="type === 'hotel'">호텔</span>] {{ place_info !== null ? place_info.title : 'a' }}</h3></div>
        <div style="float: right">
          <button type="button" style="font-size: 12px" @click="goProduct">상품 페이지로 이동></button>
        </div>
      </table>

      <div class="write_board">
        <table
          class="tbl"
          style="border-bottom: 2px solid black; width: 100%"
        >
          <colgroup>
            <col style="width:20%;">
            <col style="width:80%">
          </colgroup>
          <tbody>
          <tr>
            <th style="padding: 16px 0">
              제목
            </th>
            <td>
              <span class="content">{{ review.title}}</span>
            </td>
          </tr>
          <tr>
            <th style="padding: 16px 0">
              작성일
            </th>
            <td>
              <span class="content">{{ review.reg_date.slice(0, 10) }}</span>
            </td>
          </tr>
          <tr>
            <th>내용</th>
            <td>
              <div class="field_cmt">
                <span class="content">
                  {{ review.rev_content }}
                </span>
              </div>
            </td>
          </tr>

          <tr>
            <th>사진</th>
            <td>

              <div
                id="pictures"
                style="width: 150px;"
              >
                <div class="photoFrame" :style="`background-image: url('/api/photo/${review.img_link}')`" style="height: 150px; width: 150px; background-size: cover">
                </div>
              </div>
            </td>
          </tr>
          <tr v-if="answer">
            <th>답변</th>
            <td>

              <span class="content">
                {{ answer }}
              </span>
            </td>
          </tr>
          </tbody>
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
    },
  },
  data() {
    return {
      review: null,
      user: null,
      on: false,
      answer: null,
      seller_id: null,
      answer_content: '',
      place_info: null,
      type: null,
      payment: null,
    }
  },
  computed: {
    reg_date: function () {
      return this.review.reg_date.substring(0, 10);
    },
  },
  created() {
    this.review = this.review_prop


    axios.get('/api/review/payment/' + this.review.rev_id).then(res => {
      this.payment = res.data
    })

    // if (type === 'hotel') {
    //   axios.get('/api/review/productInfoHotel/' + this.review.rev_id).then(res => {
    //     console.log('product_info', res.data)
    //     this.product_info = res.data
    //   })
    // }

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

    console.log('/api/payment/placeInfo/' + this.type +'/' + this.review.pay_id)
    axios.get('/api/review/type/' + this.review.rev_id).then(res => {
      this.type = res.data

      axios.get('/api/payment/placeInfo/' + this.type +'/' + this.review.pay_id).then(res => {
        console.log('place_info', res.data)
        this.place_info = res.data
      })
    })

  },
  methods: {
    goProduct() {
      if (this.type === 'guide') {
        this.$router.push('/GuideProduct/' + 20);
      } else if (this.type === 'hotel') {
        this.$router.push('/palce/1/1')
        console.log('test test test', this.product_info.pl_id)
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


textarea {
  width: 947px;
  font-size: 12px;
}

.content {
  font-size: 12px;
}

th {
  border-top: 1px solid #dddfe1;
  font-size: 12px;
  color: #666;
}
td {
  padding: 10px 0 10px 10px;
  border-top: 1px solid #dddfe1;
}
input {
  width: 100%;
}
</style>
