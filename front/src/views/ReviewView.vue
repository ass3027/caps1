<template>
  <div style="max-width: 1050px">
    <h1>{{ Id }}</h1>
    {{ type }}


    <br>

    <div class="xans-element- xans-product xans-product-additional detail_board  ">
      <review-list
        :reviews="reviews"
        @deleteReview="getReviews"
        @reveiws-updated="reviewsUpdated"
      />
    </div>

<!--    <div class="board_pg_area">-->
<!--      <a class="layout-pagination-button layout-pagination-first-page">맨 처음 페이지로 가기</a>-->
<!--      <a class="layout-pagination-button layout-pagination-prev-page">이전 페이지로 가기</a>-->
<!--      <a>1</a>-->
<!--      <a class="layout-pagination-button layout-pagination-number">2</a>-->
<!--      <a class="layout-pagination-button layout-pagination-number">3</a>-->
<!--      <a class="layout-pagination-button layout-pagination-number">4</a>-->
<!--      <a class="layout-pagination-button layout-pagination-number">5</a>-->
<!--      <a class="layout-pagination-button layout-pagination-number">6</a>-->
<!--      <a class="layout-pagination-button layout-pagination-number">7</a>-->
<!--      <a class="layout-pagination-button layout-pagination-number">8</a>-->
<!--      <a class="layout-pagination-button layout-pagination-number">9</a>-->
<!--      <a class="layout-pagination-button layout-pagination-number">10</a>-->
<!--      <a class="layout-pagination-button layout-pagination-next-page">다음 페이지로 가기</a>-->
<!--      <a class="layout-pagination-button layout-pagination-last-page">맨 끝 페이지로 가기</a>-->
<!--    </div>-->

  </div>
</template>

<script>
import ReviewForm from "@/components/review/ReviewForm";
import ReviewList from "@/components/review/ReviewList";
import axios from "axios";

export default {
  name      : 'ProductReviewView',
  components: {
    // ReviewForm,
    ReviewList,
  },
  props     : {
    id  : {
      type: String
    },
    type: {
      type: String
    }
  },
  data() {
    return {
      slice_reviews: [],
      reviews: [],
      overlay: false,
      zIndex : 100,
      page: 1,
    }
  },
  created() {
    this.getReviews()
  },
  methods: {

    getReviews() {

      if (this.type === 'product') {
        axios.get('http://localhost:8080/api/productReview?pd_id=' + this.id)
          .then(res => {
            console.log(res.data)
            this.reviews = res.data
          })
          .catch((err) => {
            console.log(err)
          })
      } else if (this.type === 'store') {

      } else if (this.type === 'guide') {

      } else if (this.type === 'keeper') {

      }


    },
    reviewsUpdated() {
      this.getReviews()
    }
  }


}
</script>

<style scoped>
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

