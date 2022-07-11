<template>
  <div style="max-width: 1050px">
    <br>

    <div class="xans-element- xans-product xans-product-additional detail_board  ">
      <div v-if="reviews.length === 0">
        리뷰 없음
      </div>

      <div v-else>
        <review-list
          :reviews="slice_reviews"
          @deleteReview="getReviews"
          @reveiws-updated="reviewsUpdated"
        />
      </div>
    </div>

    <p class="btnArea after">
      <button
        href="#none"
        @click="reviewRegister()"
      >
        <span
          class="bhs_button"
          style="line-height:30px; width:130px;"
        >후기쓰기</span>
      </button>
    </p>

    <div class="board_pg_area">
      <a
        class="layout-pagination-button layout-pagination-first-page"
        @click="pageFirst"
      >맨 처음 페이지로 가기</a>
      <a
        class="layout-pagination-button layout-pagination-prev-page"
        @click="pagePrev"
      >이전 페이지로 가기</a>
      <a
        v-for="page_index in max_page"
        :key="page_index"
        :ref="page_index"
        class="layout-pagination-button layout-pagination-number"
        @click="pageChange(page_index)"
      >
        {{ page_index }}
      </a>
      <a
        class="layout-pagination-button layout-pagination-next-page"
        @click="pageNext"
      >다음 페이지로 가기</a>
      <a
        class="layout-pagination-button layout-pagination-last-page"
        @click="pageLast"
      >맨 끝 페이지로 가기</a>
    </div>
  </div>
</template>

<script>
import ReviewList from "@/components/review/ReviewList";
import axios from "axios";

export default {
  name: 'ReviewView',
  components: {
    // ReviewForm,
    ReviewList,
  },
  props: {
    id: {
      type: String
    },
    type: {
      type: String
    }
  },
  data() {
    return {
      reviews: [],
      overlay: false,
      zIndex: 100,
      current_page: 1,
    }
  },
  computed: {
    slice_reviews() {
      var current_page = this.current_page
      return this.reviews.slice((current_page - 1) * 7, current_page * 7)
    },
    max_page() {
      return Math.ceil(this.reviews.length / 7)
    }

  },
  created() {
    this.getReviews()
  },
  mounted() {
    console.log('test', this.type, this.id)
  },
  methods: {
    reviewRegister() {
      // this.$router.push("/ReviewCreate/" + this.type + '/' + this.id)
      this.$router.push('/MyPage/MyPaymentList')

      console.log('test', '!!!!!!!!!!!')
    },
    getReviews() {

      axios.get('/api/find/reviews/' + this.type + '/' + this.id)
        .then(res => {
          console.log(res.data)
          this.reviews = res.data
        })
        .catch((err) => {
          console.log(err)
        })

      // if (this.type === 'product') {
      //   axios.get('http://localhost:8080/api/productReview?pd_id=' + this.id)
      //     .then(res => {
      //       console.log(res.data)
      //       this.reviews = res.data
      //     })
      //     .catch((err) => {
      //       console.log(err)
      //     })
      //
      // } else if (this.type === 'store') {
      //   axios.get('http://localhost:8080/api/storeReview?store_id=' + this.id)
      //     .then(res => {
      //       console.log(res.data)
      //       this.reviews = res.data
      //     })
      //     .catch((err) => {
      //       console.log(err)
      //     })
      // } else if (this.type === 'guide') {
      //   axios.get('http://localhost:8080/api/guideReview?guide_id=' + this.id)
      //     .then(res => {
      //       console.log(res.data)
      //       this.reviews = res.data
      //     })
      //     .catch((err) => {
      //       console.log(err)
      //     })
      // } else if (this.type === 'gitem') {
      //   axios.get('http://localhost:8080/api/gitemReview?gitem_id=' + this.id)
      //     .then(res => {
      //       console.log(res.data)
      //       this.reviews = res.data
      //     })
      //     .catch((err) => {
      //       console.log(err)
      //     })
      // } else if (this.type === 'keeper') {
      //   axios.get('http://localhost:8080/api/keeperReview?keeper_id=' + this.id)
      //     .then(res => {
      //       console.log(res.data)
      //       this.reviews = res.data
      //     })
      //     .catch((err) => {
      //       console.log(err)
      //     })
      // } else if (this.type === 'delivery') {
      //   axios.get('http://localhost:8080/api/deliveryReview?delivery_id=' + this.id)
      //     .then(res => {
      //       console.log(res.data)
      //       this.reviews = res.data
      //     })
      //     .catch((err) => {
      //       console.log(err)
      //     })
      // }


    },
    pageChange(i) {
      console.log('test', i)
      this.current_page = i
    },
    pageFirst() {
      this.current_page = 1
    },
    pagePrev() {
      if (this.current_page > 1) {
        this.current_page -= 1
      }
    },
    pageNext() {
      if (this.current_page < this.max_page) {
        this.current_page += 1
      }
    },
    pageLast() {
      this.current_page = this.max_page
    },


    reviewsUpdated() {
      this.getReviews()
    }


  },


}
</script>

