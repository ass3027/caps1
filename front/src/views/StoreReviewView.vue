<template>
  <div
    id="review"
    style="padding-top: 70px"
  >
    <!--
    <v-row justify="center">
      <v-btn
        class="white--text"
        color="teal"
        @click="overlay = !overlay"
      >
        Show Overlay
      </v-btn>

      <v-overlay
        :z-index="zIndex"
        :value="overlay"
      >
        <review-form
          @review-submitted="reviewsUpdated"
        />
        <v-btn
          class="white--text"
          color="teal"
          @click="overlay = false"
        >
          Hide Overlay
        </v-btn>
      </v-overlay>
    </v-row>
    <br>-->

    <review-list

      :reviews="reviews"
      @deleteReview="getReviews"
      @reveiws-updated="reviewsUpdated"
    />
  </div>
</template>

<script>
// import ReviewForm from "@/components/review/ReviewForm";
import ReviewList from "@/components/review/ReviewList";
import axios from "axios";

export default {
  name: 'StoreReviewView',
  components: {
    ReviewList,
  },
  props:{
    // 상품, 주문서 등
    // 상품ID, 주문서ID 등
    'type': String,
    'store_id': String
  },
  data(){
    return{
      reviews:[],
      overlay: false,
      zIndex: 100,
    }
  },
  mounted() {
    // type에 따라 메소드를 나누고 분기 처리
    this.getReviews()

  },
  methods:{

    getReviews(){
      console.log('store_id: ' + this.store_id)

      axios({
        method: 'GET',
        url:'http://localhost:8080/api/storeReview',
        params: {
          store_id:this.store_id
        }

      })
        .then(res => {
          console.log("store_reviews: "+res.data)
          this.reviews = res.data
        })
        .catch((err) => {
          console.log(err)
        })
    },
    reviewsUpdated() {
      this.getReviews()
    }
  }
}
</script>

