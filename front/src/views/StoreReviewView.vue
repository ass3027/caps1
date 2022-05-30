<template>
  <div id="review" style="padding-top: 70px">

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
  props:{'store_id': String},
  data(){
    return{
      reviews:[],
      overlay: false,
      zIndex: 100,
    }
  },
  mounted() {
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

