<template>
  <div>
    <h1>{{ Id }}</h1>
    {{ type }}


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
    <br>
    <review-list
      :reviews="reviews"
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
  name: 'ProductReviewView',
  components: {
    ReviewForm,
    ReviewList,
  },
  props:{
    id: {
      type: String
    },
    type: {
      type: String
    }
  },
  data(){
    return{
      reviews:[],
      overlay: false,
      zIndex: 100,
    }
  },
  created() {
    this.getReviews()
  },
  methods:{

    getReviews(){

      if(this.type === 'product') {
        axios.get('http://localhost:8080/api/productReview?pd_id='+this.id)
          .then(res => {
            console.log(res.data)
            this.reviews = res.data
          })
          .catch((err) => {
            console.log(err)
          })
      }


    },
    reviewsUpdated() {
      this.getReviews()
    }
  }


}
</script>

