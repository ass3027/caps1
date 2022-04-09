<template>
  <div>
    <review-form
      :product="product"
      @review-submitted="reviewsUpdated"
    />
    <br>
    <review-list
      :reviews="reviews"
      :product="product"
      @deleteReview="getReviews"
      @reveiws-updated="reviewsUpdated"
    />
  </div>
</template>

<script>
import ReviewForm from "@/components/review/ReviewForm";
import ReviewList from "@/components/review/ReviewList";
import axios from "axios";

export default {
  name: 'TestView',
  components: {
    ReviewForm,
    ReviewList,
  },
  props: {
    product: {
      type: Object
    }
  },
  data(){
    return{
      reviews:[]
    }
  },
  created() {
    this.getReviews()
  },
  methods:{

    getReviews(){
      axios('/api/review/all')
        .then(res => {
          console.log(res.data)
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

