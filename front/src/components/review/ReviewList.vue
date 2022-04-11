<template>
  <v-container>
    <ul>
      <h3>Review {{ reviews.length }}</h3>

      <div
        v-for="(review, index) in reviews"
        :key="index"
      >

        <ReviewItem
          :review="review"
          @deleteReview="deleteReview"
          @review-updated="updateReview"
        />
      </div>
    </ul>
  </v-container>
</template>

<script>
import ReviewItem from "@/components/review/ReveiwItem";

export default {


  name:'ReviewList',
  components: {
    ReviewItem
  },
  props: {
    reviews:{
      type: [Array, Object],
    }
  },
  data() {
    return {


    }
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