<style scoped>
.active-page-num {
  background-color: #f7f7f7;
  color: #333;
}

.bhs_button {
  margin: 20px 0 20px 5px;
}

.bhs_button {
  width: 150px;
  display: inline-block;
  line-height: 40px;
  text-align: center;
  background-color: gray;
  border: 1px solid #333;
  color: #fff;
  float: right;
  margin-left: 2px;
  font-size: 13px;
}

.bhs_button:hover {
  width: 150px;
  display: inline-block;
  line-height: 40px;
  text-align: center;
  background-color: #fff;
  border: 1px solid #333;
  color: #333;
  float: right;
  margin-left: 2px;
}

.btnArea a {
  background-color: transparent;
  text-decoration: none;
  color: inherit;
  display: inline-block;
}


.btnArea {
  padding: 10px 0;
  text-align: right;
  width: 100%;
  display: table;
  border-top: 1px solid #6a3664;

}

.layout-pagination-number:hover {
  background-color: #f7f7f7;
  color: #5f0080;
}

a:any-link {
  color: -webkit-link;
  text-decoration: underline;
  cursor: auto;
}

.layout-pagination-first-page {
  background-image: url(https://res.kurly.com/pc/etc/old/images/common/icon-pagination-first.png);
}

.layout-pagination-prev-page {
  background-image: url(https://res.kurly.com/pc/etc/old/images/common/icon-pagination-prev.png);
}

.layout-pagination-next-page {
  background-image: url(https://res.kurly.com/pc/etc/old/images/common/icon-pagination-next.png);
}

.layout-pagination-last-page {
  background-image: url(https://res.kurly.com/pc/etc/old/images/common/icon-pagination-last.png);
}

.layout-pagination-first-page, .layout-pagination-prev-page, .layout-pagination-next-page, .layout-pagination-last-page {
  background-repeat: no-repeat;
  background-position: 50% 50%;
  text-indent: -9999px;
}

.layout-pagination-number {
  font-size: 12px;
  font-weight: 700;
  color: #333;
  line-height: 34px;
}

.layout-pagination-button {
  display: inline-block;
  width: 34px;
  height: 34px;
  border: 1px solid #ddd;
  border-left: 0;
  vertical-align: top;
}

.board_pg_area {
  clear: both;
  text-align: center;
  padding-bottom: 15px;
}

.xans-product-additional .btnArea .bhs_button {
  margin: 50px 0 20px 5px
}

.xans-product-additional div.board .btnArea {
  text-align: center
}

.xans-product-additional div.board .btnArea a {
  display: inline-block
}

.xans-product-additional div.board .btnArea a .btn_submit {
  background-color: #5f0080
}

.xans-product-additional div.board .btnArea a .btn_submit:hover {
  background-color: #401661;
  color: #fff
}
</style>

